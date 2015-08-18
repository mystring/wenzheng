package chapter3;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleScheduler {
	static Logger logger = Logger.getLogger(ScanDirectoryJob.class);

	public static void main(String[] args) {
		SimpleScheduler simple = new SimpleScheduler();
		simple.startScheduler();
	}

	public void startScheduler() {
		Scheduler scheduler = null;

		try {
			// Get a Scheduler instance from the Factory
			scheduler = StdSchedulerFactory.getDefaultScheduler();

			// Start the scheduler
			scheduler.start();
			logger.info("Scheduler started at " + new Date());

		} catch (SchedulerException ex) {
			// deal with any exceptions
			logger.error(ex);
		}
	}

	private void modifyScheduler(Scheduler scheduler) {

		try {
			if (!scheduler.isInStandbyMode()) {
				// pause the scheduler
				scheduler.standby();
			}

			// Do something interesting here

			// and then restart it
			scheduler.start();

		} catch (SchedulerException ex) {
			logger.error(ex);
		}
	}
}
