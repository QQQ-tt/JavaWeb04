package com.shop.core;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** @Author: QTX @Date: 2021/4/26 */
@Data
public class ActionContext {
  private HttpServletResponse response;
  private HttpServletRequest request;
  private static ThreadLocal<ActionContext> threadLocal = new ThreadLocal();

  public static ActionContext getActionContext() {
    return (ActionContext) threadLocal.get();
  }

  public static void setActionContext(ActionContext actionContext) {
    threadLocal.set(actionContext);
  }

  public ActionContext(HttpServletResponse response, HttpServletRequest request) {
    this.response = response;
    this.request = request;
  }
}
