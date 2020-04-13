package com.baizhi.cxx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "yx_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

  @Id
  private String id;
  private String cateName;
  private String levels;
  private String parentId;

}
