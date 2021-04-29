package com.shop.exception;

/** @Author: QTX @Date: 2021/4/28 */
public class MyException extends Exception {
  private static final long serialVersionUID = 5047149790647023294L;

  public MyException() {}

  public MyException(String message) {
    super(message);
  }
}
