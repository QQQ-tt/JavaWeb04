package com.shop.bean;

import lombok.Data;

/** 订单明细实体类 @Author: QTX @Date: 2021/4/27 */
@Data
public class OrderDetailBean {
  /** 编号 */
  private int id;
  /** 订单id */
  private int oId;
  /** 商品id */
  private int pId;
  /** 数量 */
  private int quantity;
  /** 金额 */
  private double cost;
}
