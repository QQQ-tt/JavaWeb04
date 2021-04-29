package com.shop.dao.impl;

import com.shop.dao.UserDao;
import com.shop.dao.rowamapper.Impl.UserRowsMapperImpl;
import com.shop.util.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

/** @Author: QTX @Date: 2021/4/27 */
public class UserDaoImpl implements UserDao {
  @Override
  public void insert(String name, String password) {
    String sql = "insert into easy_buy_user(eu_user_name,eu_password) values (?,?)";
    try {
      JdbcTemplate.executeUpdate(sql, name, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(int id) {
    String sql = "delete from easy_buy_user where eu_user_id = ?";
    try {
      JdbcTemplate.executeUpdate(sql, id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(
      String name,
      String password,
      String sex,
      String birthday,
      String identityCode,
      String email,
      String mobile,
      String address,
      int status,
      int id) {
    String sql =
        "update easy_buy_user set eu_user_name = ?,eu_password = ?,"
            + "eu_sex = ?,eu_birthday = ?,eu_identity_code = ?,"
            + "eu_email = ?,eu_mobile = ?,eu_address= ?,eu_status = ? where eu_user_id = ?";
    try {
      JdbcTemplate.executeUpdate(
          sql, name, password, sex, birthday, identityCode, email, mobile, address, status, id);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List select() {
    String sql = "select * from easy_buy_user";
    List list = null;
    try {
      list = JdbcTemplate.executeQuery(sql, new UserRowsMapperImpl(), null);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  @Override
  public List select(String name) {
    String sql = "select * from easy_buy_user where eu_user_name = ?";
    List list = null;
    try {
      list = JdbcTemplate.executeQuery(sql, new UserRowsMapperImpl(), name);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
}
