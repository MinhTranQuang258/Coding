/*
 * Class: ExtractDataJob
 *
 * Created on Mar 2, 2018
 *
 * (c) Copyright Swiss Post Solution, unpublished work
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Swiss Post Solution.
 * Floor 4-5-8, ICT Tower, Quang Trung Software City
 */
package com.sps.vn.jobs.data_extraction;


import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sps.vn.model.Data;

@Configuration
public class DataExtractionJob{
    
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    @Bean("extractionData")
    protected Job extractionData(@Qualifier("chunk")Step step, JobExecutionListener jobListener) {
        return jobBuilderFactory.get("extractionData")
                .start(step)
                .listener(jobListener)
                .build();
    }
    
    @Bean("extractionData2")
    protected Job extractionData2(@Qualifier("chunk")Step step, JobExecutionListener jobListener) {
        return jobBuilderFactory.get("extractionData")
                .start(step)
                .listener(jobListener)
                .build();
    }
    
    @Bean("extractionData3")
    protected Job extractionData3(@Qualifier("chunk")Step step, JobExecutionListener jobListener) {
        return jobBuilderFactory.get("extractionData")
                .start(step)
                .listener(jobListener)
                .build();
    }
    
    
    @Bean("chunk")
    protected Step stepChunk(ItemReader<List<Data>> reader,ItemProcessor<List<Data>, List<Data>> processor,
        ItemWriter<List<Data>> writer, StepExecutionListener listener) {
        return stepBuilderFactory.get("stepChunk")
                .listener(listener)
                .<List<Data>, List<Data>>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
    
    @Bean("tasklet")
    protected Step stepTasklet(Tasklet myTasklet) {
        return stepBuilderFactory.get("stepTasklet")
                .tasklet(myTasklet)
                .build();
    }
}
