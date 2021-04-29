package com.shop;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
}
