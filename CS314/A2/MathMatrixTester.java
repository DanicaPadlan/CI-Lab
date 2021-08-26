import java.util.Random;

/*  Student information for assignment:
 *
 *  UTEID: dmp3357
 *  email address: danica_padlan@yahoo.com
 *  Grader name: Noah
 *  Number of slip days I am using: 0
 */

/* CS314 Students. Put your experiment results and
 * answers to questions here.
 *  //Data
    Add Test Value: 600, Time: 1.09 seconds
    Add Test Value: 1200, Time: 3.29 seconds
    Multiply Test Value: 250, Time: 1.9 seconds
    Multiply Test Value: 500, 21.41 seconds

    //Questions
    1. I think the second test will take 2.18 seconds to run (2 times the original time). After running the test, it turns out it will take 3.29 seconds.
    2. My add method’s big O is O(N^2). My timing data supports this result because the second test tripled the first test’s time.
    3. I think the second test will take 19 seconds to run (10 times the original time). After running the tests, turns out it will take 21.41 seconds.
    4. My multiply method’s big O is O(N^3).  My timing data supports this result because the second test multiplied by 10 in comparison to the first test’s time.
    5. The size of a 25000 by 25000 2d array causes my program to run out of memory. I estimate that it takes around 2.5e9 bytes to run in Java

 */

/**
 * A class to run tests on the MathMatrix class
 */
public class MathMatrixTester {

