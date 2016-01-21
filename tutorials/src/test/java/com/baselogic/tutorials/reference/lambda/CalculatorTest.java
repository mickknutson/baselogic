package com.baselogic.tutorials.reference.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Lambda Calculator Test
 */
public class CalculatorTest {

    @Test
    public void testIntegers(){

        Integer result = Calculator.calculate(5, 5,
                // lambda expression:
                (x, y) -> x + y // BinaryOperator
        );

        assertThat(result, is(10));

    }
}
