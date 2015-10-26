package com.mystring.pojo;

import java.util.Date;

public class Admin
{
  private Integer id;
  private String username;
  private String password;
  private String name;
  private Date lastLoginTime;
  private String lastLoginIp;

  public Integer getId()
  {
    return this.id; }

  public void setId(Integer id) {
    this.id = id; }

  public String getUsername() {
    return this.username; }

  public void setUsername(String usrename) {
    this.username = usrename; }

  public String getPassword() {
    return this.password; }

  public void setPassword(String password) {
    this.password = password; }

  public String getName() {
    return this.name; }

  public void setName(String name) {
    this.name = name; }

  public Date getLastLoginTime() {
    return this.lastLoginTime; }

  public void setLastLoginTime(Date lastLoginTime) {
    this.lastLoginTime = lastLoginTime; }

  public String getLastLoginIp() {
    return this.lastLoginIp; }

  public void setLastLoginIp(String lastLoginIp) {
    this.lastLoginIp = lastLoginIp;
  }
}