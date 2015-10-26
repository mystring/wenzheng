package com.mystring.pojo;

import java.util.Date;

public class Orders
{
  private String id;
  private String name;
  private String tel;
  private String address;
  private String status;
  private Date insertTime;
  private Date sendTime;
  private Date receiveTime;
  private Company company;
  private String isNoticed;
  private String position;

  public String getPosition()
  {
    return this.position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getIsNoticed() {
    return this.isNoticed;
  }

  public void setIsNoticed(String isNoticed) {
    this.isNoticed = isNoticed;
  }

  public Company getCompany() {
    return this.company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public Date getSendTime() {
    return this.sendTime;
  }

  public void setSendTime(Date sendTime) {
    this.sendTime = sendTime;
  }

  public Date getReceiveTime() {
    return this.receiveTime;
  }

  public void setReceiveTime(Date receiveTime) {
    this.receiveTime = receiveTime;
  }

  public Date getInsertTime() {
    return this.insertTime;
  }

  public void setInsertTime(Date insertTime) {
    this.insertTime = insertTime;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTel() {
    return this.tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}