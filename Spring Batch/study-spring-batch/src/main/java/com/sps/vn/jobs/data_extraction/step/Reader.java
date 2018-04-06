/*
 * Class: Reader
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import com.sps.vn.model.Data;

@Component
public class Reader implements ItemReader<List<Data>>{
    
    private static final String PATCH = "/Users/tqminh_1/Desktop/Read/";

    private int count= 0;
    
    @Override
    public List<Data> read() throws Exception, UnexpectedInputException,
            ParseException, NonTransientResourceException {
        
        final File folder = new File(PATCH);
        final File[] files = folder.listFiles();

        if(count < files.length) {
            File file= files[count];
            count++;
            final String content = readFile(file);
            List<Data> dataList= extract(content);
            return dataList;
        }
        else {
            count = 0;
        }
        return null;
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
}
