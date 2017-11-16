package com.baselogic.tutorials.reference.lambda;

import com.baselogic.tutorials.annotations.Auditable;

import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 * Lambda Calculator
 */
public class Calculator {

    @Auditable("a calculation")
    public static <T> T calculate(final T op1,
                                  final T op2,
                                  final BinaryOperator<T> operator) {

        return operator.apply(op1, op2);
    }






    public static BinaryOperator<Integer> add = (a, b) -> a + b;

    public static BinaryOperator<Integer> subract = (a, b) -> a - b;

    public static BinaryOperator<Integer> multiply = (a, b) -> a * b;

    public static BinaryOperator<Integer> divide = (a, b) -> a / b;

    public static BinaryOperator<Integer> modulus = (a, b) -> a % b;


} // The End...
