package webserviceTest;

public class HelloImpl implements Hello {

	public String executeTaskList(String message) {
		return "OK|调用成功! " + message;
	}

}
