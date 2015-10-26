package com.mystring.pojo;

import java.util.Date;

public class AdminOperaRecords
{
  private Integer id;
  private Date operaTime;
  private String ip;
  private Integer adminID;
  private String operaContent;
  private String operaAdminName;

  public String getOperaAdminName()
  {
    return this.operaAdminName; }

  public void setOperaAdminName(String operaAdminName) {
    this.operaAdminName = operaAdminName; }

  public Integer getId() {
    return this.id; }

  public void setId(Integer id) {
    this.id = id; }

  public Date getOperaTime() {
    return this.operaTime; }

  public void setOperaTime(Date operaTime) {
    this.operaTime = operaTime; }

  public String getIp() {
    return this.ip; }

  public void setIp(String ip) {
    this.ip = ip; }

  public Integer getAdminID() {
    return this.adminID; }

  public void setAdminID(Integer adminID) {
    this.adminID = adminID; }

  public String getOperaContent() {
    return this.operaContent; }

  public void setOperaContent(String operaContent) {
    this.operaContent = operaContent;
  }
}