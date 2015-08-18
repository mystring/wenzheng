package chapter4;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class Listing_4_3 {
	private static Logger LOGGEG = Logger.getLogger(chapter4.Listing_4_3.class);

	public static void main(String[] args) {
		Listing_4_3 example = new Listing_4_3();
		example.startScheduler();
	}

	public void startScheduler() {
		try {
			Scheduler schduler = StdSchedulerFactory.getDefaultScheduler();
			schduler.start();
		} catch (SchedulerException e) {
			LOGGEG.error(e);
		}

	}
}
