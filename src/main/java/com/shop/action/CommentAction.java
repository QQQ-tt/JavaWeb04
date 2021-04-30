package com.shop.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shop.core.ActionContext;
import com.shop.factory.ObjectFactory;
import com.shop.service.CommService;
import com.shop.service.impl.CommentServiceImpl;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/** @Author: QTX @Date: 2021/4/29 */
public class CommentAction {

  CommService commService = (CommentServiceImpl) ObjectFactory.getObj("CommService");

  /**
   * 提交留言
   *
   * @return
   */
  public String addMessage() {
    HttpServletRequest request = ActionContext.getActionContext().getRequest();
    String name = request.getParameter("guestName");
    String title = request.getParameter("guestTitle");
    String content = request.getParameter("guestContent");
    commService.leaveComments(name, title, content);
    return "success";
  }

  /** 查询留言 */
  @SneakyThrows
  public void queryComms() {
    HttpServletRequest request = ActionContext.getActionContext().getRequest();
    String page = request.getParameter("page");
    List reveal = commService.reveal(Integer.parseInt(page));
    JSONArray jsonArray = new JSONArray();
    for (int i = 0; i < reveal.size(); i++) {
      jsonArray.add(i, reveal.get(i));
    }
    PrintWriter out = ActionContext.getActionContext().getResponse().getWriter();
    out.print(jsonArray);
    out.close();
  }

  @SneakyThrows
  public void pageNum() {
    JSONObject jsonObject = new JSONObject();
    HttpServletRequest request = ActionContext.getActionContext().getRequest();
    HttpServletResponse response = ActionContext.getActionContext().getResponse();
    int num = commService.reveal();
    request.getSession().setAttribute("num", num);
    jsonObject.put("num", request.getSession().getAttribute("num"));
    PrintWriter out = response.getWriter();
    out.print(JSONObject.toJSONString(jsonObject));
    out.close();
  }
}
