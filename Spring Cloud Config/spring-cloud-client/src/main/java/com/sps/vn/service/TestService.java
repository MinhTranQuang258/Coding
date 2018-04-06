/*
 * Class: TestService
 *
 * Created on Mar 27, 2018
 *
 * (c) Copyright Swiss Post Solution, unpublished work
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Swiss Post Solution.
 * Floor 4-5-8, ICT Tower, Quang Trung Software City
 */
package com.sps.vn.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration

public class TestService {

    @Value("${number}")
    private int number;
    
    private int result;
    @RefreshScope
    public int sum() {
        System.out.println("PreAction : " + result);
        result= number + 5;
        return result;
    }
}
 