package com.shop.util;

import lombok.SneakyThrows;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/** 通过连接池对数据库连接进行管理 @Author: QTX @Date: 2021/4/27 */
public class JdbcUtil {
  /** 创建一个本地连接 */
  private static DataSource ds = null;
  /** 本地线程来保存连接 */
  private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

  static {
    Properties p = new Properties();
    try {
      // 类加载的方式读取文件
      p.load(JdbcUtil.class.getClassLoader().getResourceAsStream("datasource.properties"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      // p保存了配置数据库的连接
      ds = BasicDataSourceFactory.createDataSource(p);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 获取数据库连接
   *
   * @return 数据库连接
   */
  @SneakyThrows
  public static Connection getConn() {
    Connection conn = threadLocal.get();
    if (conn == null) {
      conn = ds.getConnection();
      threadLocal.set(conn);
    }
    return conn;
  }

  /** 将数据库连接返回池子中 */
  @SneakyThrows
  public static void closeConn() {
    Connection conn = threadLocal.get();
    if (conn != null) {
      // 将连接放回池子中
      threadLocal.remove();
      conn.close();
    }
  }

  public static void close(PreparedStatement pstmt, ResultSet rs) {
    if (pstmt != null) {
      try {
        pstmt.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
