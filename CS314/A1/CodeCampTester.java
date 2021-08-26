
import java.util.*;

// CodeCamp.java - CS314 Assignment 1 - Tester class

/*
 *  Name: Danica Padlan
 *  email address: danica_padlan@yahoo.com
 *  UTEID: dmp3357
 *  Section 5 digit ID: 52288
 *  Grader name: Noah
 *  Number of slip days used on this assignment: 0
 */

/*
 * CS314 Students: place results of shared birthdays experiments in this
 * comment.
 *
 *  Birthday Experiment Results
 *
    Num People: 182, number of experiments with one or more shared birthday: 1000000, percentage: 100.00%
    *
    ~~Testing 2-100 People~~

    Prediction: I predict it will take 50 people to reach 50%

    Result: It takes 23 people to reach at least 50% average of shared birthdays. My prediction reached over 100% already.
    At first I was surprised on how quick it escalated but then it makes sense that the more people
    that are involved in the experiment can increase the percentage quickly. The more people there are,
    the higher the chances are for shared birthdays.

    Testing 2-100 People
    Num People: 2, number of experiments with one or more shared birthday: 155, percentage: 0.31%
    Num People: 3, number of experiments with one or more shared birthday: 408, percentage: 0.82%
    Num People: 4, number of experiments with one or more shared birthday: 852, percentage: 1.70%
    Num People: 5, number of experiments with one or more shared birthday: 1306, percentage: 2.61%
    Num People: 6, number of experiments with one or more shared birthday: 1962, percentage: 3.92%
    Num People: 7, number of experiments with one or more shared birthday: 2862, percentage: 5.72%
    Num People: 8, number of experiments with one or more shared birthday: 3798, percentage: 7.60%
    Num People: 9, number of experiments with one or more shared birthday: 4830, percentage: 9.66%
    Num People: 10, number of experiments with one or more shared birthday: 5818, percentage: 11.64%
    Num People: 11, number of experiments with one or more shared birthday: 7075, percentage: 14.15%
    Num People: 12, number of experiments with one or more shared birthday: 8244, percentage: 16.49%
    Num People: 13, number of experiments with one or more shared birthday: 9717, percentage: 19.43%
    Num People: 14, number of experiments with one or more shared birthday: 11223, percentage: 22.45%
    Num People: 15, number of experiments with one or more shared birthday: 12522, percentage: 25.04%
    Num People: 16, number of experiments with one or more shared birthday: 14354, percentage: 28.71%
    Num People: 17, number of experiments with one or more shared birthday: 15909, percentage: 31.82%
    Num People: 18, number of experiments with one or more shared birthday: 17358, percentage: 34.72%
    Num People: 19, number of experiments with one or more shared birthday: 18962, percentage: 37.92%
    Num People: 20, number of experiments with one or more shared birthday: 20651, percentage: 41.30%
    Num People: 21, number of experiments with one or more shared birthday: 22053, percentage: 44.11%
    Num People: 22, number of experiments with one or more shared birthday: 23695, percentage: 47.39%
    **Num People: 23, number of experiments with one or more shared birthday: 25422, percentage: 50.84%**
    Num People: 24, number of experiments with one or more shared birthday: 26931, percentage: 53.86%
    Num People: 25, number of experiments with one or more shared birthday: 28503, percentage: 57.01%
    Num People: 26, number of experiments with one or more shared birthday: 29823, percentage: 59.65%
    Num People: 27, number of experiments with one or more shared birthday: 31271, percentage: 62.54%
    Num People: 28, number of experiments with one or more shared birthday: 32643, percentage: 65.29%
    Num People: 29, number of experiments with one or more shared birthday: 34107, percentage: 68.21%
    Num People: 30, number of experiments with one or more shared birthday: 35349, percentage: 70.70%
    Num People: 31, number of experiments with one or more shared birthday: 36601, percentage: 73.20%
    Num People: 32, number of experiments with one or more shared birthday: 37683, percentage: 75.37%
    Num People: 33, number of experiments with one or more shared birthday: 38890, percentage: 77.78%
    Num People: 34, number of experiments with one or more shared birthday: 39895, percentage: 79.79%
    Num People: 35, number of experiments with one or more shared birthday: 40659, percentage: 81.32%
    Num People: 36, number of experiments with one or more shared birthday: 41751, percentage: 83.50%
    Num People: 37, number of experiments with one or more shared birthday: 42252, percentage: 84.50%
    Num People: 38, number of experiments with one or more shared birthday: 43225, percentage: 86.45%
    Num People: 39, number of experiments with one or more shared birthday: 43892, percentage: 87.78%
    Num People: 40, number of experiments with one or more shared birthday: 44755, percentage: 89.51%
    Num People: 41, number of experiments with one or more shared birthday: 45181, percentage: 90.36%
    Num People: 42, number of experiments with one or more shared birthday: 45609, percentage: 91.22%
    Num People: 43, number of experiments with one or more shared birthday: 46166, percentage: 92.33%
    Num People: 44, number of experiments with one or more shared birthday: 46690, percentage: 93.38%
    Num People: 45, number of experiments with one or more shared birthday: 47073, percentage: 94.15%
    Num People: 46, number of experiments with one or more shared birthday: 47450, percentage: 94.90%
    Num People: 47, number of experiments with one or more shared birthday: 47765, percentage: 95.53%
    Num People: 48, number of experiments with one or more shared birthday: 47983, percentage: 95.97%
    Num People: 49, number of experiments with one or more shared birthday: 48203, percentage: 96.41%
    Num People: 50, number of experiments with one or more shared birthday: 48521, percentage: 97.04%
    Num People: 51, number of experiments with one or more shared birthday: 48663, percentage: 97.33%
    Num People: 52, number of experiments with one or more shared birthday: 48918, percentage: 97.84%
    Num People: 53, number of experiments with one or more shared birthday: 49083, percentage: 98.17%
    Num People: 54, number of experiments with one or more shared birthday: 49121, percentage: 98.24%
    Num People: 55, number of experiments with one or more shared birthday: 49276, percentage: 98.55%
    Num People: 56, number of experiments with one or more shared birthday: 49360, percentage: 98.72%
    Num People: 57, number of experiments with one or more shared birthday: 49508, percentage: 99.02%
    Num People: 58, number of experiments with one or more shared birthday: 49575, percentage: 99.15%
    Num People: 59, number of experiments with one or more shared birthday: 49643, percentage: 99.29%
    Num People: 60, number of experiments with one or more shared birthday: 49703, percentage: 99.41%
    Num People: 61, number of experiments with one or more shared birthday: 49774, percentage: 99.55%
    Num People: 62, number of experiments with one or more shared birthday: 49797, percentage: 99.59%
    Num People: 63, number of experiments with one or more shared birthday: 49828, percentage: 99.66%
    Num People: 64, number of experiments with one or more shared birthday: 49845, percentage: 99.69%
    Num People: 65, number of experiments with one or more shared birthday: 49880, percentage: 99.76%
    Num People: 66, number of experiments with one or more shared birthday: 49905, percentage: 99.81%
    Num People: 67, number of experiments with one or more shared birthday: 49921, percentage: 99.84%
    Num People: 68, number of experiments with one or more shared birthday: 49933, percentage: 99.87%
    Num People: 69, number of experiments with one or more shared birthday: 49955, percentage: 99.91%
    Num People: 70, number of experiments with one or more shared birthday: 49948, percentage: 99.90%
    Num People: 71, number of experiments with one or more shared birthday: 49975, percentage: 99.95%
    Num People: 72, number of experiments with one or more shared birthday: 49964, percentage: 99.93%
    Num People: 73, number of experiments with one or more shared birthday: 49987, percentage: 99.97%
    Num People: 74, number of experiments with one or more shared birthday: 49978, percentage: 99.96%
    Num People: 75, number of experiments with one or more shared birthday: 49987, percentage: 99.97%
    Num People: 76, number of experiments with one or more shared birthday: 49985, percentage: 99.97%
    Num People: 77, number of experiments with one or more shared birthday: 49994, percentage: 99.99%
    Num People: 78, number of experiments with one or more shared birthday: 49992, percentage: 99.98%
    Num People: 79, number of experiments with one or more shared birthday: 49995, percentage: 99.99%
    Num People: 80, number of experiments with one or more shared birthday: 49992, percentage: 99.98%
    Num People: 81, number of experiments with one or more shared birthday: 49995, percentage: 99.99%
    Num People: 82, number of experiments with one or more shared birthday: 49997, percentage: 99.99%
    Num People: 83, number of experiments with one or more shared birthday: 49996, percentage: 99.99%
    Num People: 84, number of experiments with one or more shared birthday: 49998, percentage: 100.00%
    Num People: 85, number of experiments with one or more shared birthday: 49998, percentage: 100.00%
    Num People: 86, number of experiments with one or more shared birthday: 49998, percentage: 100.00%
    Num People: 87, number of experiments with one or more shared birthday: 50000, percentage: 100.00%
    Num People: 88, number of experiments with one or more shared birthday: 50000, percentage: 100.00%
    Num People: 89, number of experiments with one or more shared birthday: 50000, percentage: 100.00%
    Num People: 90, number of experiments with one or more shared birthday: 49999, percentage: 100.00%
    Num People: 91, number of experiments with one or more shared birthday: 50000, percentage: 100.00%
    Num People: 92, number of experiments with one or more shared birthday: 50000, percentage: 100.00%
    Num People: 93, number of experiments with one or more shared birthday: 50000, percentage: 100.00%
    Num People: 94, number of experiments with one or more shared birthday: 50000, percentage: 100.00%
    Num People: 95, number of experiments with one or more shared birthday: 50000, percentage: 100.00%
    Num People: 96, number of experiments with one or more shared birthday: 50000, percentage: 100.00%
    Num People: 97, number of experiments with one or more shared birthday: 50000, percentage: 100.00%
    Num People: 98, number of experiments with one or more shared birthday: 50000, percentage: 100.00%
    Num People: 99, number of experiments with one or more shared birthday: 50000, percentage: 100.00%
    Num People: 100, number of experiments with one or more shared birthday: 50000, percentage: 100.00%

 *
 *
 */

