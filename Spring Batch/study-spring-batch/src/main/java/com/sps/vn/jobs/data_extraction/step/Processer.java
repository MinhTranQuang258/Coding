/*
 * Class: Processer
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
package com.sps.vn.jobs.data_extraction.step;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.sps.vn.model.Data;

@Component
public class Processer implements ItemProcessor<List<Data>, List<Data>>{
    
    @Override
    public List<Data> process(List<Data> item) throws Exception {
        return item;
    }
}
