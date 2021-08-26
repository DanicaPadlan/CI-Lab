//  CodeCamp.java - CS314 Assignment 1

/*  Student information for assignment:
 * 
 *  replace <NAME> with your name.
 *
 *  On my honor, Danica Padlan, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: Danica Padlan
 *  email address: danica_padlan@yahoo.com
 *  UTEID: dmp3357
 *  Section 5 digit ID: 52288
 *  Grader name: Noah
 *  Number of slip days used on this assignment: 0
 */

import java.util.Arrays;
import java.util.Random;

public class CodeCamp {

    /**
     * Determine the Hamming distance between two arrays of ints. 
     * Neither the parameter <tt>aData</tt> or
     * <tt>bData</tt> are altered as a result of this method.<br>
     * @param aData != null, aData.length == aData.length
     * @param bData != null
     * @return the Hamming Distance between the two arrays of ints.
     */

    //counts up differences of 2 arrays
    public static int hammingDistance(int[] aData, int[] bData) {
        // check preconditions for empty or uneven array lengths
        if (aData == null || bData == null || aData.length != bData.length) { 
            throw new IllegalArgumentException("Violation of precondition: " +
                    "hammingDistance. neither parameter may equal null, arrays" +
                    " must be equal length.");
        }

        int diff = 0;

        //goes through arrays to compare each other's values
        for(int x = 0; x < aData.length; x++){

            //when values do not match, increases diff
            if(aData[x] != bData[x]){
                diff++;
            }
        }
        return diff;
    }

    /**
     * Determine if one array of ints is a permutation of another.
     * Neither the parameter <tt>aData</tt> or 
     * the parameter <tt>bData</tt> are altered as a result of this method.<br>
     * @param aData != null
     * @param bData != null
     * @return <tt>true</tt> if aData is a permutation of bData, 
     * <tt>false</tt> otherwise
     * 
     */

    //determines whether the arrays are permutations of each other
    public static boolean isPermutation(int[] aData, int[] bData) {
        // check preconditions for empty arrays
        if (aData == null || bData == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "isPermutation. neither parameter may equal null.");
        }

