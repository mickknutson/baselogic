package com.baselogic.tutorials.reference.lambda;

import java.util.function.BinaryOperator;

/**
 * Lambda Calculator
 */
public class Calculator {

    public static <T> T calculate(T op1, T op2,
                                  BinaryOperator<T> operator) {

        return operator.apply(op1, op2);
    }

}
