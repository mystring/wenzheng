package com.mystring.service;

import com.mystring.pojo.Company;
import com.mystring.pojo.Employee;
import com.mystring.pojo.Message;
import com.mystring.pojo.Orders;
import java.util.List;
import java.util.Map;

public abstract interface IEmployeeService
{
  public abstract Employee login(String paramString1, String paramString2);

  public abstract void addSend(Orders paramOrders, Employee paramEmployee);

  public abstract List<Orders> searchSendOrders(Company paramCompany, int paramInt1, int paramInt2);

  public abstract int getTotalSendOrders(Company paramCompany);

  public abstract List<Orders> searchHistorySendOrders(Company paramCompany, int paramInt1, int paramInt2);

  public abstract List<Orders> searchHistoryReceiveOrders(Company paramCompany, int paramInt1, int paramInt2);

  public abstract int getTotalHistorySendOrders(Company paramCompany);

  public abstract int getTotalHistoryReceiveOrders(Company paramCompany);

  public abstract Boolean addReceive(String paramString1, String paramString2, Employee paramEmployee);

  public abstract int getTotalReceiveOrders(Company paramCompany, String paramString1, String paramString2);

  public abstract List<Orders> searchReceiveOrders(Company paramCompany, int paramInt1, int paramInt2, String paramString1, String paramString2);

  public abstract void changeSendOrderStatus(String paramString1, String paramString2, Employee paramEmployee);

  public abstract void sendMsg(String paramString, Employee paramEmployee);

  public abstract List<Message> getMessages(Company paramCompany, int paramInt1, int paramInt2);

  public abstract int getMessagestotal(Company paramCompany);

  public abstract List<Map> getRecordsById(String paramString);
}