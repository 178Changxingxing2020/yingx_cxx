package com.baizhi.cxx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "yx_video")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {

  @Id
  private String id;
  private String title;
  private String brief;
  private String path;
  private String cover;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private java.util.Date publishDate;
  private String likeNum;
  private String playNum;
  private String categoryId;
  private Category category;

  private String groupId;
 // private ;

  private String userId;
  private User user;

}
