import java.util.Arrays;
//MathMatrix.java - CS314 Assignment 2

/*  Student information for assignment:
*
*  On my honor, Danica Padlan, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: dmp3357
*  email address: danica_padlan@yahoo.com
*  Unique section number: 52288
*  Number of slip days I am using: 0
*/

/**
 * A class that models systems of linear equations (Math Matrices)
 * as used in linear algebra.
 */
public class MathMatrix {

    private int[][] val;

    /**
     * create a MathMatrix with cells equal to the values in mat.
     * A "deep" copy of mat is made.
     * Changes to mat after this constructor do not affect this
     * Matrix and changes to this MathMatrix do not affect mat
     * @param  mat  mat !=null, mat.length > 0, mat[0].length > 0,
     * mat is a rectangular matrix
     */
    public MathMatrix(int[][] mat) {

        //catches faulty pre-conditions
        if(mat.length == 0 || mat[0].length == 0 || mat == null){
            throw new IllegalArgumentException("Violation of precondition: main, " +
                    "mat !=null, mat.length > 0, mat[0].length > 0, mat is a rectangular matrix");
        }

        //sets and fills 2d array
        val = copy(mat);
    }

    /**
     * create a MathMatrix of the specified size with all cells set to the intialValue.
     * <br>pre: numRows > 0, numCols > 0
     * <br>post: create a matrix with numRows rows and numCols columns. 
     * All elements of this matrix equal initialVal.
     * In other words after this method has been called getVal(r,c) = initialVal 
     * for all valid r and c.
     * @param numRows numRows > 0
     * @param numCols numCols > 0
     * @param initialVal all cells of this Matrix are set to initialVal
     */
    public MathMatrix(int numRows, int numCols, int initialVal) {

        //catches faulty pre-conditions
        if(numRows == 0 || numCols == 0){
            throw new IllegalArgumentException("Violation of precondition: main, " +
                    "numRows > 0, numCols > 0");
        }

        val = new int[numRows][numCols];

        //runs through val 2d array
        for(int r = 0; r < numRows; r++){
            for(int c = 0; c < numCols; c++){

                //sets val coords to given val
                val[r][c] = initialVal;
            }
        }
    }

    //implements deep copy from given array to new array
    private int[][] copy(int[][] mat){
        int[][] deepCopy = new int[mat.length][mat[0].length];

        //runs through deepCopy 2d array
        for(int r = 0; r < mat.length; r++){
            for(int c = 0; c < mat[r].length; c++){

                //copies values from mat to temp coords
                deepCopy[r][c] = mat[r][c];
            }
        }
        return deepCopy;
    }

    /**
     * Get the number of rows.
     * @return the number of rows in this MathMatrix
     */
    public int getNumRows() {
        return val.length;
    }

    /**
     * Get the number of columns.
     * @return the number of columns in this MathMatrix
     */
    public int getNumColumns(){ return val[0].length; }

    /**
     * get the value of a cell in this MathMatrix.
     * <br>pre: row  0 <= row < getNumRows(), col  0 <= col < getNumColumns()
     * @param  row  0 <= row < getNumRows()
     * @param  col  0 <= col < getNumColumns()
     * @return the value at the specified position
     */
    public int getVal(int row, int col) {

        //catches faulty pre-conditions
        if(row < 0 || row >= getNumRows() || col < 0 || col >= getNumColumns()){
            throw new IllegalArgumentException("Violation of precondition: getVal, " +
                    "row  0 <= row < getNumRows(), col  0 <= col < getNumColumns()");
        }
        return val[row][col];
    }


