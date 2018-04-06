/*
 * Class: JonListener
 *
 * Created on Mar 5, 2018
 *
 * (c) Copyright Swiss Post Solution, unpublished work
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Swiss Post Solution.
 * Floor 4-5-8, ICT Tower, Quang Trung Software City
 */
package com.sps.vn.configuration;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobExeListener implements JobExecutionListener{

    @Override
    public void beforeJob(JobExecution jobExecution) {
//        System.out.println("Time create" + jobExecution.);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
    }

}
