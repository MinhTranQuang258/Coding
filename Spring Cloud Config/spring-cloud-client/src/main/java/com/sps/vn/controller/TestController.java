/*
 * Class: TestController
 *
 * Created on Mar 23, 2018
 *
 * (c) Copyright Swiss Post Solution, unpublished work
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Swiss Post Solution.
 * Floor 4-5-8, ICT Tower, Quang Trung Software City
 */
package com.sps.vn.controller;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sps.vn.service.TestService;

@RestController
public class TestController {
    
    @Autowired
    private TestService test;
    
    @Value("${username}")
    private String username;

    @RequestMapping("/test")
    public String test() {
        return username;
    }
    
    @RequestMapping("/sum")
    public void get() {
        System.out.println("afterAction : " + test.sum());
    }
    
    @RequestMapping(value= "/encryption", method= RequestMethod.GET)
    public String encrypt() {
        ConfigurablePasswordEncryptor encryptor= new ConfigurablePasswordEncryptor();
        return encryptor.encryptPassword("12345!fra");
    }
}