    /**
     * main method that runs simple test on the MathMatrix class
     *
     * @param args not used
     */
    public static void main(String[] args) {
        int[][] data1 = {{1, 3, 6},
                        {7, 9, 11}};
        int[][] data2 = {{2, 4},
                        {6, 8},
                        {10,12}};
        int[][] expectedArray;

        // CS314 Students. When ready delete the above tests
        // and add your 22 tests here.
        MathMatrix mat1 = new MathMatrix(data1);
        MathMatrix mat2 = new MathMatrix(data2);

        //test 1, check the numRows() of mat1
        int actual = mat1.getNumRows();
        int expectedVal = 2;
        System.out.print("Test 1, getNumRows(): expected " + expectedVal + ", actual: " + actual + ".");
        if(actual == expectedVal){
            System.out.println(" Passed test 1.");
        } else{
            System.out.println(" *FAILED* test 1.");
        }

        //test 2, check the numCol of mat1
        actual = mat1.getNumColumns();
        expectedVal = 3;
        System.out.print("Test 2, getNumCols(): expected " + expectedVal + ", actual: " + actual + ".");
        if(actual == expectedVal){
            System.out.println(" Passed test 2.");
        } else{
            System.out.println(" *FAILED* test 2.");
        }

        //test 3, check the numRows of mat2
        actual = mat2.getNumRows();
        expectedVal = 3;
        System.out.print("Test 3, getNumRows(): expected " + expectedVal + ", actual: " + actual + ".");
        if(actual == expectedVal){
            System.out.println(" Passed test 3.");
        } else{
            System.out.println(" *FAILED* test 3.");
        }

        //test 4, check the numCol of mat2
        actual = mat2.getNumColumns();
        expectedVal = 2;
        System.out.print("Test 4, getNumCols(): expected " + expectedVal + ", actual: " + actual + ".");
        if(actual == expectedVal){
            System.out.println(" Passed test 4.");
        } else{
            System.out.println(" *FAILED* test 4.");
        }

        //test 5, checks getVal of mat1
        actual = mat1.getVal(1,1);
        expectedVal = 9;
        System.out.print("Test 5, getVal(): expected " + expectedVal + ", actual: " + actual + ".");
        if(actual == expectedVal){
            System.out.println(" Passed test 5.");
        } else{
            System.out.println(" *FAILED* test 5.");
        }

        //test 6, checks getVal of mat2
        actual = mat2.getVal(2,1);
        expectedVal = 12;
        System.out.print("Test 6, getVal(): expected " + expectedVal + ", actual: " + actual + ".");
        if(actual == expectedVal){
            System.out.println(" Passed test 6.");
        } else{
            System.out.println(" *FAILED* test 6.");
        }

        data1 = new int[][] {{1,2},
                            {2,3},
                            {3,4},
                            {4,5}};
        mat1 = new MathMatrix(data1);
        data2 = new int[][] {{1,3,5},
                            {2,4,6}};
        mat2 = new MathMatrix(data2);
        expectedArray = new int[][] {{5,11,17},
                                    {8,18,28},
                                    {11,25,39},
                                    {14,32,50}};
        //test 7, checks multiply from mat2 to mat1
        mat1 = mat1.multiply(mat2);
        printTestResult(get2DArray(mat1),expectedArray,7,"multiply()");

        //test 8, getTranspose for mat1;
        mat1 = mat1.getTranspose();
        expectedArray = new int[][] {{5,8,11,14},
                                    {11,18,25,32},
                                    {17,28,39,50}};
        printTestResult(get2DArray(mat1),expectedArray,8,"getTranspose()");

        //test 9, getTranspose for mat2;
        mat2 = mat2.getTranspose();
        expectedArray = new int[][] {{1,2},
                                    {3,4},
                                    {5,6}};
        printTestResult(get2DArray(mat2),expectedArray,9,"getTranspose()");

        //test 10, multiply from mat2 to mat1
        data1 = new int[][] {{1,0,1},
                            {0,1,0}};
        mat1 = mat2;
        mat2 = new MathMatrix(data1);
        mat1 = mat1.multiply(mat2);
        expectedArray = new int[][] {{1,2,1},
                                    {3,4,3},
                                    {5,6,5}};
        printTestResult(get2DArray(mat1),expectedArray,10,"multiply()");

        data1 = new int[][] {{1,1,1},
                            {0,1,1},
                            {0,0,0}};
        mat1 = new MathMatrix(data1);
        data2 = new int[][] {{1,2,3},
                            {2,3,1},
                            {3,2,1}};
        mat2 = new MathMatrix(data2);

        //test 11, isUpperTriangular() for mat1
        System.out.print("Test 11, isUpperTriangular.");
        if(mat1.isUpperTriangular() == true){
            System.out.println(" Passed test 11.");
        } else{
            System.out.println(" *FAILED* test 11.");
        }

        //test 12, isUpperTriangular() for mat2
        System.out.print("Test 12, isUpperTriangular.");
        if(mat2.isUpperTriangular() == false){
            System.out.println(" Passed test 12.");
        } else{
            System.out.println(" *FAILED* test 12.");
        }

        //test 13, add() to mat1
        mat1 = mat1.add(mat2);
        expectedArray = new int[][] {{2,3,4},
                                    {2,4,2},
                                    {3,2,1}};
        printTestResult(get2DArray(mat1),expectedArray,13,"add()");

        //test 14, add() to mat2
        mat2 = mat2.add(mat1);
        expectedArray = new int[][] {{3,5,7},
                                    {4,7,3},
                                    {6,4,2}};
        printTestResult(get2DArray(mat2),expectedArray,14,"add()");

        //test 15, subtract()
        mat2 = mat2.subtract(mat1);
        expectedArray = new int[][] {{1,2,3},
                                    {2,3,1},
                                    {3,2,1}};
        printTestResult(get2DArray(mat2),expectedArray,15,"subtract()");

        //test 16, subtract()
        mat2 = mat2.subtract(mat2);
        expectedArray = new int[][] {{0,0,0},
                                    {0,0,0},
                                    {0,0,0}};
        printTestResult(get2DArray(mat2),expectedArray,16,"subtract()");

        //test 17, getScaledMatrix()
        mat2 = mat2.getScaledMatrix(2);
        expectedArray = new int[][] {{0,0,0},
                                    {0,0,0},
                                    {0,0,0}};
        printTestResult(get2DArray(mat2),expectedArray,17,"getScaledMatrix()");

        //test 18, equals()
        System.out.print("Test 18, equals().");
        if(mat2.equals(mat2)){
            System.out.println(" Passed test 18.");
        } else{
            System.out.println(" *FAILED* test 18.");
        }

        //test 19, getScaledMatrix()
        data1 = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
        mat1 = new MathMatrix(data1);
        mat1 = mat1.getScaledMatrix(5);
        expectedArray = new int[][] {{5,10,15},
                                    {20,25,30},
                                    {35,40,45}};
        printTestResult(get2DArray(mat1),expectedArray,19,"getScaledMatrix()");

        //test 20, equals()
        data2 = new int[][] {{5,10,15},
                            {20,25,30},
                            {35,40,45}};
        mat2 = new MathMatrix(data2);

        System.out.print("Test 20, equals().");
        if(mat1.equals(mat2)){
            System.out.println(" Passed test 20.");
        } else{
            System.out.println(" *FAILED* test 20.");
        }

        //test 21, toString()
        actual = mat1.toString().length();
        expectedVal = 63;
        System.out.print("Test 21, toString().");
        if(actual == expectedVal){
            System.out.println(" Passed test 21.");
        } else{
            System.out.println(" *FAILED* test 21.");
        }

        //test 22, toString()
        data2 = new int[][] {{1, 3, 6},
                            {7, 9, 11}};
        mat2 = new MathMatrix(data2);
        actual = mat2.toString().length();
        expectedVal = 42;
        System.out.print("Test 22, toString().");
        if(actual == expectedVal){
            System.out.println(" Passed test 22.");
        } else{
            System.out.println(" *FAILED* test 22.");
        }

        //Experiment 1
        /*
         Stopwatch watch = new Stopwatch();
        data1 = new int[1200][1200];
        data2 = new int[1200][1200];
        randomize(data1);
        randomize(data2);

        MathMatrix mat1 = new MathMatrix(data1);
        MathMatrix mat2 = new MathMatrix(data2);

        watch.start();
        addExp(mat1, mat2, 1000);
        watch.stop();
        System.out.println(watch.toString());

        //Experiment 2
        data1 = new int[500][500];
        data2 = new int[500][500];
        randomize(data1);
        randomize(data2);

        MathMatrix mat1 = new MathMatrix(data1);
        MathMatrix mat2 = new MathMatrix(data2);

        watch.start();
        multExp(mat1, mat2, 1000);
        watch.stop();
        System.out.println(watch.toString());
         */
    }

