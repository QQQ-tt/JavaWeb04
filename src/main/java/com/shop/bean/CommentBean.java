package com.shop.bean;

import lombok.Data;

/** 评论实体类 @Author: QTX @Date: 2021/4/27 */
@Data
public class CommentBean {
  /** 编号 */
  private int id;
  /** 回复 */
  private String reply;
  /** 留言标题 */
  private String title;
  /** 内容 */
  private String content;
  /** 创建时间 */
  private String createTime;
  /** 回复时间 */
  private String replyTime;
  /** 留言用户昵称 */
  private String name;
}
