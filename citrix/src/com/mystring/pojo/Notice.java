package com.mystring.pojo;

import java.util.Date;

public class Notice
{
  private int id;
  private String title;
  private String content;
  private Date time;

  public Date getTime()
  {
    return this.time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}