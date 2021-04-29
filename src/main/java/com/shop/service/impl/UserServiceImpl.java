package com.shop.service.impl;

import com.shop.bean.UserBean;
import com.shop.dao.UserDao;
import com.shop.dao.impl.UserDaoImpl;
import com.shop.exception.MyException;
import com.shop.factory.ObjectFactory;
import com.shop.service.UserService;
import com.shop.transaction.TransAction;
import com.shop.transaction.impl.TransActionImpl;

import java.util.List;

/** @Author: QTX @Date: 2021/4/28 */
public class UserServiceImpl implements UserService {

  UserDao userDao = (UserDaoImpl) ObjectFactory.getObj("UserDao");
  TransAction action = (TransActionImpl) ObjectFactory.getObj("TransAction");

  @Override
  public String login(String name, String password) throws MyException {
    action.begin();
    List list = userDao.select(name);
    action.commit();
    if (list.size() == 0) {
      throw new MyException("用户名不正确");
    }
    Object o = list.get(0);
    UserBean userBean = (UserBean) o;
    if (!userBean.getPassword().equals(password)) {
      throw new MyException("密码不正确");
    }
    int status = userBean.getStatus();
    if (status == 1) {
      return "普通用户";
    } else {
      return "管理员";
    }
  }

  @Override
  public void registration(String name, String password) throws MyException {
    action.begin();
    List list = userDao.select(name);
    if (list.size() != 0) {
      throw new MyException("用户名已存在");
    }
    userDao.insert(name, password);
    action.commit();
  }

  @Override
  public void revise(
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
    action.begin();
    userDao.update(name, password, sex, birthday, identityCode, email, mobile, address, status, id);
    action.commit();
  }
}
