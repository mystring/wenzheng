package com.mystring.dao;

import com.mystring.pojo.Company;
import com.mystring.pojo.Employee;
import com.mystring.pojo.Message;
import com.mystring.pojo.Orders;
import com.mystring.pojo.Records;
import java.util.List;
import java.util.Map;

public abstract interface IEmployeeDAO
{
  public abstract Employee selectEmpByUsername(String paramString);

  public abstract void addOrders(Orders paramOrders);

  public abstract void selectOrders(Company paramCompany, String paramString);

  public abstract List<Orders> selectOrdersByConditons(Map paramMap);

  public abstract void updateOrderStatus(Map paramMap);

  public abstract int selectTotalNumByCidAndStatus(Map<String, Object> paramMap);

  public abstract Orders selectOrdersByConditons1(Map paramMap);

  public abstract void insertRecord(Records paramRecords);

  public abstract Orders selectOrderById(String paramString);

  public abstract void insertMsg(Message paramMessage);

  public abstract List<Message> selectMessagesByCid(Map paramMap);

  public abstract int selectMsgNumByCid(Integer paramInteger);

  public abstract List<Map> selectRecordsById(String paramString);
}