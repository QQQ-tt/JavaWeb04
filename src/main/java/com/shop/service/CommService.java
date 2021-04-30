package com.shop.service;

import java.util.List;

/** @Author: QTX @Date: 2021/4/29 */
public interface CommService {
  /**
   * 留言
   *
   * @param name 昵称
   * @param title 标题
   * @param context 内容
   */
  void leaveComments(String name, String title, String context);

  /**
   * 展示对应页数的留言
   *
   * @return 留言结果集
   * @param page 页数
   */
  List reveal(int page);

  /**
   * 显示总数量
   *
   * @return 总数量
   */
  int reveal();

  /**
   * 回复内容
   *
   * @param context 内容
   * @param id 留言id
   */
  void reply(String context, int id);
}
