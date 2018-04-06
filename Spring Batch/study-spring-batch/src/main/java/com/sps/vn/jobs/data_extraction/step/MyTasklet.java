/*
 * Class: SucessfulTasklet
 *
 * Created on Mar 6, 2018
 *
 * (c) Copyright Swiss Post Solution, unpublished work
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Swiss Post Solution.
 * Floor 4-5-8, ICT Tower, Quang Trung Software City
 */
package com.sps.vn.jobs.data_extraction.step;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sps.vn.model.Data;

@Component
@Qualifier("stepTasklet")
public class MyTasklet implements Tasklet{
    
    private int count= 0;
    
    private static final String PATCH = "/Users/tqminh_1/Desktop/Read/";

    @Override
    public RepeatStatus execute(
        StepContribution contribution,
        ChunkContext chunkContext) throws Exception {
        
        final File folder = new File(PATCH);
        final File[] files = folder.listFiles();

        if(count < files.length) {
            File file= files[count];
            count++;
            final String content = readFile(file);
            List<Data> dataList= extract(content);
            printReport(dataList);
        }
        else {
            count = 0;
        }
        return RepeatStatus.FINISHED;
    }
    
    private static Pattern pattern = Pattern
            .compile("\\s(\\w+-\\w+-\\w+-\\w+-\\w+)\\s\\w+\\s\\w+\\s(\\d+)");

    private List<Data> extract(final String content) {
            final List<Data> list = new ArrayList<>();

            final Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                final int duration = Integer.valueOf(matcher.group(2));
                list.add(new Data(matcher.group(1), duration));
            }
            return list;
        }
    
    private static String readFile(final File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }
    
    private void printReport(final List<? extends Data> items) {
        int average = 0;
        final double maxTime = Collections.max(items, (o1, o2) -> {
            if (o1.getDuration() == o2.getDuration()) {
                return 0;
            }
            else if (o1.getDuration() < o2.getDuration()) {
                return -1;
            }
            else {
                return 1;
            }
        }).getDuration();

        final double minTime = Collections.min(items, (o1, o2) -> {
            if (o1.getDuration() == o2.getDuration()) {
                return 0;
            }
            else if (o1.getDuration() < o2.getDuration()) {
                return -1;
            }
            else {
                return 1;
            }
        }).getDuration();
        
        for (final Data requestResult : items) {
            average += requestResult.getDuration();
            final String result = String.format(
                "card: %s done in %.2f",
                requestResult.getCardName(),
                requestResult.getDuration());
            System.out.println(result);
        }
        
        System.out.println("==========================================");
        System.out.println("Total card: " + items.size());
        System.out.println("Max time: " + maxTime);
        System.out.println("Min time: " + minTime);
        System.out.println("Average time: " + (average / items.size()));
        System.out.println("==========================================");
    }
}
