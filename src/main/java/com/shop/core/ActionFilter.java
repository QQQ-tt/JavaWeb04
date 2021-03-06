package com.shop.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** @Author: QTX @Date: 2021/4/26 */
@WebFilter(filterName = "actionFilter", urlPatterns = "/*")
public class ActionFilter implements Filter {

  private Map<String, ActionConfig> actionConfigMap = new HashMap();
  private String charset;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    SAXReader reader = new SAXReader();

    try {
      Document document =
          reader.read(ActionFilter.class.getClassLoader().getResourceAsStream("action.xml"));
      Element rootElement = document.getRootElement();
      List<Element> elements = rootElement.elements();
      Iterator var8 = elements.iterator();

      while (var8.hasNext()) {
        Element element = (Element) var8.next();
        String name = element.attributeValue("name");
        String method = element.attributeValue("method");
        String aClass = element.attributeValue("class");
        ActionConfig actionConfig = new ActionConfig(name, method, aClass);
        this.actionConfigMap.put(name, actionConfig);
        List<Element> rlist = element.elements();
        Iterator var14 = rlist.iterator();

        while (var14.hasNext()) {
          Element eres = (Element) var14.next();
          String rname = eres.attributeValue("name");
          String rtype = eres.attributeValue("type");
          String path = eres.getText();
          ResultConfig rc = new ResultConfig(rname, rtype, path);
          actionConfig.getAcResult().put(rname, rc);
          System.out.println(rc);
        }
      }
    } catch (DocumentException var20) {
      var20.printStackTrace();
    }
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    req.setCharacterEncoding("utf-8");
    // ???????????????????????????action??????
    String uri = req.getRequestURI();
    if (uri.contains(".css") || uri.contains(".js") || uri.contains(".png")) {
      // response.setContentType("text/css;charset=utf-8");
    } else {
      // ??????????????????
      response.setContentType("text/html;charset=utf-8");
    }
    // ????????????????????????
    ActionContext ac = new ActionContext(resp, req);
    // ?????????????????????????????????????????????????????????
    ActionContext.setActionContext(ac);

    String resUrl = uri.substring(req.getContextPath().length() + 1);
    System.out.println(resUrl);
    System.out.println("--------------");

    if (actionConfigMap.containsKey(resUrl)) {
      // ?????????????????????action?????????name??????
      ActionConfig acfig = actionConfigMap.get(resUrl);
      // ???????????????????????????
      try {
        Class cls = Class.forName(acfig.getClassName());
        // ??????????????????
        Object obj = cls.newInstance();
        // ??????action??????????????????
        String mod = acfig.getMethod();
        Method acModth = cls.getDeclaredMethod(mod, null);
        // ????????????
        String resView = (String) acModth.invoke(obj, null);
        // ????????????????????????resultConfig??????
        Map<String, ResultConfig> resMap = acfig.getAcResult();
        // ???????????????-->success??????error
        if (!(resMap.containsKey(resView))) {
          // throw new RuntimeException("???"+acfig.getName()+"?????????"+resView+"?????????");
          return;
        }
        // ????????????????????????ResultConfig????????????
        ResultConfig rcfig = resMap.get(resView);
        if ("dispatcher".equals(rcfig.getType())) {
          // ??????
          req.getRequestDispatcher(rcfig.getPath()).forward(req, resp);
        } else {
          // ?????????
          System.out.println(req.getContextPath() + rcfig.getPath());
          resp.sendRedirect(req.getContextPath() + rcfig.getPath());
        }
      } catch (ClassNotFoundException | InvocationTargetException e) {
        e.printStackTrace();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (SecurityException e) {
        e.printStackTrace();
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }
    } else {
      System.out.println("-----??????-----");
      chain.doFilter(req, resp);
    }
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}
