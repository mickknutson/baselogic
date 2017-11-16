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

    @Test
    public void test__addition(){

        Integer result = Calculator.calculate(5, 5,
                Calculator.add
        );

        assertThat(result, is(10));
    }


    @Test
    public void test__subtraction(){

        Integer result = Calculator.calculate(64, 46,
                Calculator.subract
        );

        assertThat(result, is(18));
    }


    @Test
    public void test__multiplication(){

        Integer result = Calculator.calculate(5, 5,
                Calculator.multiply
        );

        assertThat(result, is(25));
    }


    @Test
    public void test__division(){

        Integer result = Calculator.calculate(5, 5,
                Calculator.divide
        );

        assertThat(result, is(1));
    }


    @Test
    public void test__modulus(){

        Integer result = Calculator.calculate(10, 6,
                Calculator.modulus
        );

        assertThat(result, is(4));
    }

} // The End...
