package chapter4;

import java.text.Format;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class PrintInfoJob implements Job {
	private static Logger LOGGER = Logger.getLogger(Job.class);

	public PrintInfoJob() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// Every job has tis own job detail
		Format format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JobDetail jobDetail = context.getJobDetail();
		String jobName = jobDetail.getName();
		LOGGER.info("Name: " + jobDetail.getFullName());
		LOGGER.info("job Class: " + jobDetail.getClass());
		LOGGER.info(jobName + "fired at " + format.format(context.getFireTime()));
		LOGGER.info("Next fire time " + format.format(context.getNextFireTime()));
	}

}
