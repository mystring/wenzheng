package chapter3;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

public class Listing_3_5 {
	static Logger logger = Logger.getLogger(Listing_3_5.class);

	public static void main(String[] args) {
		Listing_3_5 example = new Listing_3_5();
		logger.info("fddfd");
		try {
			// Create a Scheduler and schedule the Job
			Scheduler scheduler = example.createScheduler();
			example.scheduleJob(scheduler);

			// Start the Scheduler running
			scheduler.start();

			logger.info("Scheduler started at " + new Date());

		} catch (SchedulerException ex) {
			logger.error(ex);
		}
	} /*
	 * return an instance of the Scheduler from the factory
	 */

	public Scheduler createScheduler() throws SchedulerException {
		return StdSchedulerFactory.getDefaultScheduler();
	}

	// Create and Schedule a ScanDirectoryJob with the Scheduler
	private void scheduleJob(Scheduler scheduler) throws SchedulerException {

		// Create a JobDetail for the Job
		JobDetail jobDetail = new JobDetail("ScanDirectory", Scheduler.DEFAULT_GROUP, ScanDirectoryJob.class);

		// Configure the directory to scan
		jobDetail.getJobDataMap().put("SCAN_DIR", "E:\\workspace\\chapter3\\src\\chapter3");

		// Create a trigger that fires every 10 seconds, forever
		Trigger trigger = TriggerUtils.makeSecondlyTrigger(10);
		trigger.setName("scanTrigger");
		// Start the trigger firing from now
		trigger.setStartTime(new Date());

		// Associate the trigger with the job in the scheduler
		scheduler.scheduleJob(jobDetail, trigger);
	}

}
