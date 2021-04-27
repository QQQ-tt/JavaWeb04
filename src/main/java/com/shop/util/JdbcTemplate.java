package com.shop.util;

import com.shop.dao.rowamapper.RowedMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** @Author: QTX @Date: 2021/4/27 */
public class JdbcTemplate {
  /**
   * 用于增删改的方法
   *
   * @param sql 从dao层传入的
   * @param params Object表示将来接受的参数类型不确定， ... ：表示将来接受的参数个数不确定， 如果使用数组接受，必然要设置长度，一旦超出长度则需要数组扩容
   * @return
   * @throws SQLException
   */
  public static int executeUpdate(String sql, Object... params) throws SQLException {
    // 获取连接
    Connection conn = JdbcUtil.getConn();
    // sql执行器
    PreparedStatement pstmt = conn.prepareStatement(sql);
    // 设置参数
    // for(){}不直接在这里遍历的原因是将来查询里面也需要设置参数，
    // 干脆提取成一个公共设置参数的方法，起码少写一个循环
    setParams(pstmt, params);
    // 执行sql
    int count = pstmt.executeUpdate();
    // 关闭连接
    JdbcUtil.close(pstmt, null);
    // 交给事务处理
    return count;
  }

  /**
   * 用于查询的公共方法
   *
   * @param sql
   * @param params
   * @return
   * @throws SQLException
   */
  public static List executeQuery(String sql, RowedMapper rm, Object... params)
      throws SQLException, SQLException {
    List list = new ArrayList();
    // 获取连接
    Connection conn = JdbcUtil.getConn();
    // sql执行器
    PreparedStatement pstmt = conn.prepareStatement(sql);
    // 设置参数
    setParams(pstmt, params);
    ResultSet rs = pstmt.executeQuery();
    while (rs.next()) {
      Object obj = rm.setObject(rs);
      list.add(obj);
    }
    JdbcUtil.close(pstmt, rs);
    // 交给事务处理
    return list;
  }

  public static void setParams(PreparedStatement pstmt, Object... params) throws SQLException {
    if (params != null && params.length > 0) {
      for (int i = 0; i < params.length; i++) {
        pstmt.setObject(i + 1, params[i]);
      }
    }
  }
}
