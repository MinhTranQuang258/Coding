/*
 * Class: JobExecution
 *
 * Created on Mar 21, 2018
 *
 * (c) Copyright Swiss Post Solution, unpublished work
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Swiss Post Solution.
 * Floor 4-5-8, ICT Tower, Quang Trung Software City
 */
package com.sps.vn.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobExecutions {
    
    @Autowired
    private JobLauncher jobLauncher;
    
    @Autowired
    private Job extractionData;
    
    @Autowired
    private Job extractionData2;
    
    @Autowired
    private Job extractionData3;

    @Scheduled(cron= "0/30 * * * * *")
    public void runJob() throws Exception{
        try {
            JobParameters jobParameters= new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            
            jobLauncher.run(extractionData, jobParameters);
        }
        catch (Exception e) {
            throw e;
        }
    }
    
    @Scheduled(cron= "0/30 * * * * *")
    public void runJob2() throws Exception{
        try {
            JobParameters jobParameters= new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            
            jobLauncher.run(extractionData2, jobParameters);
        }
        catch (Exception e) {
            throw e;
        }
    }
    
    @Scheduled(cron= "0/30 * * * * *")
    public void runJob3() throws Exception{
        try {
            JobParameters jobParameters= new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            
            jobLauncher.run(extractionData3, jobParameters);
        }
        catch (Exception e) {
            throw e;
        }
    }
}
