package chapter4;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.DirectSchedulerFactory;

public class Listing_4_1 {
	private static Logger LOGGEG = Logger.getLogger(chapter4.Listing_4_1.class);
	
	public static void main(String[] args) {
		Listing_4_1 example=new Listing_4_1();
		example.startScheduler();
	}
	/**
	 * start a scheduler job
	 */
	public void startScheduler() {
		DirectSchedulerFactory factory = DirectSchedulerFactory.getInstance();

		try {
			//factory.createVolatileSchduler(10);
 			Scheduler scheduler = factory.getScheduler();
			LOGGEG.info("Scheduler starting up....");
			scheduler.start();
		} catch (SchedulerException e) {
			LOGGEG.error(e);
		}

	}
}
