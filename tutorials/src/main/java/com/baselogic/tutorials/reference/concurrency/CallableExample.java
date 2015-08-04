package com.baselogic.tutorials.reference.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by mickknutson on 2/17/15.
 */
public class CallableExample {

    private static final Logger logger = LoggerFactory.getLogger(CallableExample.class);


    public static void main(String[] args) throws Exception{
        // Do commands 1-2

        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<Void> result = service.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                // call commands 3-5
                return null;
            }
        });

        try {
            result.get(42, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException e) {
            throw new Exception();
        }

        service.shutdown();

        // Do commands 6-10
    }

}
