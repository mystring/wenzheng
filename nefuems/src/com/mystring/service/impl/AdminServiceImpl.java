package com.mystring.service.impl;

import com.mystring.dao.IAdminDAO;
import com.mystring.pojo.Admin;
import com.mystring.pojo.AdminOperaRecords;
import com.mystring.pojo.Company;
import com.mystring.pojo.Employee;
import com.mystring.pojo.Notice;
import com.mystring.service.IAdminService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminServiceImpl
  implements IAdminService
{
  private IAdminDAO adminDAO;

  public void setAdminDAO(IAdminDAO adminDAO)
  {
    this.adminDAO = adminDAO;
  }

  public Admin login(Admin admin, String ip)
  {
    Admin ad = this.adminDAO.selectByUsername(admin.getUsername());
    if ((ad != null) && (ad.getPassword().equals(admin.getPassword()))) {
      Admin ad1 = new Admin();
      ad1.setId(ad.getId());
      ad1.setLastLoginTime(new Date());
      ad1.setLastLoginIp(ip);
      this.adminDAO.updateLoginTimeAndIp(ad1);
      operaRecords(ad, "管理员登陆");
      return ad;
    }
    return null;
  }

  public List<Employee> findAllEmp(int currentPage, int pagesize)
  {
    Map pageMap = new HashMap();
    pageMap.put("offset", Integer.valueOf((currentPage - 1) * pagesize));
    pageMap.put("pagesize", Integer.valueOf(pagesize));
    return this.adminDAO.selectAllEmp(pageMap);
  }

  public Employee findEmpById(Integer id)
  {
    return this.adminDAO.selectEmpById(id);
  }

  public void addEmployee(Employee employee, Admin admin)
  {
    operaRecords(admin, "增加用户,工号：" + employee.getUsername());
    this.adminDAO.addEmployee(employee);
  }

  public void updateEmp(Employee employee)
  {
    Employee e = this.adminDAO.selectEmpById(employee.getId());
    if (employee.getPassword() != null)
      e.setPassword(employee.getPassword());

    this.adminDAO.updataEmp(e);
  }

  public void updateAdminPwd(Admin admin)
  {
    this.adminDAO.updateAdminPwd(admin);
    operaRecords(admin, "管理员修改密码");
  }

  public List<AdminOperaRecords> findAllOperaRecords(Admin admin, int page, int rows)
  {
    Map pageMap = new HashMap();
    pageMap.put("admin", admin);
    pageMap.put("offset", Integer.valueOf((page - 1) * rows));
    pageMap.put("pagesize", Integer.valueOf(rows));
    return this.adminDAO.findAllOperaRecords(pageMap);
  }

  public int fintOrdersNum(Admin admin)
  {
    return this.adminDAO.selectRecordNum(admin);
  }

  private void operaRecords(Admin admin, String content) {
    AdminOperaRecords record = new AdminOperaRecords();
    record.setAdminID(admin.getId());
    record.setOperaTime(new Date());
    record.setOperaAdminName(admin.getName());
    record.setOperaContent(content);
    record.setIp(admin.getLastLoginIp());
    this.adminDAO.insertRecord(record);
  }

  public void addCompany(Admin admin, Company company)
  {
    this.adminDAO.addCompany(company);
    operaRecords(admin, "增加名称为" + company.getCompanyName() + "的公司");
  }

  public List<Company> findAllCompany(int page, int rows)
  {
    Map pageMap = new HashMap();
    pageMap.put("offset", Integer.valueOf((page - 1) * rows));
    pageMap.put("pagesize", Integer.valueOf(rows));
    return this.adminDAO.findAllCompany(pageMap);
  }

  public int findCompanyNum()
  {
    return this.adminDAO.selectCompanyNum();
  }

  public void delCompany(Admin admin, String id)
  {
    this.adminDAO.delCompany(id);
    operaRecords(admin, "删除id为" + id + "的公司");
  }

  public Company detailCompany(String id)
  {
    return this.adminDAO.detailCompany(id);
  }

  public void updateCompany(Admin admin, Company company)
  {
    this.adminDAO.updateCompany(company);
    operaRecords(admin, "修改名称为" + company.getCompanyName() + "的公司");
  }

  public int findEmpNum()
  {
    return this.adminDAO.selectEmpNum();
  }

  public void delEmployee(Admin admin, String id)
  {
    this.adminDAO.delEmployee(id);
    operaRecords(admin, "删除id为" + id + "的员工");
  }

  public void addNotice(Notice notice)
  {
    this.adminDAO.insertNotice(notice);
  }

  public List<Notice> getNoticeList()
  {
    return this.adminDAO.selectNotice();
  }
}