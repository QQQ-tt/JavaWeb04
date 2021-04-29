package com.shop.service.impl;

/** 用户类型 @Author: QTX @Date: 2021/4/28 */
public enum UserStatus {
  /** 普通用户 */
  generalUser("普通用户"),
  /** 管理员 */
  administrator("管理员");

  public final String status;

  /**
   * 用户类型
   *
   * @param status 用户类型
   */
  UserStatus(String status) {
    this.status = status;
  }
}
