package com.shop.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 对象工厂 @Author: QTX @Date: 2021/4/27 */
public class ObjectFactory {
  private static Map<String, Object> objectsMap = new HashMap<>();

  public static Object getObj(String name) {
    return objectsMap.get(name);
  }

  static {
    SAXReader reader = new SAXReader();
    try {
      // 获取object.xml配置文件
      Document read =
          reader.read(ObjectFactory.class.getClassLoader().getResourceAsStream("object.xml"));
      // 获得根节点
      Element rootElement = read.getRootElement();
      // 获得子节点集合
      List<Element> elements = rootElement.elements();
      for (Element element : elements) {
        // 获取标签上的属性值
        String name = element.attributeValue("name");
        String aClass = element.attributeValue("class");
        // 反射创建对象
        Object o = Class.forName(aClass).newInstance();

        objectsMap.put(name, o);
      }
    } catch (DocumentException | ClassNotFoundException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
