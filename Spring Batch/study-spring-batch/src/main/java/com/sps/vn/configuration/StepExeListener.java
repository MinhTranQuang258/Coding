/*
 * Class: StepListener
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

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class StepExeListener implements StepExecutionListener{

    @Override
    public void beforeStep(StepExecution stepExecution) {
//        System.out.println("+++++++++++++++++++ before step +++++++++++++++++++++++++");
        
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
//        System.out.println("+++++++++++++++++++ after step +++++++++++++++++++++++++");
        return null;
    }
}
