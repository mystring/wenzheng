package com.mystring.controller;

import com.mystring.service.IAdminService;
import com.mystring.service.IEmployeeService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/")
public class IndexController
{
  private IAdminService adminService;
  private IEmployeeService employeeService;

  public IEmployeeService getEmployeeService()
  {
    return this.employeeService;
  }

  @Autowired
  public void setEmployeeService(IEmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  public IAdminService getAdminService() {
    return this.adminService;
  }

  @Autowired
  public void setAdminService(IAdminService adminService) {
    this.adminService = adminService;
  }

  @RequestMapping({"/index"})
  public ModelAndView index() {
    ModelAndView mv = new ModelAndView("../../index");
    List listNotice = this.adminService.getNoticeList();
    List companyList = this.adminService.findAllCompany(1, 100);
    mv.addObject("companyList", companyList);
    mv.addObject("noticeList", listNotice);
    return mv;
  }

  @RequestMapping({"/getRecords"})
  public ModelAndView getRecords(HttpServletRequest req) {
    ModelAndView mv = new ModelAndView("../../records");
    String id = req.getParameter("orderid");
    List recordes = this.employeeService.getRecordsById(id);
    mv.addObject("listRecords", recordes);
    return mv;
  }
}