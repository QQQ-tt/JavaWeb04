package com.shop.bean;

import lombok.Data;

/** 商品分类实体类 @Author: QTX @Date: 2021/4/27 */
@Data
public class ProductCategoryBean {
  /** 编号 */
  private int id;
  /** 名字 */
  private String name;
  /** 父分类 */
  private int parentId;
}
