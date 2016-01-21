package com.baselogic.tutorials.reference.lambda;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Testing Lambda Expressions
 */
public class LambdaStreamExamplesTest {

    private static final Logger logger = LoggerFactory.getLogger(LambdaStreamExamplesTest.class);

    List<String> testList1;
    List<String> companyList;

    @Before
    public void beforeClass() {
        testList1 = Arrays.asList("", "foo", "", "bar", "", "baz");
        logger.info("- Here is a Test List: {}", testList1);
        companyList = Arrays.asList("Tesla", "", "Uber", "Pandora", "Pied Piper", "Hooli", "Google", "Yahoo", "Facebook", "", "Twitter", "LinkedIn");
        logger.info("- Here is a Company List: {}", companyList);
    }


    @Test
    public void summaryStatistics() throws Exception{
        List<Integer> input = Arrays.asList(98, 4, 7, 3, 2, 46, 21, 53, 17, 32, 63, 52);

        IntSummaryStatistics result = LambdaStreamExamples.summaryStatistics(input);
        assertThat(result.getMax(), is(98));
        assertThat(result.getMin(), is(2));
        assertThat(result.getSum(), is(398L));
        assertThat(result.getCount(), is(12L));
        assertThat(result.getAverage(), is(33.166666666666664));

    }


    @Test
    public void printNumberOfEmptyStrings() throws Exception{
        long result = LambdaStreamExamples.printNumberOfEmptyStrings(testList1);
        assertThat(result, is(3L));
    }

    @Test
    public void createListOfCubes() throws Exception{
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> result = LambdaStreamExamples.createListOfCubes(numbers);
        List<Integer> expected = Arrays.asList(1, 8, 27, 64, 125);
        assertThat(result, is(expected));
    }

    @Test
    public void uppercaseAndJoinStrings() throws Exception{
        //-------------------------------------------------------------------//
        // Convert String to UPPERCASE and join them using space
        List<String> input = Arrays.asList("lambda", "expressions", "are", "cool", "!");

        String result = LambdaStreamExamples.uppercaseAndJoinStrings(input);
        String expected = "LAMBDA EXPRESSIONS ARE COOL !";
        assertThat(result, is(expected));
    }

    @Test
    public void createListGreaterThanLength() throws Exception{
        //-------------------------------------------------------------------//
        // Convert String to UPPERCASE and join them using space
        List<String> input = companyList;

        List<String> result = LambdaStreamExamples.createListGreaterThanLength(input, 6);
        List<String> expected = Arrays.asList("Pandora", "Pied Piper", "Facebook", "Twitter", "LinkedIn");
        assertThat(result, is(expected));
    }

    @Test
    public void removeAllEmptyStrings() throws Exception{
        //-------------------------------------------------------------------//
        // Convert String to UPPERCASE and join them using space
        List<String> input = companyList;

        List<String> result = LambdaStreamExamples.removeAllEmptyStrings(input);
        List<String> expected = Arrays.asList("Tesla", "Uber", "Pandora", "Pied Piper", "Hooli", "Google", "Yahoo", "Facebook", "Twitter", "LinkedIn");
        assertThat(result, is(expected));
    }

    @Test
    public void countEntriesThatBeginWith() throws Exception{
        //-------------------------------------------------------------------//
        // Convert String to UPPERCASE and join them using space
        List<String> input = companyList;

        Long result = LambdaStreamExamples.countEntriesThatBeginWith(input, "P");
        Long expected = 2L;
        assertThat(result, is(expected));
    }

}
