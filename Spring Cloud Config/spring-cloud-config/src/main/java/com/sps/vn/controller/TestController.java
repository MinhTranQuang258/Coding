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

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class TestController {
  
    @RequestMapping(value= "/encryption", method= RequestMethod.POST)
    public String encrypt(@RequestBody ObjectNode json) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(json.get("key").asText());
        encryptor.setAlgorithm(json.get("algorithm").asText());
        String encryptedText = encryptor.encrypt(json.get("password").asText());
        return encryptedText;
    }
}