    /**
     * implements MathMatrix addition, (this MathMatrix) + rightHandSide.
     * <br>pre: rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * @return a new MathMatrix that is the result of adding this Matrix to rightHandSide.
     * The number of rows in the returned Matrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned Matrix is equal to the number of columns in this MathMatrix.
     */
    public MathMatrix add(MathMatrix rightHandSide){

        //catches faulty pre-conditions
        if(rightHandSide.getNumRows() != getNumRows() || rightHandSide.getNumColumns() != getNumColumns()){
            throw new IllegalArgumentException("Violation of precondition: add, " +
                    "rightHandSide.getNumRows() = getNumRows(), rightHandSide.numColumns() = getNumColumns()");
        }

        int[][] temp = new int[getNumRows()][getNumColumns()];

        //runs through temp 2d array
        for(int r = 0; r < temp.length; r++){
            for(int c = 0; c < temp[r].length; c++){

                //adds values of same coords from the 2 2d arrays into new 2d array
                temp[r][c] = val[r][c] + rightHandSide.getVal(r,c);
            }
        }
        return new MathMatrix(temp);
    }

    /**
     * implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
     * <br>pre: rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * @return a new MathMatrix that is the result of subtracting rightHandSide from this MathMatrix.
     * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned MathMatrix is equal to the number of columns in this MathMatrix.
     */
    public MathMatrix subtract(MathMatrix rightHandSide){

        //catches faulty pre-conditions
        if(rightHandSide.getNumRows() != getNumRows() || rightHandSide.getNumColumns() != getNumColumns()){
            throw new IllegalArgumentException("Violation of precondition: subtract, " +
                    "rightHandSide.getNumRows() = getNumRows(), rightHandSide.numColumns() = getNumColumns()");
        }

        int[][] temp = new int[val.length][val[0].length];

        //runs through temp 2d array
        for(int r = 0; r < temp.length; r++){
            for(int c = 0; c < temp[r].length; c++){

                //subtracts values of same coords from the 2 2d arrays into new 2d array
                temp[r][c] = val[r][c] - rightHandSide.getVal(r,c);
            }
        }

        return new MathMatrix(temp);
    }

    /**
     * implements matrix multiplication, (this MathMatrix) * rightHandSide.
     * <br>pre: rightHandSide.getNumRows() = getNumColumns()
     * <br>post: This method should not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumColumns()
     * @return a new MathMatrix that is the result of multiplying this MathMatrix and rightHandSide.
     * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned MathMatrix is equal to the number of columns in rightHandSide.
     */
    public MathMatrix multiply(MathMatrix rightHandSide){

        //catches faulty pre-conditions
        if(rightHandSide.getNumRows() != getNumColumns()){
            throw new IllegalArgumentException("Violation of precondition: multiply, " +
                    "rightHandSide.getNumRows() = getNumColumns()");
        }

        //creates new array based off of current array's rows and parameter array's column
        int[][] temp = new int[getNumRows()][rightHandSide.getNumColumns()];

        //goes through temp matrix's coords
        for(int rTemp = 0; rTemp < temp.length; rTemp++){
            for(int cTemp = 0; cTemp < temp[0].length; cTemp++){

                //multiplies and adds array's products
                int sum = 0;
                for(int x = 0; x < getNumColumns(); x++){
                    sum+= val[rTemp][x] * rightHandSide.getVal(x,cTemp);
                }

                //sets sum to temp coords
                temp[rTemp][cTemp] = sum;
            }
        }
        return new MathMatrix(temp);
    } 

    /**
     * Create and return a new Matrix that is a copy
     * of this matrix, but with all values multiplied by a scale
     * value.
     * <br>pre: none
     * <br>post: returns a new Matrix with all elements in this matrix 
     * multiplied by factor. 
     * In other words after this method has been called 
     * returned_matrix.getVal(r,c) = original_matrix.getVal(r, c) * factor
     * for all valid r and c.
     * @param factor the value to multiply every cell in this Matrix by.
     * @return a MathMatrix that is a copy of this MathMatrix, but with all
     * values in the result multiplied by factor.
     */
    public MathMatrix getScaledMatrix(int factor) {
        int[][] temp = copy(val);

        //goes through temp's coords
        for(int r = 0; r < temp.length; r++){
            for(int c = 0; c < temp[r].length; c++){

                //multiplies current value by given factor
                temp[r][c] *= factor;
            }
        }
        return new MathMatrix(temp);
    }

