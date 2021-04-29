package com.shop.service;

import com.shop.exception.MyException;

/** @Author: QTX @Date: 2021/4/27 */
public interface UserService {
  /**
   * 登录
   *
   * @param name
   * @param password
   * @return 结果1 普通用户 2 管理员 3 登录失败
   * @throws MyException 登录失败原因
   */
  String login(String name, String password) throws MyException;

  /**
   * 注册
   *
   * @param name
   * @param password
   */
  void registration(String name, String password) throws MyException;

  /**
   * 修改信息
   *
   * @param name
   * @param password
   * @param sex
   * @param birthday
   * @param identityCode
   * @param email
   * @param mobile
   * @param address
   * @param status
   */
  void revise(
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
}
