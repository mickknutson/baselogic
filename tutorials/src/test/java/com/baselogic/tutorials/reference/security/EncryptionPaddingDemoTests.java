package com.baselogic.tutorials.reference.security;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * Created by mickknutson on 4/30/15.
 */
public class EncryptionPaddingDemoTests {

    private static final Logger logger = LoggerFactory.getLogger(EncryptionPaddingDemoTests.class);

    public static final String ALGORITHM_MODE_PAD = "AES/ECB/PKCS5Padding";
//    public static final String ALGORITHM_MODE_PAD = "AES/CBC/PKCS5Padding";
//    public static final String ALGORITHM_MODE_PAD = "AES/CTR/NoPadding";

    EncryptionPaddingDemo encryptionPaddingDemo = new EncryptionPaddingDemo();

    String seed = "foo Bar";

    String keyPassword = "";

    //-----------------------------------------------------------------------//
    // Unit Tests
    //-----------------------------------------------------------------------//
    @Test
    public void encryptSecretKeySpecTests() throws Exception{

//        byte[] result = encryptionPaddingDemo.encryptSecretKeySpec(
//                seed.getBytes(Charset.defaultCharset()),
//                keyPassword.getBytes(Charset.defaultCharset()),
//                ALGORITHM_MODE_PAD
//        );
    }

}
