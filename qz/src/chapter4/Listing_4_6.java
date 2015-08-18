package chapter4;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

public class Listing_4_6 {
	private static Logger LOGGER = Logger.getLogger(Listing_4_6.class);

	public static void main(String[] args) {
		Listing_4_6 example = new Listing_4_6();
		example.runScheduler();
	}

	public void runScheduler() {
		Scheduler scheduler = null;

		try {  
			// Create a default instance of the Scheduler
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
 			LOGGER.info("Scheduler was started at " + new Date());

			// Create the JobDetail
			JobDetail jobDetail = new JobDetail("PrintJobDataMapJob", Scheduler.DEFAULT_GROUP, PrintJobDataMapJob.class);
 			// Store some state for the Job
			jobDetail.getJobDataMap().put("name", "John Doe");
			jobDetail.getJobDataMap().put("age", 23);
			jobDetail.getJobDataMap().put("balance", new BigDecimal(1200.37));
		  	// Create a trigger that fires once     
			Trigger trigger = TriggerUtils.makeImmediateTrigger(SimpleTrigger.REPEAT_INDEFINITELY, 10000);
			trigger.setName("PrintJobDataMapJobTrigger");

			scheduler.scheduleJob(jobDetail, trigger);

		} catch (SchedulerException ex) {
			LOGGER.error(ex);
		}
	}

}