    /**
     * accessor: get a transpose of this MathMatrix. 
     * This Matrix is not changed.
     * <br>pre: none
     * @return a transpose of this MathMatrix
     */
    public MathMatrix getTranspose() {
        //swaps rows and columns of new array
        int[][] temp = new int[val[0].length][val.length];

        //goes through temp array
        for(int r = 0; r < temp.length; r++){
            for(int c = 0; c < temp[r].length; c++){

                //sets previous values to new coords
                temp[r][c] = val[c][r];
            }
        }
        return new MathMatrix(temp);
    }

    /**
     * override equals.
     * @return true if rightHandSide is the same size as this MathMatrix and all values in the
     * two MathMatrix objects are the same, false otherwise
     */
    public boolean equals(Object rightHandSide){
        /* CS314 Students. The following is standard equals
         * method code. We will learn about in the coming weeks.
         */
        boolean result = false;
        // We use getClass instead of instanceof because we only want a MathMatrix to equal 
        // another MathMatrix as opposed to any possible sub classes. We would
        // use instance of if we were implementing am interface and wanted to equal
        // other objects that are instances of that interface but not necessarily
        // MathMatrix objects.
        if( rightHandSide != null && this.getClass() == rightHandSide.getClass()){
            // rightHandSide is a non null MathMatrix
            MathMatrix otherMatrix = (MathMatrix) rightHandSide;

            //checks if both 2d arrays are same size
            if(getNumRows() == otherMatrix.getNumRows() || getNumColumns() == otherMatrix.getNumColumns()){

                //goes through both arrays
                for(int r = 0; r < val.length; r++){
                    for(int c = 0; c < val[r].length; c++){

                        //sets boolean value based on met conditions
                        result = (val[r][c] == otherMatrix.getVal(r,c));
                    }
                }
            }
            // I recommend not changing any other code.
        }
        return result;
    }

    /**
     * override toString.
     * @return a String with all elements of this MathMatrix. 
     * Each row is on a separate line.
     * Spacing based on longest element in this Matrix.
     */
    public String toString() {

        //numSpace format given by assignment page
        final int numSpace = 5;
        StringBuilder result = new StringBuilder();

        //goes through 2d array
        for(int r = 0; r < val.length; r++){
            result.append("|");
            for(int c = 0; c < val[r].length; c++){
                String temp = "" + val[r][c];

                //calculates and appends " " according to given numSpace
                int spaceLim = numSpace - temp.length();
                for(int space = 0; space < spaceLim; space++){
                    result.append(" ");
                }

                //adds value after spaces
                result.append(" " + temp);
            }

            //ends and newlines to new row for result
            result.append("|\n");
        }
        return result.toString();
    }

    /**
     * Return true if this MathMatrix is upper triangular. To
     * be upper triangular all elements below the main 
     * diagonal must be 0.<br>
     * pre: this is a square matrix. getNumRows() == getNumColumns()  
     * @return <tt>true</tt> if this MathMatrix is upper triangular,
     * <tt>false</tt> otherwise. 
     */
    public boolean isUpperTriangular(){

        //catches faulty pre-conditions
        if(getNumRows() != getNumColumns()){
            throw new IllegalArgumentException("Violation of pre-condition: isUpperTriangular, " +
                    "getNumRows() == getNumColumns()");
        }

        //goes through 2d array in diagonal-stairs pattern
        for(int r = 0; r < val.length; r++){
            for(int c = 0; c < r; c++){

                //checks for non-zero values
                if(val[r][c] != 0){
                    return false;
                }
            }
        }
        return true;
    }

    // method to ensure mat is rectangular. It is public so that
    // tester classes can use it. 
    // pre: mat != null, mat has at least one row
    // return true if all rows in mat have the same
    // number of columns false otherwise.
    public static boolean rectangularMatrix(int[][] mat) {

        //catches faulty pre-conditions
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("argument mat may not be null and must "
                    + " have at least one row. mat = " + Arrays.toString(mat));
        }
        boolean isRectangular = true;
        int row = 1;
        final int COLUMNS = mat[0].length;
        while (isRectangular && row < mat.length) {
            isRectangular = (mat[row].length == COLUMNS);
            row++;
        }
        return isRectangular;
    }
}
