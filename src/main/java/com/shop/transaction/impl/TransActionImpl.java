package com.shop.transaction.impl;

import com.shop.transaction.TransAction;
import com.shop.util.JdbcUtil;
import lombok.SneakyThrows;

import java.sql.Connection;

/** @Author: QTX @Date: 2021/4/6 */
public class TransActionImpl implements TransAction {

  @SneakyThrows
  @Override
  public void begin() {
    Connection conn = JdbcUtil.getConn();
    conn.setAutoCommit(false);
  }

  @SneakyThrows
  @Override
  public void commit() {
    Connection conn = JdbcUtil.getConn();
    conn.commit();
    JdbcUtil.closeConn();
  }

  @SneakyThrows
  @Override
  public void rollback() {
    Connection conn = JdbcUtil.getConn();
    conn.rollback();
    // 放回池子中
    JdbcUtil.closeConn();
  }
}
