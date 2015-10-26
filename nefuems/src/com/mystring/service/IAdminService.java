package com.mystring.service;

import com.mystring.pojo.Admin;
import com.mystring.pojo.AdminOperaRecords;
import com.mystring.pojo.Company;
import com.mystring.pojo.Employee;
import com.mystring.pojo.Notice;
import java.util.List;

public abstract interface IAdminService
{
  public abstract Admin login(Admin paramAdmin, String paramString);

  public abstract List<Employee> findAllEmp(int paramInt1, int paramInt2);

  public abstract void updateAdminPwd(Admin paramAdmin);

  public abstract Employee findEmpById(Integer paramInteger);

  public abstract void addEmployee(Employee paramEmployee, Admin paramAdmin);

  public abstract void updateEmp(Employee paramEmployee);

  public abstract void addCompany(Admin paramAdmin, Company paramCompany);

  public abstract List<AdminOperaRecords> findAllOperaRecords(Admin paramAdmin, int paramInt1, int paramInt2);

  public abstract int fintOrdersNum(Admin paramAdmin);

  public abstract List<Company> findAllCompany(int paramInt1, int paramInt2);

  public abstract int findCompanyNum();

  public abstract void delCompany(Admin paramAdmin, String paramString);

  public abstract Company detailCompany(String paramString);

  public abstract void updateCompany(Admin paramAdmin, Company paramCompany);

  public abstract int findEmpNum();

  public abstract void delEmployee(Admin paramAdmin, String paramString);

  public abstract void addNotice(Notice paramNotice);

  public abstract List<Notice> getNoticeList();
}