public class CodeCampTester {

    public static void main(String[] args) {
        final String newline = System.getProperty("line.separator");

        // CS314 Students: add tests here.
        /*
        //Birthday Experiment
        int year = 365;
        int numExperiments = 1000000;
        System.out.println("Birthday Experiment Results");
        System.out.println();
        CodeCamp.avgShared(numExperiments,182,year);
        System.out.println();

        numExperiments = 50000;
        System.out.println("Testing 2-100 People");
        for(int x = 2; x <= 100; x++){
            CodeCamp.avgShared(numExperiments, x, year);
        }

         */

        //test 1a, hamming distance
        int[] h1 = {2,13,1,16,9,25,10,7};
        int[] h2 = {2,14,1,15,9,26,10,11};
        int expected = 4;
        int actual = CodeCamp.hammingDistance(h1, h2);
        System.out.println("Test 1a hamming distance: expected value: " + expected
                + ", actual value: " + actual);
        if (expected == actual) {
            System.out.println(" passed test 1a, hamming distance");
        } else {
            System.out.println(" ***** FAILED ***** test 1a, hamming distance");
        }

        //test 2a, hamming distance
        h1 = new int[] {7,7,7,0,3,7,2};
        h2 = new int[] {7,7,7,0,3,7,8};
        expected = 1;
        actual = CodeCamp.hammingDistance(h1, h2);
        System.out.println( newline + "Test 2a hamming distance: expected value: " + expected
                + ", actual value: " + actual);
        if (expected == actual) {
            System.out.println(" passed test 2a, hamming distance");
        } else {
            System.out.println(" ***** FAILED ***** test 2a, hamming distance");
        }

        //test 3a, isPermutation
        int[] a = {5,9,10};
        int[] b = {10,5,9};
        boolean expectedBool = true;
        boolean actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 3a isPermutation: expected value: " + expectedBool
                + ", actual value: " + actualBool);
        if (expectedBool == actualBool) {
            System.out.println(" passed test 3a, isPermutation");
        } else {
            System.out.println(" ***** FAILED ***** test 3a, isPermutation");
        }

