package com.shop.dao.rowamapper.Impl;

import com.shop.bean.UserBean;
import com.shop.dao.rowamapper.RowedMapper;
import lombok.SneakyThrows;

import java.sql.ResultSet;

/** 将查询结果封装成对象 @Author: QTX @Date: 2021/4/6 */
public class UserRowsMapperImpl implements RowedMapper {
  @SneakyThrows
  @Override
  public Object setObject(ResultSet rs) {
    UserBean userBean = new UserBean();
    userBean.setId(rs.getInt("eu_user_id"));
    userBean.setName(rs.getString("eu_user_name"));
    userBean.setPassword(rs.getString("eu_password"));
    userBean.setSex(rs.getString("eu_sex"));
    userBean.setBirthday(rs.getString("eu_birthday"));
    userBean.setIdCard(rs.getString("eu_identity_code"));
    userBean.setEmail(rs.getString("eu_email"));
    userBean.setMobile(rs.getString("eu_mobile"));
    userBean.setAddress(rs.getString("eu_address"));
    userBean.setStatus(rs.getInt("eu_status"));
    return userBean;
  }
}
