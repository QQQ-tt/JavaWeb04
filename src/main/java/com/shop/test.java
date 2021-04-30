package com.shop;

import com.alibaba.fastjson.JSONArray;
import com.shop.dao.impl.CommentDaoImpl;
import com.shop.service.CommService;
import com.shop.service.impl.CommentServiceImpl;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/** @Author: QTX @Date: 2021/4/29 */
public class test {
  @Test
  public void test() {
    try {
      Class<?> aClass = Class.forName("com.shop.service.impl.UserServiceImpl");
      Object o = aClass.newInstance();

      Method acModth = aClass.getDeclaredMethod("login", String.class, String.class);
      // 执行方法
      String resView = (String) acModth.invoke(o, "qqq", "123456");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException | NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void json() {
    JSONArray objects = new JSONArray();
    CommService commService = new CommentServiceImpl();
    List reveal = commService.reveal(1);
    for (int i = 0; i < reveal.size(); i++) {
      objects.add(i, reveal.get(i));
    }
    System.out.println(objects);
  }

  @Test
  public void num() {
    CommentDaoImpl commentDao = new CommentDaoImpl();
    int select = commentDao.select();
    System.out.println(select);
  }
}
