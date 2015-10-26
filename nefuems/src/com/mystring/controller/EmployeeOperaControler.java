package com.mystring.controller;

import com.mystring.pojo.Employee;
import com.mystring.pojo.Orders;
import com.mystring.service.IEmployeeService;
import com.mystring.util.BaseEntity;
import com.mystring.util.StringUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({ "/employee" })
public class EmployeeOperaControler {
	private BaseEntity baseEntity;
	private IEmployeeService employeeService;

	public IEmployeeService getEmployeeService() {
		return this.employeeService;
	}

	@Autowired
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(value = { "/show/{firstPath}/{secondPath}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String show(@PathVariable String firstPath, @PathVariable String secondPath) {
		return "employee/" + firstPath + "/" + secondPath;
	}

	@ResponseBody
	@RequestMapping({ "/login" })
	public Object login(HttpServletRequest req, HttpSession session) {
		if (session.getAttribute("employee") != null)
			session.removeAttribute("employee");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		this.baseEntity = new BaseEntity();
		if ((!(StringUtil.isBlank(username))) && (!(StringUtil.isBlank(password)))) {
			Employee emp = this.employeeService.login(username, password);
			if (emp == null) {
				this.baseEntity.setMsg("用户名或密码错误");
				this.baseEntity.setSuccess(Boolean.valueOf(false));
				return this.baseEntity;
			}
			session.setAttribute("employee", emp);
			this.baseEntity.setSuccess(Boolean.valueOf(true));
			this.baseEntity.setMsg("登录成功");
			return this.baseEntity;
		}
		this.baseEntity.setSuccess(Boolean.valueOf(false));
		this.baseEntity.setMsg("用户名或者密码为空");
		return this.baseEntity;
	}

	@RequestMapping({ "/main" })
	public ModelAndView doMain() {
		ModelAndView mv = new ModelAndView("employee/main");
		return mv;
	}

	@RequestMapping({ "/logout" })
	public void logout(HttpSession session, HttpServletResponse resp) throws IOException {
		session.removeAttribute("employee");
		resp.sendRedirect("/nefuems");
	}

	@RequestMapping({ "/addSend" })
	@ResponseBody
	public Object addSend(HttpServletRequest req, HttpSession session) {
		this.baseEntity = new BaseEntity();
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
		String address = req.getParameter("address");
		Employee employee = (Employee) session.getAttribute("employee");
		Orders order = new Orders();
		order.setAddress(address);
		order.setId(id);
		order.setName(name);
		order.setTel(tel);
		order.setCompany(employee.getCompany());
		order.setInsertTime(new Date());
		order.setStatus("1");
		try {
			this.employeeService.addSend(order, employee);
			this.baseEntity.setSuccess(Boolean.valueOf(true));
			this.baseEntity.setMsg("添加成功");
		} catch (Exception e) {
			this.baseEntity.setSuccess(Boolean.valueOf(false));
			this.baseEntity.setMsg("添加失败");
		}
		return this.baseEntity;
	}

	@ResponseBody
	@RequestMapping({ "/searchSendOrders" })
	public Object searchSendOrders(HttpServletRequest req, HttpSession session) {
		this.baseEntity = new BaseEntity();
		Employee employee = (Employee) session.getAttribute("employee");
		int page = (req.getParameter("page") == null) ? 1 : Integer.parseInt(req.getParameter("page"));
		int rows = (req.getParameter("rows") == null) ? 10 : Integer.parseInt(req.getParameter("rows"));
		List orders = this.employeeService.searchSendOrders(employee.getCompany(), page, rows);
		int total = this.employeeService.getTotalSendOrders(employee.getCompany());
		this.baseEntity.setTotal(total);
		this.baseEntity.setRows(orders);
		return this.baseEntity;
	}

	@ResponseBody
	@RequestMapping({ "/searchHistorySendOrders" })
	public Object searchHistorySendOrders(HttpServletRequest req, HttpSession session) {
		this.baseEntity = new BaseEntity();
		Employee employee = (Employee) session.getAttribute("employee");
		int page = (req.getParameter("page") == null) ? 1 : Integer.parseInt(req.getParameter("page"));
		int rows = (req.getParameter("rows") == null) ? 10 : Integer.parseInt(req.getParameter("rows"));
		List orders = this.employeeService.searchHistorySendOrders(employee.getCompany(), page, rows);
		int total = this.employeeService.getTotalHistorySendOrders(employee.getCompany());
		this.baseEntity.setTotal(total);
		this.baseEntity.setRows(orders);
		return this.baseEntity;
	}

	@ResponseBody
	@RequestMapping({ "/searchHistoryReceiveOrders" })
	public Object searchHistoryReceiveOrders(HttpServletRequest req, HttpSession session) {
		this.baseEntity = new BaseEntity();
		Employee employee = (Employee) session.getAttribute("employee");
		int page = (req.getParameter("page") == null) ? 1 : Integer.parseInt(req.getParameter("page"));
		int rows = (req.getParameter("rows") == null) ? 10 : Integer.parseInt(req.getParameter("rows"));
		List orders = this.employeeService.searchHistoryReceiveOrders(employee.getCompany(), page, rows);
		int total = this.employeeService.getTotalHistoryReceiveOrders(employee.getCompany());
		this.baseEntity.setTotal(total);
		this.baseEntity.setRows(orders);
		return this.baseEntity;
	}

	@ResponseBody
	@RequestMapping({ "/changeStatus" })
	public Object changeStatus(HttpServletRequest req, HttpSession session) {
		this.baseEntity = new BaseEntity();
		Employee employee = (Employee) session.getAttribute("employee");
		String id = req.getParameter("id");
		String status = req.getParameter("status");
		try {
			this.employeeService.changeSendOrderStatus(id, status, employee);
			this.baseEntity.setSuccess(Boolean.valueOf(true));
			this.baseEntity.setMsg("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.baseEntity.setSuccess(Boolean.valueOf(false));
			this.baseEntity.setMsg("操作失败");
		}
		return this.baseEntity;
	}

	@ResponseBody
	@RequestMapping({ "/receive" })
	public Object receive(HttpServletRequest req, HttpSession session) {
		this.baseEntity = new BaseEntity();
		String oid = req.getParameter("oid");
		String position = req.getParameter("position");
		Employee employee = (Employee) session.getAttribute("employee");
		try {
			Boolean flag = this.employeeService.addReceive(oid, position, employee);
			if (!(flag.booleanValue())) {
				return this.baseEntity;
			}
			this.baseEntity.setSuccess(Boolean.valueOf(true));
			this.baseEntity.setMsg("添加成功");
		} catch (Exception flag) {
			this.baseEntity.setSuccess(Boolean.valueOf(false));
			this.baseEntity.setMsg("添加失败");
		}
		return this.baseEntity;
	}

	@RequestMapping({ "/getPositions" })
	@ResponseBody
	public Object getPositions() {
		this.baseEntity = new BaseEntity();
		String s = "[{id: 1,text: 'Languages',children: [{id: 11,text: 'Java'},{id: 12,text: 'C++'}]}]";
		return s;
	}

	@RequestMapping({ "/searchReceiveOrders" })
	@ResponseBody
	public Object searchReceiveOrders(HttpServletRequest req, HttpSession session) {
		this.baseEntity = new BaseEntity();

		Employee employee = (Employee) session.getAttribute("employee");
		int page = (req.getParameter("page") == null) ? 1 : Integer.parseInt(req.getParameter("page"));
		int rows = (req.getParameter("rows") == null) ? 10 : Integer.parseInt(req.getParameter("rows"));
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
		List orders = this.employeeService.searchReceiveOrders(employee.getCompany(), page, rows, name, tel);
		int total = this.employeeService.getTotalReceiveOrders(employee.getCompany(), name, tel);
		this.baseEntity.setTotal(total);
		this.baseEntity.setRows(orders);
		return this.baseEntity;
	}

	@RequestMapping({ "/sendMsg" })
	@ResponseBody
	public Object sendMsg(HttpServletRequest req, HttpSession session) {
		this.baseEntity = new BaseEntity();
		Employee employee = (Employee) session.getAttribute("employee");
		String id = req.getParameter("id");
		try {
			this.employeeService.sendMsg(id, employee);
			this.baseEntity.setSuccess(Boolean.valueOf(true));
			this.baseEntity.setMsg("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.baseEntity.setSuccess(Boolean.valueOf(false));
			this.baseEntity.setMsg("操作失败");
		}
		return this.baseEntity;
	}

	@RequestMapping({ "/batchSendMsg" })
	@ResponseBody
	public Object batchSendMsg(HttpServletRequest req, HttpSession session) {
		this.baseEntity = new BaseEntity();
		Employee employee = (Employee) session.getAttribute("employee");
		String ids = req.getParameter("ids");
		String[] _ids = ids.split(",");
		try {
			for (String id : _ids)
				this.employeeService.sendMsg(id, employee);

			this.baseEntity.setSuccess(Boolean.valueOf(true));
			this.baseEntity.setMsg("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.baseEntity.setSuccess(Boolean.valueOf(false));
			this.baseEntity.setMsg("操作失败");
		}
		return this.baseEntity;
	}

	@ResponseBody
	@RequestMapping({ "/getMsg" })
	public Object getMsg(HttpServletRequest req, HttpSession session) {
		this.baseEntity = new BaseEntity();
		Employee employee = (Employee) session.getAttribute("employee");
		int page = (req.getParameter("page") == null) ? 1 : Integer.parseInt(req.getParameter("page"));
		int rows = (req.getParameter("rows") == null) ? 10 : Integer.parseInt(req.getParameter("rows"));

		List msgs = this.employeeService.getMessages(employee.getCompany(), page, rows);

		int total = this.employeeService.getMessagestotal(employee.getCompany());
		this.baseEntity.setSuccess(Boolean.valueOf(true));
		this.baseEntity.setRows(msgs);
		this.baseEntity.setTotal(total);
		return this.baseEntity;
	}
}