        //test 4a, isPermutation
        a = new int[] {6,19,15};
        b = new int[] {17,15,8};
        expectedBool = false;
        actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 4a isPermutation: expected value: " + expectedBool
                + ", actual value: " + actualBool);
        if (expectedBool == actualBool) {
            System.out.println(" passed test 4a, isPermutation");
        } else {
            System.out.println(" ***** FAILED ***** test 4a, isPermutation");
        }

        // test 5a, mostVowels
        String[] arrayOfStrings = { "longhOrn", "tExas", "bevO", "UnIverSity" };
        int expectedResult = 3;
        int actualResult = CodeCamp.mostVowels(arrayOfStrings);
        System.out.println(newline + "Test 5a mostVowels: expected result: " + expectedResult
                + ", actual result: " + actualResult);
        if (actualResult == expectedResult) {
            System.out.println(" passed test 5a, mostVowels");
        } else {
            System.out.println("***** FAILED ***** test 5a, mostVowels");
        }

        // test 6a, mostVowels
        arrayOfStrings = new String[] { "", null, "" ,null, "null", "", null};
        expectedResult = 4;
        actualResult = CodeCamp.mostVowels(arrayOfStrings);
        System.out.println(newline + "Test 6a mostVowels: expected result: " + expectedResult
                + ", actual result: " + actualResult);
        if (actualResult == expectedResult) {
            System.out.println(" passed test 6a, mostVowels");
        } else {
            System.out.println("***** FAILED ***** test 6a, mostVowels");
        }

        //test 7a, shared birthdays
        int pairs = CodeCamp.sharedBirthdays(51, 10);
        System.out.println(newline + "Test 7a shared birthdays: expected: a value of one or more, " +
                "actual: " + pairs);
        int expectedShared = 0;
        if (pairs > expectedShared) {
            System.out.println(" passed test 7a, shared birthdays");
        } else {
            System.out.println("***** FAILED ***** test 7a, shared birthdays");
        }

        //fix test
        //test 8a, shared birthdays
        pairs = CodeCamp.sharedBirthdays(100, 365);
        System.out.println(newline + "Test 8a shared birthdays: expected: a value of one or more, " +
                "actual: " + pairs);
        expectedShared = 0;
        if (pairs > expectedShared) {
            System.out.println(" passed test 8a, shared birthdays");
        } else {
            System.out.println("***** FAILED ***** test 8a, shared birthdays");
        }

        //test 9a, queensAreSafe
        char[][] board = new char[][] {  { '.', '.', '.', '.' },
                { '.', '.', '.', '.' },
                { '.', '.', '.', 'q' },
                { 'q', '.', '.', '.' } };
        expectedBool = true ;
        actualBool = CodeCamp.queensAreSafe(board);
        System.out.println(newline + "Test 9a queensAreSafe: expected value: " + expectedBool
                + ", actual value: " + actualBool);
        if (expectedBool == actualBool) {
            System.out.println(" passed test 9a, queensAreSafe");
        } else {
            System.out.println(" ***** FAILED ***** test 9a, queensAreSafe");
        }

        //test 10a, queensAreSafe
        board = new char[][] { { '.', '.', '.', 'q' },
                { '.', '.', 'q', '.' },
                { '.', '.', '.', 'q' },
                { 'q', '.', '.', '.' } };
        expectedBool = false;
        actualBool = CodeCamp.queensAreSafe(board);
        System.out.println(newline + "Test 10a queensAreSafe: expected value: " + expectedBool
                + ", actual value: " + actualBool);
        if (expectedBool == actualBool) {
            System.out.println(" passed test 10a, queensAreSafe");
        } else {
            System.out.println(" ***** FAILED ***** test 10a, queensAreSafe");
        }

        //test 11a, getValueOfMostPlot
        int[][] city = new int[][] { { 0, -1, -5, -3 }
                                    ,{-1, -5, 0, -3 }
                                    ,{ -3, -1, -5, 2} };
        expected = 2;
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println("\nTest 11a getValueOfMostValuablePlot: expected value: " + expected
                + ", actual value: " + actual);
        if (expected == actual) {
            System.out.println(" passed test 11a, getValueOfMostValuablePlot");
        } else {
            System.out.println(" ***** FAILED ***** test 11a, getValueOfMostValuablePlot");
        }

        //test12a, getValueOfMostPlot
        city = new int[][] { { 0, 0, -1, 5, 5, -6 } };
        expected = 10;
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println("\nTest 12a getValueOfMostValuablePlot: expected value: " + expected
                + ", actual value: " + actual);
        if (expected == actual) {
            System.out.println(" passed test 12a, getValueOfMostValuablePlot");
        } else {
            System.out.println(" ***** FAILED ***** test 12a, getValueOfMostValuablePlot");
        }

    } // end of main method
}
