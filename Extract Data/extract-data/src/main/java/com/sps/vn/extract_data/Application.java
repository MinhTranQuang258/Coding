package com.sps.vn.extract_data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Application {

    private static class RequestResult {
        private int duration;

        private String id;

        public RequestResult(final String id, final int duration) {
            super();
            this.id = id;
            this.duration = duration;
        }

        public int getDuration() {
            return this.duration;
        }

        public String getId() {
            return this.id;
        }
    }

    private static final String PATCH = "/Users/tqminh_1/Desktop/Read/";

    private static Pattern pattern = Pattern
        .compile("\\s(\\w+-\\w+-\\w+-\\w+-\\w+)\\s\\w+\\s\\w+\\s(\\d+)");

    private static List<RequestResult> extract(final String content) {
        final List<RequestResult> list = new ArrayList<>();

        final Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            final int duration = Integer.valueOf(matcher.group(2));
            list.add(new RequestResult(matcher.group(1), duration));
        }
        return list;
    }

    public static void main(final String[] args) throws IOException {
        long startTime= System.currentTimeMillis();
        final File folder = new File(PATCH);
        final File[] files = folder.listFiles();

        for (final File file : files) {
            
            final String content = readFile(file);
            final List<RequestResult> results = extract(content);
            printReport(results);
        
        }
        System.out.println("Total time: " + (System.currentTimeMillis()- startTime));
    }

    private static void printReport(final List<RequestResult> results) {
        int average = 0;
        final int maxTime = Collections.max(results, (o1, o2) -> {
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

        final int minTime = Collections.min(results, (o1, o2) -> {
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
        
        for (final RequestResult requestResult : results) {
            average += requestResult.getDuration();
            final String result = String.format(
                "card: %s done in %d",
                requestResult.getId(),
                requestResult.getDuration());
            System.out.println(result);
        }
        
        System.out.println("==========================================");
        System.out.println("Total card: " + results.size());
        System.out.println("Max time: " + maxTime);
        System.out.println("Min time: " + minTime);
        System.out.println("Average time: " + (average / results.size()));
        System.out.println("==========================================");
    }

    private static String readFile(final File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }
}
