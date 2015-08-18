package chapter4;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class Listing_4_9 {
	private static Logger LOGGER = Logger.getLogger(Listing_4_9.class);
	public static void main(String[] args) {
		Listing_4_9 example=new Listing_4_9();
		example.startScheduler();
	}
	public void startScheduler() {
		// create and the scheduler 
		try { 
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			LOGGER.info("scheduler has been started");
			JobDetail jobDetail = new JobDetail("printJob", Scheduler.DEFAULT_GROUP, PrintInfoJob.class);
			Trigger trigger = new SimpleTrigger("myTrigger", Scheduler.DEFAULT_GROUP, new Date(), null, SimpleTrigger.REPEAT_INDEFINITELY, 60000L);
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			LOGGER.error(e);
		}

	}
}
