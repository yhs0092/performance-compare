package com.github.yhs0092.demo.performance_compare.javachassis;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;

public class JavaChassisServerMain {
  public static void main(String[] args) throws Exception {
    System.setProperty("local.registry.file", "registry.yaml");
    Log4jUtils.init();
    BeanUtils.init();
  }
}
