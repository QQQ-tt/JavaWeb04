package com.shop.action;

import com.alibaba.fastjson.JSONObject;
import com.shop.core.ActionContext;
import com.shop.exception.MyException;
import com.shop.factory.ObjectFactory;
import com.shop.service.UserService;
import com.shop.service.impl.UserStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** @Author: QTX @Date: 2021/4/28 */
public class UserAction {
  UserService userService = (UserService) ObjectFactory.getObj("UserService");

  /**
   * 登录
   *
   * @return 视图
   */
  public String login() {
    JSONObject jsonObject = new JSONObject();
    HttpServletRequest request = ActionContext.getActionContext().getRequest();
    String name = request.getParameter("name");
    String password = request.getParameter("passWord");
    String login = "";
    PrintWriter out = null;
    try {
      out = ActionContext.getActionContext().getResponse().getWriter();
      login = userService.login(name, password);
      if (login.equals(UserStatus.generalUser.status)) {
        request.getSession().setAttribute("name", name);
        return "generalUser";
      } else {
        request.getSession().setAttribute("name", name);
        return "administrator";
      }
    } catch (MyException e) {
      jsonObject.put("MyException", e.getMessage());
      out.print(JSONObject.toJSONString(jsonObject));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /** 登录后网页用户名展示 */
  public void loginUser() {
    JSONObject jsonObject = new JSONObject();
    HttpServletRequest request = ActionContext.getActionContext().getRequest();
    HttpServletResponse response = ActionContext.getActionContext().getResponse();
    if (request.getSession().getAttribute("name") != null) {
      jsonObject.put("name", request.getSession().getAttribute("name"));
    }
    try {
      PrintWriter out = out = response.getWriter();
      out.print(JSONObject.toJSONString(jsonObject));
      System.out.println(JSONObject.toJSONString(jsonObject));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** 退出登录删除Session里的用户名 */
  public void empty() {
    HttpServletRequest request = ActionContext.getActionContext().getRequest();
    request.getSession().removeAttribute("name");
  }

  /** 注册用户 */
  public String logging() {
    JSONObject jsonObject = new JSONObject();
    HttpServletRequest request = ActionContext.getActionContext().getRequest();
    String name = request.getParameter("name");
    String passWord = request.getParameter("rePassWord");
    PrintWriter out = null;
    try {
      out = ActionContext.getActionContext().getResponse().getWriter();
      userService.registration(name, passWord);
    } catch (MyException e) {
      request.getSession().setAttribute("MyException", e.getMessage());
      jsonObject.put("MyException", request.getSession().getAttribute("MyException"));
      out.print(JSONObject.toJSONString(jsonObject));
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      out.close();
    }
    return "success";
  }
}
