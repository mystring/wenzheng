package com.mystring.pojo;

import java.util.Date;

public class Records
{
  public Integer id;
  public Company company;
  public String oid;
  private String receiveName;
  private String receiveTel;
  private String receiveAddress;
  private String ostatus;
  private String nowAddress;
  private Date nowTime;
  private Employee nowEmp;

  public String getReceiveTel()
  {
    return this.receiveTel; }

  public void setReceiveTel(String receiveTel) {
    this.receiveTel = receiveTel;
  }

  public Integer getId() {
    return this.id; }

  public void setId(Integer id) {
    this.id = id; }

  public Company getCompany() {
    return this.company; }

  public void setCompany(Company company) {
    this.company = company; }

  public String getOid() {
    return this.oid; }

  public void setOid(String oid) {
    this.oid = oid; }

  public String getReceiveName() {
    return this.receiveName; }

  public void setReceiveName(String receiveName) {
    this.receiveName = receiveName;
  }

  public String getReceivetel() {
    return this.receiveTel; }

  public void setReceivetel(String receivetel) {
    this.receiveTel = receivetel; }

  public String getReceiveAddress() {
    return this.receiveAddress; }

  public void setReceiveAddress(String receiveAddress) {
    this.receiveAddress = receiveAddress; }

  public String getOstatus() {
    return this.ostatus; }

  public void setOstatus(String ostatus) {
    this.ostatus = ostatus; }

  public String getNowAddress() {
    return this.nowAddress; }

  public void setNowAddress(String nowAddress) {
    this.nowAddress = nowAddress; }

  public Date getNowTime() {
    return this.nowTime; }

  public void setNowTime(Date date) {
    this.nowTime = date; }

  public Employee getNowEmp() {
    return this.nowEmp; }

  public void setNowEmp(Employee nowEmp) {
    this.nowEmp = nowEmp;
  }
}