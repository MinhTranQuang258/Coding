package com.spsvietnam.connectionpool;

import java.awt.print.Printable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import junit.framework.Test;

/**
 * Hello world!
 *
 */
public class App {

    //	public static void main(String[] args) {
    //		A.getInstance().getStudent().setCount(5);
    //		System.out.println(A.getInstance().getStudent().getCount());
    //	}

    public static void main(String[] args) throws Exception{

        //		final DemoPoolingDataSource demoPoolingDataSource= new DemoPoolingDataSource();
        //		DataSource dataSource= demoPoolingDataSource.setupDataSource();
        //		final DemoPoolingDataSource2 demoPoolingDataSource2= new DemoPoolingDataSource2();
        //		DataSource dataSource2= demoPoolingDataSource2.setupDataSource();
        //		DemoBasicDataSource basicDataSource= new DemoBasicDataSource();
        //		try {
        //			for (int i = 0; i < 3; i++) {
        //				new Thread(new Runnable() {
        //					@Override
        //					public void run() {
        //						try {
        //							System.out.println(Thread.currentThread().getName() + " Begin processing");
        //							
        ////							Connection connection = dataSource.getConnection();
        ////							Connection connection2= dataSource2.getConnection();
        //							basicDataSource.getConnection();
        //							
        //							
        //							System.out.println(Thread.currentThread().getName() + " begin sleeping. Connection count = " );
        //							TimeUnit.SECONDS.sleep(15);
        //							
        //							System.out.println(Thread.currentThread().getName() + " end sleeping. Connection count = " );
        //						} catch (Exception e) {
        //							e.printStackTrace();
        //						}
        //						finally {
        //                            
        //                        }
        //					}
        //				}, "thread-" + i).start();
        //			}
        //		} catch (Exception e) {
        //			e.printStackTrace();
        //		}

//        final App app = new App();
//        for (int i = 0; i < 6; i++) {
//            new Thread(new Runnable() {
//                
//                @Override
//                public void run() {
//                    app.test();                    
//                }
//            }).start();
//        }
//        app.test();
        
        int size= 2999999;
        Random random = new Random();
        List<Student> list= new ArrayList<Student>();
        for (int i= 0 ; i< size; i++) {
            int rand= random.nextInt(10000);
            Student student= new Student(rand);
            list.add(student);
        }
        int k= size / 2;
        List<Student> list1= list.subList(0, k);
        List<Student> list2= list.subList(k, size);
//        List<Integer> list3= list.subList((k + k), (k + k + k));
//        List<Integer> list4= list.subList((k + k + k), size);
//        System.out.println(list4.size());
//        Map<Integer, List<Integer>> map= new HashMap<>();
//        map.put(1, list1);
//        map.put(2, list2);
        
        Thread thread1= null;
        Thread thread2= null;
//        Thread thread3= null;
//        Thread thread4= null;
        getThread(thread1, list1);
        getThread(thread2, list2);
//        getThread(thread3, list3);
//        getThread(thread4, list4);
        
//        findDuplicatedValuesWithSet(list);
//        findDuplicatedValuesWithMap(list);
//        finDuplicatedValues(list);
    }
    
    private static void getThread(Thread thread, List<Student> list) {
        thread= new Thread(new Runnable() {
            
            @Override
            public void run() {
              findDuplicatedValuesWithMap(list);
            }
        });
        thread.start();
    } 
    
    private static void sortTime(List<Integer> list) {
        long startTime= System.currentTimeMillis();
        Collections.sort(list);
        System.out.println("Sort time: " + (System.currentTimeMillis()- startTime));
    }
    
    private static void countTime(List<Integer> list) {
        long startTime= System.currentTimeMillis();
        int dem= 1;
        for (int i= 0; i< list.size()- 1; i++) {
            if( list.get(i) == list.get(i + 1)) {
               dem++;
            }
            else {
//                System.out.println(list.get(i) +": "+ dem);
                dem= 1;
            }
        }
//        System.out.println(dem);
        System.out.println("Count time: " + (System.currentTimeMillis()- startTime));
    }
    
    private static void findDuplicatedValuesWithSet(List<Student> list) {
        long startTime= System.currentTimeMillis();
        Set<Student> set= new HashSet<>(list);
        for (Student student: set) {
            Collections.frequency(list, student);
//            System.out.println(integer + ": " + Collections.frequency(list, integer));
        }
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
    }
    
    
    
    private static void findDuplicatedValuesWithMap(List<Student> list) {
        long startTime= System.currentTimeMillis();
        
        Map<Integer, AtomicInteger> map= new HashMap<>();
        
        for(Student student: list) {
            AtomicInteger count= map.get(student.getValue());
            if(count != null) {
                int newValue= count.incrementAndGet();
            }
//            map.put(student.getValue(), (count == null) ? 1 : count.incrementAndGet());
        }
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
//        printMap(map);
    }
    
    private static void printMap(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Count: " + entry.getValue());
        } 
    } 
}
