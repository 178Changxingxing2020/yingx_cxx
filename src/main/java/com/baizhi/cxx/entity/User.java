package com.baizhi.cxx.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "yx_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @Excel(name="ID",height = 20, width = 30)
  private String id;
  @Excel(name="用户名")
  private String username;
  @Excel(name="手机号")
  private String phone;
  @Excel(name="头像",type = 2)
  private String headImg;
  @Excel(name="签名")
  private String sign;
  @Excel(name="微信")
  private String wechat;
  @Excel(name="状态")
  private String status;
  @Excel(name="注册时间",format = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private java.util.Date createDate;
  private Integer videoNum;
  private Integer fansNum;
  private Integer score;

}
