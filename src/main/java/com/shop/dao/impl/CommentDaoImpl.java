package com.shop.dao.impl;

import com.shop.dao.CommentDao;
import com.shop.dao.rowamapper.Impl.CommentRowsMapper;
import com.shop.util.JdbcTemplate;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/** @Author: QTX @Date: 2021/4/29 */
public class CommentDaoImpl implements CommentDao {
  @SneakyThrows
  @Override
  public void insert(String name, String title, String content) {
    String sql =
        "insert into easy_buy_comment(ec_nick_name,ec_title,ec_content,ec_create_time) values (?,?,?,?)";

    JdbcTemplate.executeUpdate(sql, name, title, content, time());
  }

  @SneakyThrows
  @Override
  public List select() {
    String sql = "select * from easy_buy_comment";
    return JdbcTemplate.executeQuery(sql, new CommentRowsMapper(), null);
  }

  @SneakyThrows
  @Override
  public void update(String content, int id) {
    String sql = "update easy_buy_comment set ec_reply = ?,ec_reply_time = ? where ec_id = ?";
    JdbcTemplate.executeUpdate(sql, content, time(), id);
  }

  public String time() {
    Date dd = new Date();
    // 格式化
    SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String time = sim.format(dd);
    return time;
  }
}
