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
    // 获取地址栏中请求的action名称
    String uri = req.getRequestURI();
    if (uri.contains(".css") || uri.contains(".js") || uri.contains(".png")) {
      // response.setContentType("text/css;charset=utf-8");
    } else {
      // 处理响应乱码
      response.setContentType("text/html;charset=utf-8");
    }
    // 封装请求的消息头
    ActionContext ac = new ActionContext(resp, req);
    // 将封装请求的消息头存放到线程本地变量中
    ActionContext.setActionContext(ac);

    String resUrl = uri.substring(req.getContextPath().length() + 1);
    System.out.println(resUrl);
    System.out.println("--------------");

    if (actionConfigMap.containsKey(resUrl)) {
      // 如果当前请求的action对应的name存在
      ActionConfig acfig = actionConfigMap.get(resUrl);
      // 通过反射获取类路径
      try {
        Class cls = Class.forName(acfig.getClassName());
        // 创建创建对象
        Object obj = cls.newInstance();
        // 获取action节点上的方法
        String mod = acfig.getMethod();
        Method acModth = cls.getDeclaredMethod(mod, null);
        // 执行方法
        String resView = (String) acModth.invoke(obj, null);
        // 根据执行结果查找resultConfig对象
        Map<String, ResultConfig> resMap = acfig.getAcResult();
        // 有没有视图-->success还是error
        if (!(resMap.containsKey(resView))) {
          // throw new RuntimeException("在"+acfig.getName()+"找不到"+resView+"该视图");
          return;
        }
        // 如果视图存在则将ResultConfig获取出来
        ResultConfig rcfig = resMap.get(resView);
        if ("dispatcher".equals(rcfig.getType())) {
          // 转发
          req.getRequestDispatcher(rcfig.getPath()).forward(req, resp);
        } else {
          // 重定向
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
      System.out.println("-----放行-----");
      chain.doFilter(req, resp);
    }
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}
