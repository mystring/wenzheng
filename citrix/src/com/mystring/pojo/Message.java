package com.mystring.pojo;

import java.util.Date;

public class Message
{
  private Integer id;
  private String oid;
  private Date time;
  private String msg;
  private String name;
  private String tel;
  private Integer cid;

  public Integer getCid()
  {
    return this.cid; }

  public void setCid(Integer cid) {
    this.cid = cid; }

  public Integer getId() {
    return this.id; }

  public void setId(Integer id) {
    this.id = id; }

  public String getOid() {
    return this.oid; }

  public void setOid(String oid) {
    this.oid = oid; }

  public Date getTime() {
    return this.time; }

  public void setTime(Date time) {
    this.time = time; }

  public String getMsg() {
    return this.msg; }

  public void setMsg(String msg) {
    this.msg = msg; }

  public String getName() {
    return this.name; }

  public void setName(String name) {
    this.name = name; }

  public String getTel() {
    return this.tel; }

  public void setTel(String tel) {
    this.tel = tel;
  }
}