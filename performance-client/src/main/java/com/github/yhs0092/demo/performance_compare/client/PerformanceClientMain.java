package com.github.yhs0092.demo.performance_compare.client;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;

public class PerformanceClientMain {
  public static void main(String[] args) throws Exception {
    System.setProperty("local.registry.file", "registry.yaml");
    Log4jUtils.init();
    BeanUtils.init();
  }
}
