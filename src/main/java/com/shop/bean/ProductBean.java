package com.shop.bean;

import lombok.Data;

/** 商品信息实体类 @Author: QTX @Date: 2021/4/27 */
@Data
public class ProductBean {
  /** 编号 自增 */
  private int id;
  /** 商品名字 */
  private String name;
  /** 描述 */
  private String description;
  /** 价格 */
  private double price;
  /** 库存 */
  private int stock;
  /** 分类id */
  private int pId;
  /** 二级分类id */
  private int pChildId;
  /** 文件名 */
  private String fileName;
}
