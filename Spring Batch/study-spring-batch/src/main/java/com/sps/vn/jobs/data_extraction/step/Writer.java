/*
 * Class: Writer
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
package com.sps.vn.jobs.data_extraction.step;
import java.util.Collections;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.sps.vn.model.Data;

@Component
public class Writer implements ItemWriter<List<Data>>{

    @Override
    public void write(List<? extends List<Data>> items) throws Exception {
        for (List<Data> list : items) {
            printReport(list);
        }
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
