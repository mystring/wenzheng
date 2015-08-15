package jvm;

import java.util.ArrayList;
import java.util.List;
/**
 * VM: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath
 * Project: jvm<br/>
 * Description: <br/>
 * Created at: 2015年8月13日<br/>
 * Created by luwz3.
 * 
 * @author luwz3
 */
public class HeadOOM {
	static class OOMObject{}
	 public static void main(String[] args) {
		List<OOMObject> list=new ArrayList<HeadOOM.OOMObject>();
		while (true) {
			list.add(new OOMObject());
		}
	}

}
