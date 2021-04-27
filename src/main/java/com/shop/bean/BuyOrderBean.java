package com.shop.bean;

import lombok.Data;

/** 用户订单实体类 @Author: QTX @Date: 2021/4/27 */
@Data
public class BuyOrderBean {
  /** 编号 */
  private int id;
  /** 用户id */
  private int userId;
  /** 用户名 */
  private String userName;
  /** 用户地址 */
  private String userAddress;
  /** 创建时间 */
  private String createTime;
  /** 金额 */
  private double cost;
  /** 状态 1 下单 2 审核通过 3 配货 4 送货中 5收获并确认 */
  private int status;
  /** 付款方式 1 货到付款 2 网上支付 */
  private int type;
}
