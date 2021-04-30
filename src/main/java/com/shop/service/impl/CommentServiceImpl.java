package com.shop.service.impl;

import com.shop.dao.CommentDao;
import com.shop.dao.impl.CommentDaoImpl;
import com.shop.factory.ObjectFactory;
import com.shop.service.CommService;
import com.shop.transaction.TransAction;
import com.shop.transaction.impl.TransActionImpl;

import java.util.List;

/** @Author: QTX @Date: 2021/4/29 */
public class CommentServiceImpl implements CommService {
  TransAction action = (TransActionImpl) ObjectFactory.getObj("TransAction");
  CommentDao commentDao = (CommentDaoImpl) ObjectFactory.getObj("CommentDao");

  @Override
  public void leaveComments(String name, String title, String context) {
    action.begin();
    commentDao.insert(name, title, context);
    action.commit();
  }

  @Override
  public List reveal(int page) {
    action.begin();
    List select = commentDao.select(page);
    action.commit();
    return select;
  }

  @Override
  public int reveal() {
    action.begin();
    int select = commentDao.select();
    action.commit();
    return select;
  }

  @Override
  public void reply(String context, int id) {
    action.begin();
    commentDao.update(context, id);
    action.commit();
  }
}
