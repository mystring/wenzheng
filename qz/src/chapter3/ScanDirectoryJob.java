package chapter3;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

 
public class ScanDirectoryJob implements Job {       
    static Logger logger =Logger.getLogger(ScanDirectoryJob.class);
    
     
    public void execute(JobExecutionContext context)       
              throws JobExecutionException {       
     
         // Every job has its own job detail       
         JobDetail jobDetail = context.getJobDetail();       
     
         // The name is defined in the job definition       
      //   String jobName = jobDetail.getName();       
     
         // Log the time the job started       
        // logger.info(jobName + " fired at " + new Date());       
     
         // The directory to scan is stored in the job map       
         JobDataMap dataMap = jobDetail.getJobDataMap();       
                   String dirName = dataMap.getString("SCAN_DIR");       
     
         // Validate the required input       
         if (dirName == null) {       
              throw new JobExecutionException( "Directory not configured" );       
         }       
     
         // Make sure the directory exists       
         File dir = new File(dirName);       
         if (!dir.exists()) {       
          throw new JobExecutionException( "Invalid Dir "+ dirName);       
         }       
     
         // Use FileFilter to get only XML files       
         FileFilter filter = new FileExtensionFileFilter(".java");       
     
         File[] files = dir.listFiles(filter);       
     
         if (files == null || files.length <= 0) {       
              logger.info("No java files found in " + dir);       
     
              // Return since there were no files       
              return;       
         }       
     
         // The number of XML files       
         int size = files.length;       
     
         // Iterate through the files found       
         for (int i = 0; i < size; i++) {       
     
              File file = files[i];       
     
              // Log something interesting about each file.       
              File aFile = file.getAbsoluteFile();       
              long fileSize = file.length();       
              String msg = aFile + " - Size: " + fileSize;       
              logger.info(msg);       
         }       
    }       
}   

