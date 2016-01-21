package com.baselogic.tutorials.reference.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;


/**
 * Lambda Expression Syntax:
 *
 * (int x, int y)  ->  {
 *      int max = x  > y  ?  x  : y;
 *      return max;
 *  }
 *
 *  http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 */
public class LambdaStreamExamples {

    private static final Logger logger = LoggerFactory.getLogger(LambdaStreamExamples.class);


    static FileFilter getFilter(String ext) {
        return (pathname) -> pathname.toString().endsWith(ext);
    }

    /**
     * Set of Examples
     * @throws Exception
     */
    public static IntSummaryStatistics summaryStatistics(List<Integer> input) throws Exception {

        IntSummaryStatistics intSummaryStatistics = input
                .stream()
                .mapToInt((x) -> x)
                .summaryStatistics();

        logger.info("- Here is a integerList List: " + input);
        logger.info("Highest Prime # " + intSummaryStatistics.getMax());
        logger.info("Lowest Prime  # " + intSummaryStatistics.getMin());
        logger.info("Sum of All: " + intSummaryStatistics.getSum());
        logger.info("Average of all: " + intSummaryStatistics.getAverage() + "\n");

        return intSummaryStatistics;

    }

    public static long printNumberOfEmptyStrings(List<String> input){

        //-------------------------------------------------------------------//
        // Print only empty list count
        long emptyCount = input
                .stream() // open stream
                .filter(cList -> cList.isEmpty())
                .count();
        logger.info("Test1: # of Empty Strings: {}", emptyCount);
        return emptyCount;

    }


    public static List<Integer> createListOfCubes(List<Integer> input){

        //-------------------------------------------------------------------//
        // Create List of Cubes
        List<Integer> cubes = input
                .stream()
                .map(myInt -> myInt * myInt * myInt)
                .distinct()
                .collect(Collectors.toList());
        logger.info("- Create cubes for 1,2,3,4,5: " + cubes + "\n");
        return cubes;
    }

    public static String uppercaseAndJoinStrings(List<String> input){
        String joinList = input
                .stream()
                .map(x -> x.toUpperCase())
                .collect(Collectors.joining(" "));
        logger.info("- Join All String with UPPERCASE: " + joinList);
        return joinList;
    }

    public static List<String> createListGreaterThanLength(List<String> input, int gt){
        //-------------------------------------------------------------------//
        // Create a List with String > 6 characters
        List<String> newList = input
                .stream()
                .filter(x -> x.length() > gt)
                .collect(Collectors.toList());
        logger.info("New list which has total characters > 6: {}\n", newList);

        return newList;
    }

    public static List<String> removeAllEmptyStrings(List<String> input){
        // Remove all empty Strings from List
        List<String> removeEmptyStrings = input
                .stream()
                .filter(x -> !x.isEmpty())
                .collect(Collectors.toList());

        return removeEmptyStrings;
    }

    public static Long countEntriesThatBeginWith(List<String> input, String startsWith){
        //-------------------------------------------------------------------//
        // Match the pattern which starts with letter 'T' and print count
        Long startCount = input
                .stream()
                .filter(x -> x.startsWith(startsWith))
                .count();

        return startCount;
    }


} // the end...
