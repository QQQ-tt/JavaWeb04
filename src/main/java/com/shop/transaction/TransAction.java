package com.shop.transaction;

/** 事务 @Author: QTX @Date: 2021/4/6 */
public interface TransAction {

  /** 开始 */
  void begin();

  /** 提交 */
  void commit();

  /** 回滚 */
  void rollback();
}
