package com.shop.dao;

import java.util.List;

/** @Author: QTX @Date: 2021/4/27 */
public interface UserDao {
  /**
   * 增加用户
   *
   * @param name 用户名
   * @param password 密码
   */
  void insert(String name, String password);

  /**
   * 根据id删除用户
   *
   * @param id 用户id编号
   */
  void delete(int id);

  /**
   * 修该用户信息
   *
   * @param name 用户名
   * @param password 密码
   * @param sex 性别
   * @param birthday 生日
   * @param identityCode 身份证号
   * @param email 邮箱
   * @param mobile 移动电话
   * @param address 地址
   * @param status 用户类型 1普通用户 2管理员
   * @param id
   */
  void update(
      String name,
      String password,
      String sex,
      String birthday,
      String identityCode,
      String email,
      String mobile,
      String address,
      int status,
      int id);
  /**
   * 查询所有用户
   *
   * @return 查询结果
   */
  List select();

  /**
   * 根据昵称查询
   *
   * @param name 用户名
   * @return 查询结果
   */
  List select(String name);
}
