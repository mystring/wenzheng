package com.mystring.pojo;

import java.util.Date;

public class Company
{
  private Integer id;
  private String companyName;
  private int empNum;
  private Date createTime;
  private String address;

  public Company()
  {
  }

  public Company(Integer id)
  {
    this.id = id;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCompanyName() {
    return this.companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public int getEmpNum() {
    return this.empNum;
  }

  public void setEmpNum(int empNum) {
    this.empNum = empNum;
  }

  public Date getCreateTime() {
    return this.createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}