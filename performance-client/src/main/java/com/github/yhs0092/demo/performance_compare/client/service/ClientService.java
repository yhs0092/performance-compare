package com.github.yhs0092.demo.performance_compare.client.service;

import javax.inject.Inject;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestSchema(schemaId = "client")
@RequestMapping(path = "/client")
public class ClientService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

  @Inject
  private StatusManager statusManager;

  @PutMapping(path = "/status")
  public Status setStatus(@RequestBody Status status) {
    LOGGER.info("setStatus [{}]", status);
    return statusManager.migrateStatus(status);
  }

  @GetMapping(path = "/status")
  public Status getStatus() {
    return statusManager.currentStatus();
  }
}
