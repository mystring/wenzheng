package com.mystring.dao;

import com.mystring.pojo.Admin;
import com.mystring.pojo.AdminOperaRecords;
import com.mystring.pojo.Company;
import com.mystring.pojo.Employee;
import com.mystring.pojo.Notice;
import java.util.List;
import java.util.Map;

public abstract interface IAdminDAO
{
  public abstract Admin selectByUsername(String paramString);

  public abstract void updateLoginTimeAndIp(Admin paramAdmin);

  public abstract void insertRecord(AdminOperaRecords paramAdminOperaRecords);

  public abstract void updateAdminPwd(Admin paramAdmin);

  public abstract List<AdminOperaRecords> findAllOperaRecords(Map<String, Object> paramMap);

  public abstract int selectRecordNum(Admin paramAdmin);

  public abstract Employee selectEmpById(Integer paramInteger);

  public abstract void addEmployee(Employee paramEmployee);

  public abstract void delEmployee(String paramString);

  public abstract void updataEmp(Employee paramEmployee);

  public abstract List<Employee> selectAllEmp(Map<String, Integer> paramMap);

  public abstract void addCompany(Company paramCompany);

  public abstract List<Company> findAllCompany(Map<String, Object> paramMap);

  public abstract int selectCompanyNum();

  public abstract void delCompany(String paramString);

  public abstract Company detailCompany(String paramString);

  public abstract void updateCompany(Company paramCompany);

  public abstract int selectEmpNum();

  public abstract void insertNotice(Notice paramNotice);

  public abstract List<Notice> selectNotice();
}