    //Experiment Helper Methods
    /*
    //fills 2d array with random values
    private static void randomize(int[][] mat){
        for(int r = 0; r < mat.length; r++){
            for(int c = 0; c < mat[r].length; c++){
                mat[r][c] = (int) (Math.random() * 100);
            }
        }
    }

    //runs Experiment 1 tests
    private static void addExp(MathMatrix mat1, MathMatrix mat2, int numTimes){
        for(int x = 0; x < numTimes; x++){
            mat1.add(mat2);
        }
    }

    //runs Experiment 2 tests
    private static void multExp(MathMatrix mat1, MathMatrix mat2, int numTimes){
        for(int x = 0; x < numTimes; x++){
            mat1.multiply(mat2);
        }
    }

     */

    // method that sums elements of mat, may overflow int!
    // pre: mat != null
    private static int sumVals(MathMatrix mat) {
        if (mat == null) {
            throw new IllegalArgumentException("mat may not be null");
        } 
        int result = 0;
        final int ROWS =  mat.getNumRows();
        final int COLS = mat.getNumColumns();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                result += mat.getVal(r, c); // likely to overflow, but can still do simple check
            }
        }
        return result;
    }

    // create a matrix with random values
    // pre: rows > 0, cols > 0, randNumGen != null
    public static MathMatrix createMat(Random randNumGen, int rows,
            int cols, final int LIMIT) {

        if (randNumGen == null) {
            throw new IllegalArgumentException("randomNumGen variable may no be null");
        } else if(rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("rows and columns must be greater than 0. " +
                    "rows: " + rows + ", cols: " + cols);
        }

        int[][] temp = new int[rows][cols];
        final int SUB = LIMIT / 4;
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                temp[r][c] = randNumGen.nextInt(LIMIT) - SUB;
            }
        }

        return new MathMatrix(temp);
    }

    private static void printTestResult(int[][] data1, int[][] data2, int testNum, String testingWhat) {
        System.out.print("Test number " + testNum + " tests the " + testingWhat +". The test ");
        String result = equals(data1, data2) ? "passed" : "failed";
        System.out.println(result);
    }

    // pre: m != null, m is at least 1 by 1 in size
    // return a 2d array of ints the same size as m and with 
    // the same elements
    private static int[][] get2DArray(MathMatrix m) {
        //check precondition
        if  ((m == null) || (m.getNumRows() == 0) 
                || (m.getNumColumns() == 0)) {
            throw new IllegalArgumentException("Violation of precondition: get2DArray");
        }

        int[][] result = new int[m.getNumRows()][m.getNumColumns()];
        for(int r = 0; r < result.length; r++) {
            for(int c = 0; c < result[0].length; c++) {
                result[r][c] = m.getVal(r,c);
            }
        }
        return result;
    }

    // pre: data1 != null, data2 != null, data1 and data2 are at least 1 by 1 matrices
    //      data1 and data2 are rectangular matrices
    // post: return true if data1 and data2 are the same size and all elements are
    //      the same
    private static boolean equals(int[][] data1, int[][] data2) {
        //check precondition
        if((data1 == null) || (data1.length == 0) 
                || (data1[0].length == 0) || !rectangularMatrix(data1)
                ||  (data2 == null) || (data2.length == 0)
                || (data2[0].length == 0) || !rectangularMatrix(data2)) {
            throw new IllegalArgumentException( "Violation of precondition: equals check on 2d arrays of ints");
        }
        boolean result = (data1.length == data2.length) && (data1[0].length == data2[0].length);
        int row = 0;
        while (result && row < data1.length) {
            int col = 0;
            while (result && col < data1[0].length) {
                result = (data1[row][col] == data2[row][col]);
                col++;
            }
            row++;
        }

        return result;
    }


    // method to ensure mat is rectangular
    // pre: mat != null, mat is at least 1 by 1
    private static boolean rectangularMatrix( int[][] mat ) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + " Parameter mat may not be null" 
                    + " and must be at least 1 by 1");
        }
        return MathMatrix.rectangularMatrix(mat);
    }
}

