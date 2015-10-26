package com.mystring.filter;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class PerssionFilter
  implements Filter
{
  private static final Logger logger = Logger.getLogger(PerssionFilter.class);

  public void init(FilterConfig filterConfig)
    throws ServletException
  {
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException
  {
    HttpServletRequest req = (HttpServletRequest)request;
    HttpServletResponse resp = (HttpServletResponse)response;

    //logger.info("<====================================>" + JSON.toJSON(req.getParameterMap()));

    if (req.getRequestURI().equals("/nefuems/admin/login.do")) {
      chain.doFilter(request, response);
      return;
    }
    if (req.getRequestURI().equals("/nefuems/employee/login.do")) {
      chain.doFilter(request, response);
      return;
    }

    if ((req.getRequestURI().startsWith("/nefuems/admin")) && 
      (req.getSession().getAttribute("admin") == null)) {
      resp.sendRedirect("/nefuems");
      return;
    }

    if ((req.getRequestURI().startsWith("/nefuems/employee")) && 
      (req.getSession().getAttribute("employee") == null)) {
      resp.sendRedirect("/nefuems");
      return;
    }

    chain.doFilter(request, response);
  }

  public void destroy()
  {
  }
}