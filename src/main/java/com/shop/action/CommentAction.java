package com.shop.action;

import com.shop.core.ActionContext;
import com.shop.factory.ObjectFactory;
import com.shop.service.CommService;
import com.shop.service.impl.CommentServiceImpl;

import javax.servlet.http.HttpServletRequest;

/** @Author: QTX @Date: 2021/4/29 */
public class CommentAction {

  CommService commService = (CommentServiceImpl) ObjectFactory.getObj("CommService");

  public String addMessage() {
    HttpServletRequest request = ActionContext.getActionContext().getRequest();
    String name = request.getParameter("guestName");
    String title = request.getParameter("guestTitle");
    String content = request.getParameter("guestContent");
    commService.leaveComments(name, title, content);
    return "success";
  }
}
