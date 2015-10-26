package com.mystring.pojo;

public class Employee
{
  private Integer id;
  private String username;
  private String password;
  private String name;
  private Company company;
  private String role;
  private Employee superior;
  private String eaddress;
  private String status;

  public String getEaddress()
  {
    return this.eaddress; }

  public void setEaddress(String eaddress) {
    this.eaddress = eaddress; }

  public Employee getSuperior() {
    return this.superior; }

  public void setSuperior(Employee superior) {
    this.superior = superior;
  }

  public Integer getId() {
    return this.id; }

  public void setId(Integer id) {
    this.id = id; }

  public String getUsername() {
    return this.username; }

  public void setUsername(String username) {
    this.username = username; }

  public String getPassword() {
    return this.password; }

  public void setPassword(String password) {
    this.password = password; }

  public String getName() {
    return this.name; }

  public void setName(String name) {
    this.name = name; }

  public Company getCompany() {
    return this.company; }

  public void setCompany(Company company) {
    this.company = company; }

  public String getRole() {
    return this.role; }

  public void setRole(String role) {
    this.role = role; }

  public String getStatus() {
    return this.status; }

  public void setStatus(String status) {
    this.status = status;
  }
}