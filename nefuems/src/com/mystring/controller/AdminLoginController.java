package com.mystring.controller;

import com.mystring.pojo.Admin;
import com.mystring.service.IAdminService;
import com.mystring.util.BaseEntity;
import com.mystring.util.StringUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/admin"})
public class AdminLoginController
{
  private BaseEntity baseEntity = new BaseEntity();
  private IAdminService adminService;

  public IAdminService getAdminService()
  {
    return this.adminService;
  }

  @Autowired
  public void setAdminService(IAdminService adminService) {
    this.adminService = adminService;
  }

  @RequestMapping(value={"/login"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String tologin(HttpSession session, HttpServletResponse response) throws IOException {
    if (session.getAttribute("admin") != null) {
      response.sendRedirect("/nefuems/admin/main.do");
      return null;
    }
    return "admin/login";
  }

  @ResponseBody
  @RequestMapping(value={"/login"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public Object doLogin(HttpServletRequest req, HttpSession session, HttpServletResponse resp)
    throws Exception
  {
    if (session.getAttribute("admin") != null)
      session.removeAttribute("admin");

    String username = req.getParameter("username");
    String password = req.getParameter("password");
    if ((!(StringUtil.isBlank(username))) && (!(StringUtil.isBlank(password)))) {
      Admin ad = new Admin();
      ad.setUsername(username);
      ad.setPassword(password);
      ad = this.adminService.login(ad, req.getRemoteAddr());
      if (ad == null) {
        this.baseEntity.setMsg("用户名或密码错误");
        this.baseEntity.setSuccess(Boolean.valueOf(false));
        return this.baseEntity;
      }
      session.setAttribute("admin", ad);
      this.baseEntity.setSuccess(Boolean.valueOf(true));
      return this.baseEntity;
    }
    this.baseEntity.setSuccess(Boolean.valueOf(false));
    this.baseEntity.setMsg("用户名或者密码为空");
    return this.baseEntity; }

  @RequestMapping({"/logout"})
  public void logout(HttpSession session, HttpServletResponse resp) throws IOException {
    session.removeAttribute("admin");
    resp.sendRedirect("/nefuems"); }

  @RequestMapping({"/main"})
  public ModelAndView doMain() {
    ModelAndView mv = new ModelAndView("admin/main");
    return mv;
  }
}