package com.shop.bean;

import lombok.Data;

/** 用户实体类 @Author: QTX @Date: 2021/4/27 */
@Data
public class UserBean {
  /** 用户id */
  private int id;
  /** 用户名 */
  private String name;
  /** 用户密码 */
  private String password;
  /** 性别 */
  private String sex;
  /** 出生日期 */
  private String birthday;
  /** 身份证号 */
  private String idCard;
  /** 邮箱 */
  private String email;
  /** 移动电话 */
  private String mobile;
  /** 地址 */
  private String address;
  /** 用户类型 普通用户 2 管理员 */
  private int status;
}
