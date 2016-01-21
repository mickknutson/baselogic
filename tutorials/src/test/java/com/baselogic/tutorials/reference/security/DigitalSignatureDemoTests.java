package com.baselogic.tutorials.reference.security;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by mickknutson on 4/30/15.
 */
public class DigitalSignatureDemoTests {

    private static final Logger logger = LoggerFactory.getLogger(DigitalSignatureDemoTests.class);

    //-----------------------------------------------------------------------//
    // Unit Tests
    //-----------------------------------------------------------------------//
    @Test
    public void providerTests() {

        //tbd
    }

    //-----------------------------------------------------------------------//
    // Lifecycle Methods
    //-----------------------------------------------------------------------//
    @BeforeClass
    public static void beforeClass(){
        logger.warn("=== BEFORE ============================================");
    }
    @AfterClass
    public static void afterClass(){
        logger.warn("=== AFTER =============================================");
    }
    @Before
    public void beforeEachTest() throws Exception {}

    @After
    public void afterEachTest() throws Exception {}
}
