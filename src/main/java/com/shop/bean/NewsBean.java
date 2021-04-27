package com.shop.bean;

import lombok.Data;

/** 新闻实体类 @Author: QTX @Date: 2021/4/27 */
@Data
public class NewsBean {
  /** 编号 */
  private int id;
  /** 标题 */
  private String title;
  /** 内容 */
  private String content;
  /** 录入时间 */
  private String dateTime;
}
