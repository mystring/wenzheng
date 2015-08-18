package chapter4;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

public class Listing_4_2 { 
	private static Logger LOGGEG = Logger.getLogger(chapter4.Listing_4_2.class);
	public static void main(String[] args) {
		Listing_4_2 example=new Listing_4_2();
		example.startScheduler();
	}
	public void startScheduler(){
		StdSchedulerFactory factory=new StdSchedulerFactory();
		Properties props=new Properties();
		props.put(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, "org.quartz.simpl.SimpleThreadPool");
		props.put("org.quartz.threadPool.threadCount", "10");
		try{
			factory.initialize(props);
			Scheduler scheduler=factory.getScheduler();
			scheduler.start();
		}catch(Exception e){
			LOGGEG.error(e);
		}
		
		
		
		
		
		
		
		
		
		
	}
}
