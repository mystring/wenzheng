package com.mystring.service.impl;

import com.mystring.dao.IEmployeeDAO;
import com.mystring.pojo.Company;
import com.mystring.pojo.Employee;
import com.mystring.pojo.Message;
import com.mystring.pojo.Orders;
import com.mystring.pojo.Records;
import com.mystring.service.IEmployeeService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements IEmployeeService {
	private IEmployeeDAO employeeDao;

	public IEmployeeDAO getEmployeeDao() {
		return this.employeeDao;
	}

	public void setEmployeeDao(IEmployeeDAO employeeDao) {
		this.employeeDao = employeeDao;
	}

	public Employee login(String username, String password) {
		Employee emp = this.employeeDao.selectEmpByUsername(username);
		if ((emp != null) && (emp.getPassword().equals(password)))
			return emp;

		return null;
	}

	public void addSend(Orders order, Employee employee) {
		this.employeeDao.addOrders(order);
	}

	public List<Orders> searchSendOrders(Company company, int page, int rows) {
		Map map = new HashMap();
		map.put("company", company);
		map.put("status", "1");
		map.put("offset", Integer.valueOf(rows * (page - 1)));
		map.put("pagesize", Integer.valueOf(rows));
		return this.employeeDao.selectOrdersByConditons(map);
	}

	public int getTotalSendOrders(Company company) {
		Map map = new HashMap();
		map.put("company", company);
		map.put("status", "1");
		return this.employeeDao.selectTotalNumByCidAndStatus(map);
	}

	public void changeSendOrderStatus(String id, String status, Employee e) {
		Map map = new HashMap();
		if ("2".equals(status))
			map.put("sendTime", new Date());

		if ("4".equals(status))
			map.put("receiveTime", new Date());

		map.put("id", id);
		map.put("status", status);
		this.employeeDao.updateOrderStatus(map);

		Orders order = this.employeeDao.selectOrderById(id);
		if (order != null) {
			Records record = new Records();
			record.setCompany(order.getCompany());
			record.setNowAddress(e.getEaddress());
			record.setNowEmp(e);
			if ("2".equals(status))
				record.setOstatus("1");

			if ("4".equals(status))
				record.setOstatus("2");

			record.setNowTime(new Date());
			record.setOid(order.getId());
			record.setNowAddress(e.getEaddress());
			record.setReceiveName(order.getName());
			record.setReceiveAddress(order.getAddress());
			record.setReceivetel(order.getTel());
			this.employeeDao.insertRecord(record);
		}
	}

	public List<Orders> searchHistorySendOrders(Company company, int page, int rows) {
		Map map = new HashMap();
		map.put("company", company);
		map.put("status", "2");
		map.put("offset", Integer.valueOf(rows * (page - 1)));
		map.put("pagesize", Integer.valueOf(rows));
		return this.employeeDao.selectOrdersByConditons(map);
	}

	public int getTotalHistorySendOrders(Company company) {
		Map map = new HashMap();
		map.put("company", company);
		map.put("status", "2");
		int total = this.employeeDao.selectTotalNumByCidAndStatus(map);
		map.put("status", "4");

		return total;
	}

	public List<Orders> searchHistoryReceiveOrders(Company company, int page, int rows) {
		Map map = new HashMap();
		map.put("company", company);
		map.put("status", "4");
		map.put("offset", Integer.valueOf(rows * (page - 1)));
		map.put("pagesize", Integer.valueOf(rows));
		return this.employeeDao.selectOrdersByConditons(map);
	}

	public int getTotalHistoryReceiveOrders(Company company) {
		Map map = new HashMap();
		map.put("company", company);
		map.put("status", "4");
		return this.employeeDao.selectTotalNumByCidAndStatus(map);
	}

	public Boolean addReceive(String oid, String position, Employee e) {
		try {
			Map param = new HashMap();
			param.put("oid", oid);
			param.put("cid", e.getCompany().getId());
			param.put("status", "1");
			Orders order = this.employeeDao.selectOrdersByConditons1(param);
			if (order == null) {
				return Boolean.valueOf(false);
			}
			order.setPosition(position);
			order.setStatus("3");
			order.setInsertTime(new Date());
			this.employeeDao.addOrders(order);
			Records record = new Records();
			record.setCompany(e.getCompany());
			record.setNowAddress(e.getEaddress());
			record.setNowEmp(e);
			record.setOstatus("1");
			record.setNowTime(new Date());
			record.setOid(oid);
			record.setNowAddress(e.getEaddress());
			record.setReceiveName(order.getName());
			record.setReceiveAddress(order.getAddress());
			record.setReceivetel(order.getTel());
			this.employeeDao.insertRecord(record);
			return Boolean.valueOf(true);
		} catch (Exception e1) {
			return Boolean.valueOf(false);
		}
	}

	public List<Orders> searchReceiveOrders(Company company, int page, int rows, String name, String tel) {
		Map map = new HashMap();
		map.put("company", company);
		map.put("status", "3");
		map.put("offset", Integer.valueOf(rows * (page - 1)));
		map.put("pagesize", Integer.valueOf(rows));
		map.put("name", name);
		map.put("tel", tel);
		return this.employeeDao.selectOrdersByConditons(map);
	}

	public int getTotalReceiveOrders(Company company, String name, String tel) {
		Map map = new HashMap();
		map.put("company", company);
		map.put("status", "3");
		map.put("name", name);
		map.put("tel", tel);
		return this.employeeDao.selectTotalNumByCidAndStatus(map);
	}

	public void sendMsg(String id, Employee employee) {
		Orders order = this.employeeDao.selectOrderById(id);
		Message msg = new Message();
		msg.setOid(order.getId());
		msg.setName(order.getName());
		msg.setTel(order.getTel());
		msg.setTime(new Date());
		msg.setCid(employee.getCompany().getId());
		msg.setMsg(order.getName() + "您好，您的快递到了，请到东北林业大学快递联合中心" + order.getCompany().getCompanyName() + "柜台处携带有效证件领取");
		this.employeeDao.insertMsg(msg);
	}

	public List<Message> getMessages(Company company, int page, int rows) {
		Map param = new HashMap();
		param.put("cid", company.getId());
		param.put("pagesize", Integer.valueOf(rows));
		param.put("offset", Integer.valueOf((page - 1) * rows));
		return this.employeeDao.selectMessagesByCid(param);
	}

	public int getMessagestotal(Company company) {
		return this.employeeDao.selectMsgNumByCid(company.getId());
	}

	public List<Map> getRecordsById(String id) {
		return this.employeeDao.selectRecordsById(id);
	}
}