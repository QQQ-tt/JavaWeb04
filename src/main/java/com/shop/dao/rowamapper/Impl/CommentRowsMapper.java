package com.shop.dao.rowamapper.Impl;

import com.shop.bean.CommentBean;
import com.shop.dao.rowamapper.RowedMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/** @Author: QTX @Date: 2021/4/29 */
public class CommentRowsMapper implements RowedMapper {
  @Override
  public Object setObject(ResultSet rs) throws SQLException {
    CommentBean commentBean = new CommentBean();
    commentBean.setId(rs.getInt("ec_id"));
    commentBean.setName(rs.getString("ec_nick_name"));
    commentBean.setTitle(rs.getString("ec_title"));
    commentBean.setContent(rs.getString("ec_content"));
    commentBean.setCreateTime(rs.getString("ec_create_time"));
    commentBean.setReply(rs.getString("ec_reply"));
    commentBean.setReplyTime(rs.getString("ec_nick_name"));

    return commentBean;
  }
}
