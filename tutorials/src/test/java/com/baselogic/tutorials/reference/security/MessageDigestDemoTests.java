package com.baselogic.tutorials.reference.security;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Reflections on trusting trust
 */
public class MessageDigestDemoTests {

    private static final Logger logger = LoggerFactory.getLogger(MessageDigestDemoTests.class);

    MessageDigestDemo messageDigestDemo = new MessageDigestDemo();

    static final String clearText = "someUnencryptedPassword";
    static final String salt = "nonce(SALT)Value";

    static final DateTime then = DateTime.parse("2015-07-03T01:20");
    static final DateTime now = DateTime.now(DateTimeZone.forID("America/Denver"));

    //-----------------------------------------------------------------------//

    @Test
    public void generateMessageDigest_Base64_MD5() throws Exception{

        String algorithm = "MD5";

        String result = messageDigestDemo.generateMessageDigest_Base64(algorithm, salt, clearText, then.toDate());
        String expected = "tIWBFV9WPRATFt4NgX41oQ==";

        assertThat(result, is(expected));
        assertThat(result.length(), is(24));
        logger.info("expected: {}", expected);
        logger.info("result: {}", result);
    }

    @Test
    public void generateMessageDigest_Base64_SHA1() throws Exception{

        String algorithm = "SHA1";

        String result = messageDigestDemo.generateMessageDigest_Base64(algorithm, salt, clearText, then.toDate());
        String expected = "/TI2NEee94r5S2siZ/eW/5RaZQE=";

        assertThat(result, is(expected));
        assertThat(result.length(), is(28));
        logger.info("expected: {}", expected);
        logger.info("result: {}", result);
    }

    @Test
    public void generateMessageDigest_Base64_SHA256() throws Exception{

        String algorithm = "SHA-256";

        String result = messageDigestDemo.generateMessageDigest_Base64(algorithm, salt, clearText, then.toDate());
        String expected = "jZSF+XXLy5H+n5KEoG3ppIvFLCLUIrhFYRDPYCKZNz4=";

        assertThat(result, is(expected));
        assertThat(result.length(), is(44));
        logger.info("expected: {}", expected);
        logger.info("result: {}", result);
    }

    @Test
    public void generateMessageDigest_Base64_SHA384() throws Exception{

        String algorithm = "SHA-384";

        String result = messageDigestDemo.generateMessageDigest_Base64(algorithm, salt, clearText, then.toDate());
        String expected = "FAaJsuuZulaxEL/ZRVXiyFysybxWKbi/IzPAqPjDUhyVknuLRzS8oOs5QjpAOmar";

        assertThat(result, is(expected));
        assertThat(result.length(), is(64));
        logger.info("expected: {}", expected);
        logger.info("result: {}", result);
    }

    @Test
    public void generateMessageDigest_Base64_SHA512() throws Exception{

        String algorithm = "SHA-512";

        String result = messageDigestDemo.generateMessageDigest_Base64(algorithm, salt, clearText, then.toDate());
        String expected = "XJdcz8Rvzj6fERFCGQZdktLb6DX6k1L+pCqvFHY7il4qDe8owxbJnqWUELRCAlc2nxWvwj46YUlJLgONjrQ4tw==";

        assertThat(result, is(expected));
        assertThat(result.length(), is(88));
        logger.info("expected: {}", expected);
        logger.info("result: {}", result);
    }

    @Test
    public void generateMessageDigestSalt_Hex_MD5() throws Exception{

        final String algorithm = "MD5";

        final String result = messageDigestDemo.generateMessageDigestSalt_Hex(algorithm, salt, clearText);
        final String expected = "955881d4026dbd7afb50045fbde74f15";

        assertThat(result, is(expected));
        assertThat(result.length(), is(32));
        logger.info("expected: {}", expected);
        logger.info("sResult: {}", result);
    }

    @Test
    public void generateMessageDigestSalt_Hex_SHA1() throws Exception{

        String algorithm = "SHA1";

        String result = messageDigestDemo.generateMessageDigestSalt_Hex(algorithm, salt, clearText);
        String expected = "dea6d6048594c6115a0b0318d562711f14221fb7";

        assertThat(result, is(expected));
        assertThat(result.length(), is(40));
        logger.info("expected: {}", expected);
        logger.info("sResult: {}", result);
    }

    @Test
    public void generateMessageDigestSalt_Hex_SHA256() throws Exception{

        String algorithm = "SHA-256";

        String result = messageDigestDemo.generateMessageDigestSalt_Hex(algorithm, salt, clearText);
        String expected = "e7d3443e835ad830711b4c7be88dd29308ddca7c38f974382167198705ea0830";

        assertThat(result, is(expected));
        assertThat(result.length(), is(64));
        logger.info("expected: {}", expected);
        logger.info("sResult: {}", result);
    }

    @Test
    public void generateMessageDigest_Hex_SHA1() throws Exception{

        String algorithm = "SHA1";

        String result = messageDigestDemo.generateMessageDigest_Hex(algorithm, clearText);
        String expected = "ddaabbe6354d0079297daf37751881bb614a76aa";

        assertThat(result, is(expected));
        assertThat(result.length(), is(40));
        logger.info("expected: {}", expected);
        logger.info("result: {}", result);
    }

    //-----------------------------------------------------------------------//
    //-----------------------------------------------------------------------//

    @Test
    public void generateMac_Hex_Hmac_MD5() throws Exception{

        String algorithm = "HmacMD5";

        byte[] digest = messageDigestDemo.generateMac(algorithm, salt, clearText);
        String result = messageDigestDemo.toHex(digest);
        String expected = "4fc131b451e7dc7ce4fa56ea5290feb7";

        assertThat(result, is(expected));
        assertThat(result.length(), is(32));
        logger.info("expected: {}", expected);
        logger.info("result: {}", result);
    }

    @Test
    public void generateMac_Hex_Hmac_SHA1() throws Exception{

        String algorithm = "HmacSHA1";

        byte[] digest = messageDigestDemo.generateMac(algorithm, salt, clearText);
        String result = messageDigestDemo.toHex(digest);
        String expected = "cb41372d2759808af4ae3511eb2ff140118127be";

        assertThat(result, is(expected));
        assertThat(result.length(), is(40));
        logger.info("expected: {}", expected);
        logger.info("result: {}", result);
    }

    @Test
    public void generateMac_Hex_Hmac_SHA256() throws Exception{

        String algorithm = "HmacSHA256";

        byte[] digest = messageDigestDemo.generateMac(algorithm, salt, clearText);
        String result = messageDigestDemo.toHex(digest);
        String expected = "6cae88144b5ae772c11e1c6f7be5e77b37f0ee132a75e0e023b73a328b149089";

        assertThat(result, is(expected));
        assertThat(result.length(), is(64));
        logger.info("expected: {}", expected);
        logger.info("result: {}", result);
    }




    @Test
    public void generateMac() throws Exception{

        String algorithm = "HmacSHA256";

        byte[] result = messageDigestDemo.generateMac(algorithm, salt, clearText);
        String resultHex = messageDigestDemo.toHex(result);
        String expectedHex = "6cae88144b5ae772c11e1c6f7be5e77b37f0ee132a75e0e023b73a328b149089";

        assertThat(resultHex, is(expectedHex));
        assertThat(resultHex.length(), is(64));
        logger.info("expected: {}", expectedHex);
        logger.info("result: {}", result);

    }




    @BeforeClass
    public static void beforeClass(){
        logger.warn("=== BEFORE ============================================");
    }
    @AfterClass
    public static void afterClass(){
        logger.warn("=== AFTER =============================================");
    }


}
