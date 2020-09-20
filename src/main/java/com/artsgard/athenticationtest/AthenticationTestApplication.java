package com.artsgard.athenticationtest;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author artsgard
 */
@SpringBootApplication
//@ComponentScan(basePackages = "com.artsgard.athenticationtest")
public class AthenticationTestApplication implements ApplicationRunner {
    
    private static final Logger log = LoggerFactory.getLogger(AthenticationTestApplication.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("authentiaction-test-job")
    private Job job;
    
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) throws JSONException {
        SpringApplication.run(AthenticationTestApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("authentiaction-test-date", new Date())
                .toJobParameters();
        jobLauncher.run(job, jobParameters);
        log.info("Time of \"authentiaction-test start", dateFormat.format(new Date()));
        
    }
}

