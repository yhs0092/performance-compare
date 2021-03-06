package com.github.yhs0092.demo.performance_compare.springboot2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class SpringBoot2ServerMain {
  @RequestMapping(value = "/server/reflect", method = RequestMethod.POST)
  public String reflect(@RequestBody String body) {
    return body;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringBoot2ServerMain.class, args);
  }
}
