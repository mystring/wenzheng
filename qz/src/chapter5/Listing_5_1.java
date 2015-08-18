package chapter5;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import chapter4.PrintInfoJob;

public class Listing_5_1 {
	private static Logger LOGGER = Logger.getLogger(Listing_5_1.class);

	public static void main(String[] args) {
		Listing_5_1 example=new Listing_5_1();
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
			JobDetail jobDetail = new JobDetail("PrintInfoJob", Scheduler.DEFAULT_GROUP, PrintInfoJob.class);

			// Create a CronTrigger
			try {
				// CronTrigger that fires @7:30am Mon - Fri
				CronTrigger trigger = new CronTrigger("CronTrigger", null, "33 11 11 ? * MON-FRI");

				scheduler.scheduleJob(jobDetail, trigger);
			} catch (ParseException ex) {
				LOGGER.error("Error parsing cron expr", ex);

			}

		} catch (SchedulerException ex) {
			LOGGER.error(ex);
		}

	}
}
