package com.mystring.util;

import java.util.List;

public class BaseEntity
{
  private Boolean success;
  private String msg;
  private Object obj;
  private List<?> rows;
  private int total;

  public int getTotal()
  {
    return this.total; }

  public void setTotal(int total) {
    this.total = total; }

  public Boolean getSuccess() {
    return this.success; }

  public void setSuccess(Boolean success) {
    this.success = success; }

  public String getMsg() {
    return this.msg; }

  public void setMsg(String msg) {
    this.msg = msg; }

  public Object getObj() {
    return this.obj; }

  public void setObj(Object obj) {
    this.obj = obj; }

  public List<?> getRows() {
    return this.rows; }

  public void setRows(List<?> rows) {
    this.rows = rows;
  }
}