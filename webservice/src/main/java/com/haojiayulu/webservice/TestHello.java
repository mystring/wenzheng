package com.haojiayulu.webservice;

import java.net.URL;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.junit.Test;

public class TestHello {
	@Test
	public void testExecuteTask() throws Exception {
		String res = "";

		String ss = "ERR|2352|5683|2235|8428";
		String[] str = ss.split("\\|");
		String tempMsg = "";
		for (int i = 1; i < str.length; i++) {
			tempMsg += str[i] + ",";
		}
		tempMsg = tempMsg.substring(0, tempMsg.length() - 1);

		// 调接�?
		res = this.executeTask("http://localhost:8080/webservice/services/executeTask?wsdl", "executeTaskList", new Object[] { tempMsg });

		System.out.println(res); // 输出结果
	}

	// 核心处理方法
	private synchronized String executeTask(String url, String method, Object[] args) throws Exception {

		// 创建Service实例
		Service service = new Service();

		// 通过Service实例创建Call实例
		Call call = (Call) service.createCall();

		// 将WebService的服务路径加入到Call实例中，并为Call设置服务的位�?
		URL webServiceUrl = new URL(url);
		call.setTargetEndpointAddress(webServiceUrl);

		// 调用WebService方法
		call.setOperationName(method);

		// 调用WebService传入参数
		String result = (String) call.invoke(args);

		return result;
	}
}
