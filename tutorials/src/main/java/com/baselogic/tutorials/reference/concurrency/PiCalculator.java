package com.baselogic.tutorials.reference.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.lang.Math.*;

/**
 * Created by mickknutson on 4/29/15.
 */
public class PiCalculator implements Callable<Double> {

    private static final Logger logger = LoggerFactory.getLogger(PiCalculator.class);

    public Double call() {
        // Machin's formula
        // pi/4 = 4 * arctan(1/5) - arctan(1/239);
        double pi = 16 * atan(1.0 / 5.0) - 4 * atan(1.0 / 239.0);
        return pi;
    }

    public static void main(String[] args) throws Exception {

        ExecutorService ex = Executors.newCachedThreadPool();

        Future<Double> result = ex.submit(new PiCalculator());

        // do other stuff

        Double pi = result.get();
        logger.info("{}", pi);

        ex.shutdown();
    }
}
