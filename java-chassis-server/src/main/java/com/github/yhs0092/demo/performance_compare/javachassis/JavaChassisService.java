package com.github.yhs0092.demo.performance_compare.javachassis;

import javax.ws.rs.core.MediaType;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestSchema(schemaId = "server")
@RequestMapping(path = "/server", produces = MediaType.TEXT_PLAIN)
public class JavaChassisService {
  @PostMapping(path = "/reflect")
  public String reflect(@RequestBody String body) {
    return body;
  }
}
