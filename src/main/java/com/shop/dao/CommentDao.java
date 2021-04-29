package com.shop.dao;

import java.util.List;

/** @Author: QTX @Date: 2021/4/29 */
public interface CommentDao {
  /**
   * 留言
   *
   * @param name 留言用户昵称
   * @param title 留言标题
   * @param content 留言内容
   */
  void insert(String name, String title, String content);

  /**
   * 展示所有留言
   *
   * @return
   */
  List select();

  /**
   * 回复
   *
   * @param content 回复内容
   * @param id 留言信息id
   */
  void update(String content, int id);
}
