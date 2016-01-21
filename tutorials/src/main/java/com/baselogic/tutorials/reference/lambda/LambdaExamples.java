package com.baselogic.tutorials.reference.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;

import java.nio.file.Files;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitor;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;

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
public class LambdaExamples {

    private static final Logger logger = LoggerFactory.getLogger(LambdaExamples.class);

    @FunctionalInterface
    interface MathOperation {
        int operation(int a, int b);
    }

    @FunctionalInterface
    interface GreetingService {
        void sayMessage(String message);
    }




    public int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }


    /**
     * convert all characters to upper case.
     * @param words
     * @return
     */
    public static List<String> allToUpperCase(List<String> words) {
        return words.stream()
                .map(string -> string.toUpperCase())
                .collect(Collectors.toList());
    }

    /*
     * Long form:
    public static List<String> uppercaseFirstChar(List<String> words) {
        return words.stream()
                .map(value -> {
                    char firstChar = value.charAt(0);
                    firstChar = LambdaExamples.toUpperCase(firstChar);
                    return firstChar + value.substring(1);
                })
                .collect(Collectors.toList());
    }*/

    /*
     * Short form:
     */
    public static List<String> uppercaseFirstChar(List<String> words) {
        return words.stream()
                .map(LambdaExamples::firstToUppercase)
                .collect(Collectors.toList());
    }

    public static char toUpperCase(char value) {
        return Character.toUpperCase(value);
    }

    public static String toUppercase(String value) {
        return value.toUpperCase();
    }

    public static String firstToUppercase(String value) {
        char firstChar = value.charAt(0);
        firstChar = LambdaExamples.toUpperCase(firstChar);
        return firstChar + value.substring(1);
    }


    /**
     * More examples
     * @throws Exception
     */
    public static void example2() throws Exception {
        //-------------------------------------------------------------------//
        // Target type #1: variable declaration

        Runnable r = () -> { logger.info("running"); };
        r.run();

        //-------------------------------------------------------------------//
        // Target type #2: assignment

        Runnable r2 = () -> logger.info("running");
        r2.run();

        //-------------------------------------------------------------------//
        // Target type #3: return statement (in getFilter())

        File[] files = new File(".").listFiles(getFilter("txt"));
        for (File file: files)
            logger.info(file.toString());

        //-------------------------------------------------------------------//
        // Target type #4: array initializer

        FileSystem fs = FileSystems.getDefault();
        final PathMatcher matchers[] =
                {
                        (path) -> path.toString().endsWith("txt"),
                        (path) -> path.toString().endsWith("java")
                };

        FileVisitor<Path> visitor;
        visitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file,
                                             BasicFileAttributes
                                                     attribs)
            {
                Path name = file.getFileName();
                for (PathMatcher matcher: matchers)
                {
                    if (matcher.matches(name))
                        System.out.printf("***Found matched file: '%s'.%n",
                                file);
                    logger.info("logger:: Found matched file:", "'%s'.$n", file);
                }
                return FileVisitResult.CONTINUE;
            }
        };
        Files.walkFileTree(Paths.get("."), visitor);


        //-------------------------------------------------------------------//
        // Target type #5: method or constructor arguments

        new Thread(() -> logger.info("running")).start();

        //-------------------------------------------------------------------//
        // Target type #6: lambda body (a nested lambda)

        Callable<Runnable> callable = () -> () ->
                logger.info("called");
        callable.call().run();

        //-------------------------------------------------------------------//
        // Target type #7: ternary conditional expression

        boolean ascendingSort = false;
        Comparator<String> compare;

        compare = (ascendingSort) ? (s1, s2) -> s1.compareTo(s2)
                : (s1, s2) -> s2.compareTo(s1);

        List<String> cities = Arrays.asList(
                "Washington", "London", "Rome", "Berlin", "Jerusalem",
                "Park City", "Sydney", "Moscow");
        Collections.sort(cities, compare);

        cities.forEach(logger::info);
//        for (String city: cities)
//            logger.info(city);

        //-------------------------------------------------------------------//
        // Target type #8: cast expression

        String user =
                AccessController.doPrivileged((PrivilegedAction<String>) ()
                        ->
                        System.getProperty("user.name"));
        logger.info("user: {}", user);
    }

    static FileFilter getFilter(String ext) {
        return (pathname) -> pathname.toString().endsWith(ext);
    }

} // the end...
