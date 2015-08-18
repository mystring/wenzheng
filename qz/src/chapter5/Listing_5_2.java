package chapter5;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import chapter4.PrintInfoJob;

public class Listing_5_2 {
	private static Logger LOGGER = Logger.getLogger(Listing_5_2.class);

	public static void main(String[] args) {
		Listing_5_2 example=new Listing_5_2();
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
				// cron that fires every min from 2 8pm
				CronTrigger trigger = new CronTrigger("MyTrigger", null, "0 * 11-20 * * ?");

				Calendar cal = Calendar.getInstance();
				// Set the date to 1 day from now
				cal.add(Calendar.DATE, 0);
				trigger.setStartTime(cal.getTime());

				// Move ahead 2 days to set the end time
				cal.add(Calendar.DATE, 2);
				trigger.setEndTime(cal.getTime());

				scheduler.scheduleJob(jobDetail, trigger);
			} catch (ParseException ex) {
				LOGGER.error("Couldn't parse cron expr", ex);
			}

		} catch (SchedulerException ex) {
			LOGGER.error(ex);
		}
	}
}
