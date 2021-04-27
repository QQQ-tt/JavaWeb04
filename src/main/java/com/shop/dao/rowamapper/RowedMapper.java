package com.shop.dao.rowamapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/** @Author: QTX @Date: 2021/4/6 */
public interface RowedMapper {
  /**
   * 创建对应实现类的对象
   *
   * @param rs 查询结果集
   * @return
   * @throws SQLException
   */
  Object setObject(ResultSet rs) throws SQLException;
}
