/*
 * Class: EncryptationHelper
 *
 * Created on Mar 14, 2018
 *
 * (c) Copyright Swiss Post Solution, unpublished work
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Swiss Post Solution.
 * Floor 4-5-8, ICT Tower, Quang Trung Software City
 */
package com.sps.vn.Encrypt;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class EncryptationHelper {
    private static final String DEFAULT_ENCRYPTION_KEY = "This is a fairly long phrase used to encrypt";
    private static final String ALGORITHM_NAME = "DES";
    private static final String UTF8 = "UTF8";
    
    public static String decrypt(String msg) throws Exception {
        return decrypt(msg, DEFAULT_ENCRYPTION_KEY);
    }

    private static String decrypt(String msg, String keys) throws Exception {
        try {
            Cipher decipher = getInitializedCipher(Cipher.DECRYPT_MODE, keys);
            byte[] utf8 = decipher.doFinal(Base64.decodeBase64(msg));
            return new String(utf8, UTF8);
        } catch (Exception ex) {
            throw ex;
        }
    }
  
    private static Cipher getInitializedCipher(int opmode, String keys) throws Exception {
        String cipherkey = keys;
        if (cipherkey == null) {
            cipherkey = DEFAULT_ENCRYPTION_KEY;
        }
        KeySpec keySpec;
        try {
            keySpec = new DESKeySpec(cipherkey.getBytes());
            SecretKey key = SecretKeyFactory.getInstance(ALGORITHM_NAME).generateSecret(keySpec);
            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            cipher.init(opmode, key);
            return cipher;
        } catch (Exception ex) {
           throw ex;
        }
    }
}
