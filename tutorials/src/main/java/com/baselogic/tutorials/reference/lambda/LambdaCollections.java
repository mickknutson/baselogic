package com.baselogic.tutorials.reference.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lambda Collection Utilities
 */
public class LambdaCollections {

    private static final Logger logger = LoggerFactory.getLogger(LambdaCollections.class);

    @FunctionalInterface
    interface Lister {
        int sort(int a, int b);
    }

}
