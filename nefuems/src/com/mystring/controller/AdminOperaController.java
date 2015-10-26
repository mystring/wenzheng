package com.mystring.controller;

import com.mystring.pojo.Admin;
import com.mystring.pojo.Company;
import com.mystring.pojo.Employee;
import com.mystring.pojo.Notice;
import com.mystring.service.IAdminService;
import com.mystring.util.BaseEntity;
import com.mystring.util.StringUtil;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/admin"})
public class AdminOperaController
{
  private BaseEntity baseEntity;
  private IAdminService adminService;

  public IAdminService getAdminService()
  {
    return this.adminService;
  }

  @Autowired
  public void setAdminService(IAdminService adminService) {
    this.adminService = adminService;
  }

  @RequestMapping(value={"/show/{firstPath}/{secondPath}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String show(@PathVariable String firstPath, @PathVariable String secondPath)
  {
    return "admin/" + firstPath + "/" + secondPath;
  }

  @RequestMapping(value={"/show/companyEmpManger/employeeManager"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView show() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("admin/companyEmpManger/employeeManager");
    List companys = this.adminService.findAllCompany(1, 10000);
    mv.addObject("companys", companys);
    return mv;
  }

  @ResponseBody
  @RequestMapping(value={"/updatePwd"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public BaseEntity updatePwd(HttpServletRequest req, HttpSession session)
  {
    this.baseEntity = new BaseEntity();
    String prePwd = req.getParameter("prePwd");
    String newPwd = req.getParameter("newPwd");
    Admin admin = (Admin)session.getAttribute("admin");
    if ((!(StringUtil.isBlank(newPwd))) && (!(StringUtil.isBlank(newPwd))) && (admin != null) && (admin.getPassword().equals(prePwd))) {
      admin.setPassword(newPwd);
      this.adminService.updateAdminPwd(admin);
      session.setAttribute("admin", admin);
      this.baseEntity.setSuccess(Boolean.valueOf(true));
      this.baseEntity.setMsg("密码修改成功");
      return this.baseEntity;
    }
    this.baseEntity.setSuccess(Boolean.valueOf(false));
    this.baseEntity.setMsg("旧密码错误，密码修改失败！");
    return this.baseEntity;
  }

  @ResponseBody
  @RequestMapping({"/searchOperaRecords"})
  public BaseEntity searchOperaRecords(HttpSession session, HttpServletRequest req) {
    this.baseEntity = new BaseEntity();
    int page = (req.getParameter("page") == null) ? 1 : Integer.parseInt(req.getParameter("page"));
    int rows = (req.getParameter("rows") == null) ? 10 : Integer.parseInt(req.getParameter("rows"));
    Admin admin = (Admin)session.getAttribute("admin");
    if (admin != null) {
      List records = this.adminService.findAllOperaRecords(admin, page, rows);
      int total = this.adminService.fintOrdersNum(admin);
      this.baseEntity.setTotal(total);
      this.baseEntity.setRows(records);
    }
    return this.baseEntity;
  }

  @ResponseBody
  @RequestMapping({"/searchCompany"})
  public BaseEntity searchCompany(HttpSession session, HttpServletRequest req) {
    this.baseEntity = new BaseEntity();
    int page = (req.getParameter("page") == null) ? 1 : Integer.parseInt(req.getParameter("page"));
    int rows = (req.getParameter("rows") == null) ? 10 : Integer.parseInt(req.getParameter("rows"));
    List companys = this.adminService.findAllCompany(page, rows);
    int total = this.adminService.findCompanyNum();
    this.baseEntity.setTotal(total);
    this.baseEntity.setRows(companys);

    return this.baseEntity;
  }

  @ResponseBody
  @RequestMapping({"/addCompany"})
  public BaseEntity addCompany(HttpServletRequest req, HttpSession session) {
    this.baseEntity = new BaseEntity();
    String companyName = req.getParameter("companyName");
    String address = req.getParameter("address");
    if (!(StringUtil.isBlank(companyName))) {
      Company company = new Company();
      company.setAddress(address);
      company.setCompanyName(companyName);
      company.setCreateTime(new Date());
      this.adminService.addCompany((Admin)session.getAttribute("admin"), company);
      this.baseEntity.setSuccess(Boolean.valueOf(true));
      this.baseEntity.setMsg("添加成功");
      return this.baseEntity;
    }
    this.baseEntity.setSuccess(Boolean.valueOf(false));
    this.baseEntity.setMsg("添加失败");
    return this.baseEntity;
  }

  @ResponseBody
  @RequestMapping({"/delCompany"})
  public BaseEntity delCompany(HttpServletRequest req, HttpSession session) {
    this.baseEntity = new BaseEntity();
    String id = req.getParameter("id");
    if (!(StringUtil.isBlank(id))) {
      this.adminService.delCompany((Admin)session.getAttribute("admin"), id);
      this.baseEntity.setSuccess(Boolean.valueOf(true));
      this.baseEntity.setMsg("删除成功");
      return this.baseEntity;
    }
    this.baseEntity.setSuccess(Boolean.valueOf(false));
    this.baseEntity.setMsg("删除失败");
    return this.baseEntity;
  }

  @ResponseBody
  @RequestMapping({"/detailCompany"})
  public BaseEntity detailCompany(HttpSession session, HttpServletRequest req) {
    String id = req.getParameter("id");
    this.baseEntity = new BaseEntity();
    Company company = this.adminService.detailCompany(id);
    this.baseEntity.setObj(company);
    return this.baseEntity;
  }

  @ResponseBody
  @RequestMapping({"/updateCompany"})
  public BaseEntity updateCompany(HttpServletRequest req, HttpSession session) {
    this.baseEntity = new BaseEntity();
    String id = req.getParameter("id");
    String companyName = req.getParameter("companyName");
    String address = req.getParameter("address");
    if (!(StringUtil.isBlank(companyName))) {
      Company company = new Company();
      company.setId(Integer.valueOf(Integer.parseInt(id)));
      company.setAddress(address);
      company.setCompanyName(companyName);
      company.setCreateTime(new Date());
      this.adminService.updateCompany((Admin)session.getAttribute("admin"), company);
      this.baseEntity.setSuccess(Boolean.valueOf(true));
      this.baseEntity.setMsg("修改成功");
      return this.baseEntity;
    }
    this.baseEntity.setSuccess(Boolean.valueOf(false));
    this.baseEntity.setMsg("修改失败");
    return this.baseEntity;
  }

  @ResponseBody
  @RequestMapping({"/addEmployee"})
  public BaseEntity addEmployee(HttpServletRequest req, HttpSession session) {
    this.baseEntity = new BaseEntity();
    Employee employee = new Employee();
    employee.setName(req.getParameter("name"));
    employee.setPassword(req.getParameter("password"));
    employee.setUsername(req.getParameter("username"));
    employee.setRole(req.getParameter("role"));
    Company company = new Company();
    company.setId(Integer.valueOf(Integer.parseInt(req.getParameter("company"))));
    employee.setCompany(company);
    this.adminService.addEmployee(employee, (Admin)session.getAttribute("admin"));
    this.baseEntity.setSuccess(Boolean.valueOf(true));
    this.baseEntity.setMsg("添加成功");
    return this.baseEntity;
  }

  @ResponseBody
  @RequestMapping({"/searchEmployee"})
  public BaseEntity searchEmployee(HttpServletRequest req, HttpSession session) {
    this.baseEntity = new BaseEntity();
    int page = (req.getParameter("page") == null) ? 1 : Integer.parseInt(req.getParameter("page"));
    int rows = (req.getParameter("rows") == null) ? 10 : Integer.parseInt(req.getParameter("rows"));
    List emps = this.adminService.findAllEmp(page, rows);
    int total = this.adminService.findEmpNum();
    this.baseEntity.setTotal(total);
    this.baseEntity.setRows(emps);

    return this.baseEntity;
  }

  @ResponseBody
  @RequestMapping({"/delEmployee"})
  public BaseEntity delEmployee(HttpServletRequest req, HttpSession session) {
    this.baseEntity = new BaseEntity();
    String id = req.getParameter("id");
    if (!(StringUtil.isBlank(id))) {
      this.adminService.delEmployee((Admin)session.getAttribute("admin"), id);
      this.baseEntity.setSuccess(Boolean.valueOf(true));
      this.baseEntity.setMsg("删除成功");
      return this.baseEntity;
    }
    this.baseEntity.setSuccess(Boolean.valueOf(false));
    this.baseEntity.setMsg("删除失败");
    return this.baseEntity;
  }

  @ResponseBody
  @RequestMapping({"/addNotice"})
  public BaseEntity addNotice(HttpServletRequest req, HttpSession session) {
    this.baseEntity = new BaseEntity();
    String title = req.getParameter("title");
    String content = req.getParameter("content");
    if (!(StringUtil.isBlank(title))) {
      Notice notice = new Notice();
      notice.setTime(new Date());
      notice.setTitle(title);
      notice.setContent(content);
      this.adminService.addNotice(notice);
      this.baseEntity.setSuccess(Boolean.valueOf(true));
      this.baseEntity.setMsg("添加成功");
      return this.baseEntity;
    }
    this.baseEntity.setSuccess(Boolean.valueOf(false));
    this.baseEntity.setMsg("添加失败");
    return this.baseEntity;
  }
}