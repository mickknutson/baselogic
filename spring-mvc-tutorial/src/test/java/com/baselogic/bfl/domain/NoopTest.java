package com.baselogic.tutorials.domain;

import org.junit.After;
import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class NoopTest {

//    private static final Logger logger = LoggerFactory.getLogger(NoopTest.class);

    //-----------------------------------------------------------------------//
    // Lifecycle Methods
    //-----------------------------------------------------------------------//
//    @Before
//    public void initTransaction() throws Exception {
//        logger.warn(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        logger.warn(">>> SEED >>>");
//        TestUtils.seedData(em,
//                dataSetFile,
//                nullPrimaryKeyFilters);
//    }
//
    @After
    public void afterTests() throws Exception {
    }

    //-----------------------------------------------------------------------//
    // Unit Tests
    //-----------------------------------------------------------------------//
    @Test
    public void testNoop() throws Exception {
//        logger.debug("noop");
    }

}
