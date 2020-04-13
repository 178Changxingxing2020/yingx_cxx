package com.baizhi.cxx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "yingx_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {

  @Id
  private String id;
  private String adminName;
  private java.util.Date time;
  private String operation;
  private String status;


}
