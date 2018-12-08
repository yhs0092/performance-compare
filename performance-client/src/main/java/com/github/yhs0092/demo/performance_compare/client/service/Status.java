package com.github.yhs0092.demo.performance_compare.client.service;

import java.util.Objects;

public class Status {
  private int concurrency;

  private String requestBody;

  public int getConcurrency() {
    return concurrency;
  }

  public void setConcurrency(int concurrency) {
    this.concurrency = concurrency;
  }

  public String getRequestBody() {
    return requestBody;
  }

  public void setRequestBody(String requestBody) {
    this.requestBody = requestBody;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Status status = (Status) o;
    return concurrency == status.concurrency &&
        Objects.equals(requestBody, status.requestBody);
  }

  @Override
  public int hashCode() {
    return Objects.hash(concurrency, requestBody);
  }
}
