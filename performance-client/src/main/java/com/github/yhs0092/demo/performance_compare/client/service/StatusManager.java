package com.github.yhs0092.demo.performance_compare.client.service;

import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.ws.Holder;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.yhs0092.demo.performance_compare.client.service.ServerRegister.ServerInterface;

@Component
public class StatusManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(StatusManager.class);

  @RpcReference(schemaId = "server", microserviceName = "server")
  private ServerInterface server;

  private ExecutorService executorService = Executors.newCachedThreadPool();

  private Status currentStatus = new Status();

  private Stack<Holder<Boolean>> workersFlag = new Stack<>();

  public Status migrateStatus(Status status) {
    Status oldStatus = currentStatus;
    currentStatus = status;
    if (currentStatus.getConcurrency() > oldStatus.getConcurrency()) {
      for (int i = 0; i < currentStatus.getConcurrency() - oldStatus.getConcurrency(); ++i) {
        Holder<Boolean> workerFlag = new Holder<>(true);
        executorService.submit(() -> invokeServer(workerFlag));
        workersFlag.add(workerFlag);
      }
    } else if (currentStatus.getConcurrency() < oldStatus.getConcurrency()) {
      for (int i = 0; i < oldStatus.getConcurrency() - currentStatus.getConcurrency(); ++i) {
        workersFlag.pop().value = false;
      }
    }

    return oldStatus;
  }

  private void invokeServer(Holder<Boolean> workerFlag) {
    server.reflect(currentStatus.getRequestBody()).whenComplete((s, throwable) -> {
      if (workerFlag.value ) {
        invokeServer(workerFlag);
//      } else if (null != throwable) {
//        throwable.printStackTrace();
      } else {
        LOGGER.info("worker stop, flag: {}", workerFlag.hashCode());
      }
    });
  }

  public Status currentStatus() {
    return currentStatus;
  }
}
