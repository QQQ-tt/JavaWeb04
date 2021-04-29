package com.shop.core;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/** @Author: QTX @Date: 2021/4/26 */
@Data
public class ActionConfig {
  /** 请求路径名 */
  private String name;
  /** 执行方法名 */
  private String method;
  /** 方法所在的类路径 */
  private String className;
  /** 方法返回的结果集合 */
  private Map<String, com.shop.core.ResultConfig> acResult = new HashMap();

  public ActionConfig(String name, String method, String className) {
    this.name = name;
    this.method = method;
    this.className = className;
  }
}
