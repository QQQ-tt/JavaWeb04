package com.shop.core;

import lombok.Data;

/** @Author: QTX @Date: 2021/4/26 */
@Data
public class ResultConfig {
  private String name;
  private String type;
  private String path;

  public ResultConfig(String name, String type, String path) {
    this.name = name;
    this.type = type;
    this.path = path;
  }
}
