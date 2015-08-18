package chapter4;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

public class Listing_4_5 {
	private static Logger LOGGER = Logger.getLogger(Job.class);
	public static void main(String[] args) {
		Listing_4_5 example=new Listing_4_5();
		example.runScheduler();
	}
	public void runScheduler() {

		try {

			// Create a default instance of the Scheduler
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			LOGGER.info("Scheduler starting up...");
			scheduler.start();

			// Create the JobDetail
			JobDetail jobDetail = new JobDetail("PrintInfoJob", Scheduler.DEFAULT_GROUP, PrintInfoJob.class);
			// Create a trigger that fires now and repeats forever
			Trigger trigger = TriggerUtils.makeImmediateTrigger(SimpleTrigger.REPEAT_INDEFINITELY, 10000);
			trigger.setName("PrintInfoJobTrigger");

			// register with the Scheduler
			scheduler.scheduleJob(jobDetail, trigger);

		} catch (SchedulerException ex) {
			LOGGER.error(ex);
		}
	}

}