        //not valid if the arrays are different lengths
        if(aData.length != bData.length){
            return false;
        } else {

            //creates deep copies
            int[] aSort = copy(aData);
            int[] bSort = copy(bData);

            //gets sorted, deep copies
            aSort = sort(aSort);
            bSort = sort(bSort);

            //reads through arrays' elements
            for(int x = 0; x < aSort.length; x++){

                //checks if values are not the same
                if(aSort[x] != bSort[x]){
                    return false;
                }
            }
        }
        return true;
    }

    //creates deep copies of given array
    private static int[] copy(int[] data){
        int[] copied = new int [data.length];

        //copies given data to new array
        for(int x = 0; x < data.length; x++){
            copied[x] = data[x];
        }
        return copied;
    }

    //sorts array in ascending order
    private static int[] sort(int[] data){

        //goes through given array
        for(int x = 1; x < data.length; x++){
            int currPos = x;

            //checks for invalid position
            //and small elements for switch
            while(currPos != 0 && data[currPos] < data[currPos-1]){

                //performs swap between the compared positions
                int temp = data[currPos];
                data[currPos] = data[currPos - 1];
                data[currPos - 1] = temp;

                //moves position back one to be checked again
                currPos--;
            }
        }
        return data;
    }

    /**
     * Determine the index of the String that 
     * has the largest number of vowels. 
     * Vowels are defined as <tt>'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 
     * 'U', and 'u'</tt>.
     * The parameter <tt>arrayOfStrings</tt> is not altered as a result of this method.
     * <p>pre: <tt>arrayOfStrings != null</tt>, <tt>arrayOfStrings.length > 0</tt>, 
     * there is an least 1 non null element in arrayOfStrings.
     * <p>post: return the index of the non-null element in arrayOfStrings that has the 
     * largest number of characters that are vowels.
     * If there is a tie return the index closest to zero. 
     * The empty String, "", has zero vowels.
     * It is possible for the maximum number of vowels to be 0.<br>
     * @param arrayOfStrings the array to check
     * @return the index of the non-null element in arrayOfStrings that has 
     * the largest number of vowels.
     */

    //counts and returns position of String with highest vowel count in given array
    public static int mostVowels(String[] arrayOfStrings) {
        // check preconditions for empty String array
        if (arrayOfStrings == null || arrayOfStrings.length == 0 || !atLeastOneNonNull(arrayOfStrings)) { 
            throw new IllegalArgumentException("Violation of precondition: " +
                    "mostVowels. parameter may not equal null and must contain " +
                    "at least one none null value.");
        }

        int maxVow = -1;
        int biggest = -1;

        for(int x = 0; x < arrayOfStrings.length; x++){

            //checks for null elements
            if(arrayOfStrings[x] != null) {

                //grabs current word's vowel count
                int curVow = totalVowels(arrayOfStrings[x]);

                //checks which value is bigger
                if (curVow > maxVow) {
                    maxVow = curVow;
                    biggest = x;
                }
            }
        }
        return biggest;
    }

    //returns total vowels of given String
    private static int totalVowels(String word){
        int total = 0;

        //list of valid vowels to check for
        char[] vowels = {'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u'};

        //runs through given word
        for(int x = 0; x < word.length(); x++){
            char curr = word.charAt(x);

            //runs through vowels[] for ALL possible vowels
            for(int y = 0; y < vowels.length; y++){

                //checks for vowels
                if(curr == vowels[y]){
                    total++;
                }
            }
        }
        return total;
    }

    /**
     * Perform an experiment simulating the birthday problem.
     * Pick random birthdays for the given number of people. 
     * Return the number of pairs of people that share the
     * same birthday.<br>
     * @param numPeople The number of people in the experiment.
     * This value must be > 0
     * @param numDaysInYear The number of days in the year for this experiement.
     * This value must be > 0
     * @return The number of pairs of people that share a birthday 
     * after running the simulation.
     */

    //returns how many shared birthdays there are
    //need to run experiment tho
    public static int sharedBirthdays(int numPeople, int numDaysInYear) {
        // check preconditions
        if (numPeople <= 0 || numDaysInYear <= 0) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "sharedBirthdays. both parameters must be greater than 0. " +
                    "numPeople: " + numPeople + 
                    ", numDaysInYear: " + numDaysInYear);
        }

        //gets birthdays array
        int[] birthdays = randomBirthdays(numPeople, numDaysInYear);
        int shared = 0;

        //goes through array 2 times to check for matching days
        for(int x = 0; x < birthdays.length; x++){
            for(int y = x + 1; y < birthdays.length; y++){
                if(birthdays[x] == birthdays[y]){
                    shared++;
                }
            }
        }
        return shared;
    }

    //generates random values and returns values in array
    private static int[] randomBirthdays(int numPeople, int numDaysInYear){
        int[] birthdays = new int[numPeople];

        //runs through array to replace values
        for(int x = 0; x < birthdays.length; x++){

            //range of birthdays goes from 0 to given value of numDaysInYear
            birthdays[x] = (int) (Math.random() * numDaysInYear);
        }
        return birthdays;
    }

    /**
     * Determine if the chess board represented by board is a safe set up.
     * <p>pre: board != null, board.length > 0, board is a square matrix.
     * (In other words all rows in board have board.length columns.),
     * all elements of board == 'q' or '.'. 'q's represent queens, '.'s
     * represent open spaces.<br>
     * <p>post: return true if the configuration of board is safe,
     * that is no queen can attack any other queen on the board.
     * false otherwise.
     * the parameter <tt>board</tt> is not altered as a result of 
     * this method.<br>
     * @param board the chessboard
     * @return true if the configuration of board is safe,
     * that is no queen can attack any other queen on the board.
     * false otherwise.
     */

    //determines if given board is safe
    public static boolean queensAreSafe(char[][] board) {
        char[] validChars = {'q', '.'};
        // check preconditions
        if (board == null || board.length == 0 || !isSquare(board) 
                || !onlyContains(board, validChars)) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "queensAreSafe. The board may not be null, must be square, " +
                    "and may only contain 'q's and '.'s");        
        }

        //goes through all squares of board
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[r].length; c++){

                //checks for 'q'
                if(board[r][c] == validChars[0]){

                    //calls check to check queen's safety
                    if(!check(board,r,c, validChars[0])){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //checks all directions on board for unsafe queen positions
    private static boolean check(char[][] board, int row, int col, char piece){

        //all changes to go 8 directions
        final int[][] directions = {{0,1}, {1,0}, {1,-1}, {1,1},{0,-1}, {-1,0}, {-1,1}, {-1,-1}};

        //runs through all directions
        for(int d = 0; d < directions.length; d++){

            //changes row and col to check the directions
            int r = row + directions[d][0];
            int c = col + directions[d][1];

            //only runs if coords are in bounds of board
            while(inBounds(board, r, c)){

                //checks if piece is present
                if(board[r][c] == piece){
                    return false;
                } else {

                    //increases in given direction
                    r += directions[d][0];
                    c += directions[d][1];
                }
            }
        }
        return true;
    }

    //checks if coordinates are in bounds of given board
    private static boolean inBounds(char[][] board, int row, int col){
        if(row >= 0 && col >= 0
                && row < board.length && col < board[0].length){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Given a 2D array of ints return the value of the
     * most valuable contiguous sub rectangle in the 2D array.
     * The sub rectangle must be at least 1 by 1. 
     * <p>pre: <tt>mat != null, mat.length > 0, mat[0].length > 0,
     * mat</tt> is a rectangular matrix.
     * <p>post: return the value of the most valuable contiguous sub rectangle
     * in <tt>city</tt>.<br>
     * @param city The 2D array of ints representing the value of
     * each block in a portion of a city.
     * @return return the value of the most valuable contiguous sub rectangle
     * in <tt>city</tt>.
     */

    //returns the max value of a subplot in given 2d array
    public static int getValueOfMostValuablePlot(int[][] city) {
        // check preconditions
        if (city == null || city.length == 0 || city[0].length == 0 
                || !isRectangular(city) ) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "getValueOfMostValuablePlot. The parameter may not be null," +
                    " must value at least one row and at least" +
                    " one column, and must be rectangular."); 
        }        

        int max = city[0][0];

        //obtains start plots from top left of array
        for(int rStart = 0; rStart < city.length; rStart++){
            for(int cStart = 0; cStart < city[rStart].length; cStart++){

                //gets end plots from bottom right of array
                for(int rEnd = city.length-1; rEnd >= rStart; rEnd--){
                    for(int cEnd = city[rStart].length-1; cEnd >= cStart; cEnd--){

                        //calls addPlots to obtain each changing plots sum
                        int cur = addPlots(city, rStart, cStart, rEnd, cEnd);

                        //checks if current value is greater than max
                        if(cur > max){
                            max = cur;
                        }
                    }
                }
            }
        }
        return max;
    }

    //adds sum of given boundaries of 2d array
    private static int addPlots(int[][] city, int rStart, int cStart, int rEnd, int cEnd){
        int value = 0;
        //goes through given boundaries from the end to the starting coords
        for(int r = rEnd; r >= rStart; r--){
            for(int c = cEnd; c >= cStart; c--){
                value+= city[r][c];
            }
        }
        return value;
    }

    // !!!!! ***** !!!!! ***** !!!!! ****** !!!!! ****** !!!!! ****** !!!!!!
    // CS314 STUDENTS: Put your birthday problem experiment code here:

    //calculates percentage of shared birthdays
    public static void avgShared(int numExperiments, int numPeople, int numDays){
        final int HUNDRED = 100;
        int[] sharedTotals = new int[numExperiments];

        //runs loop based on numExperiments given
        for(int x = 0; x < numExperiments; x++){
            sharedTotals[x] = sharedBirthdays(numPeople, numDays);
        }

        //returns results statement
        System.out.printf("Num People: " + numPeople + ", number of experiments with " +
                "one or more shared birthday: " + getTotal(sharedTotals) + ", percentage: %.2f",
                getAverage(getTotal(sharedTotals), numExperiments, HUNDRED));
        System.out.print("%");
        System.out.println();

    }

    //counts how many experiments had at least one shared birthday
    private static int getTotal(int[] data){
        int total = 0;
        for(int x = 0; x < data.length; x++){
            if(data[x] >= 1 ){
                total++;
            }
        }
        return total;
    }

    //calculates average of shared birthdays
    private static double getAverage(int sharedTotal, int numExperiments, int hundred){
        return ((double) sharedTotal / numExperiments) * hundred;
    }

    /* 
     * pre: arrayOfStrings != null
     * post: return true if at least one element in arrayOfStrings is 
     * not null, otherwise return false. 
     */
    private static boolean atLeastOneNonNull(String[] arrayOfStrings) {
        // check precondition
        if (arrayOfStrings == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "atLeastOneNonNull. parameter may not equal null.");
        }
        boolean foundNonNull = false;
        int i = 0;
        while( !foundNonNull && i < arrayOfStrings.length ) {
            foundNonNull = arrayOfStrings[i] != null;
            i++;
        }
        return foundNonNull;
    }


    /* 
     * pre: mat != null
     * post: return true if mat is a square matrix, false otherwise
     */
    private static boolean isSquare(char[][] mat) {
        if (mat == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "isSquare. Parameter may not be null.");
        }
        final int numRows = mat.length;
        int row = 0;
        boolean isSquare = true;
        while (isSquare && row < numRows) {
            isSquare = ( mat[row] != null) && (mat[row].length == numRows);
            row++;
        }
        return isSquare;
    }


    /* 
     * pre: mat != null, valid != null
     * post: return true if all elements in mat are one of the 
     * characters in valid
     */
    private static boolean onlyContains(char[][] mat, char[] valid) {
        // check preconditions
        if (mat == null || valid == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "onlyContains. Parameters may not be null.");
        }
        String validChars = new String(valid);
        int row = 0;
        boolean onlyContainsValidChars = true;
        while (onlyContainsValidChars && row < mat.length) {
            int col = 0;
            while(onlyContainsValidChars && col < mat[row].length) {
                int indexOfChar = validChars.indexOf(mat[row][col]);
                onlyContainsValidChars = indexOfChar != -1;
                col++;
            }
            row++;
        }
        return onlyContainsValidChars;
    }


    /*
     * pre: mat != null, mat.length > 0
     * post: return true if mat is rectangular
     */
    private static boolean isRectangular(int[][] mat) {
        // check preconditions
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "isRectangular. Parameter may not be null and must contain" +
                    " at least one row.");
        }
        boolean correct = mat[0] != null;
        int row = 0;
        while(correct && row < mat.length) {
            correct = (mat[row] != null) 
                    && (mat[row].length == mat[0].length);
            row++;
        }
        return correct;
    }

    // make constructor private so no instances of CodeCamp can not be created
    private CodeCamp() {

    }
}
