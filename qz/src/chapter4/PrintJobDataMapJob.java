package chapter4;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class PrintJobDataMapJob implements Job { 
	private static Logger LOGGER = Logger.getLogger(chapter4.PrintJobDataMapJob.class);

	 public void execute(JobExecutionContext context)   
             throws JobExecutionException {   

		 LOGGER.info("in PrintJobDataMapJob");   

        // Every job has its own job detail   
        JobDataMap jobDataMap =   
                     context.getJobDetail().getJobDataMap();   

        // Iterate through the key/value pairs   
        Iterator iter = jobDataMap.keySet().iterator();   

        while (iter.hasNext()) {   
             Object key = iter.next();   
             Object value = jobDataMap.get(key);   

             LOGGER.info("Key: " + key + " - Value: " + value);   
        }   
    }   

}
