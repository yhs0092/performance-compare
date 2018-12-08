package com.github.yhs0092.demo.performance_compare.client.service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import javax.ws.rs.core.MediaType;

import org.apache.servicecomb.core.BootListener;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.serviceregistry.RegistryUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class ServerRegister implements BootListener {
  @Override
  public void onBootEvent(BootEvent event) {
    if (!EventType.AFTER_REGISTRY.equals(event.getEventType())) {
      return;
    }
    ArrayList<String> endpoints = new ArrayList<>();
    endpoints.add("rest://127.0.0.1:18081");
    RegistryUtils.getServiceRegistry()
        .registerMicroserviceMappingByEndpoints("server",
            "0.0.1",
            endpoints,
            ServerInterface.class);
  }

  @RestSchema(schemaId = "server")
  @RequestMapping(path = "/server", produces = MediaType.TEXT_PLAIN)
  public interface ServerInterface {
    @PostMapping(path = "/reflect")
    CompletableFuture<String> reflect(@RequestBody String body);
  }
}
