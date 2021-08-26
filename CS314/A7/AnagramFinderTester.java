import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, Danica Padlan, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: dmp3357
 *  email address: danica_padlan@yahoo.com
 *  TA name: Noah
 *  Number of slip days I am using: 0
 */

public class AnagramFinderTester {


    private static final String testCaseFileName = "testCaseAnagrams.txt";
    private static final String dictionaryFileName = "d3.txt";

    /**
     * main method that executes tests.
     * @param args Not used.
     */
    public static void main(String[] args) {

        //letterInventoryTests();
        //cs314StudentTestsForLetterInventory();


        // tests on the anagram solver itself
        boolean displayAnagrams = getChoiceToDisplayAnagrams();
        AnagramSolver solver
                = new AnagramSolver(AnagramMain.readWords(dictionaryFileName));
        runAnagramTests(solver, displayAnagrams);

    }

    private static void letterInventoryTests() {
        LetterInventory lets1 = new LetterInventory("");
        Object expected = 0;
        Object actual = lets1.size();
        showTestResults(expected, actual, 1, " size on empty LetterInventory");

        expected = "";
        actual = lets1.toString();
        showTestResults(expected, actual, 2, " toString on empty LetterInventory");

        expected = 0;
        actual = lets1.get('A');
        showTestResults(expected, actual, 3, " get on empty LetterInventory");

        expected = 0;
        actual = lets1.get('z');
        showTestResults(expected, actual, 4, " get on empty LetterInventory");

        expected = true;
        actual = lets1.isEmpty();
        showTestResults(expected, actual, 5, " isEmpty on empty LetterInventory");

        expected = "";
        actual = lets1.toString();
        showTestResults(expected, actual, 6, " LetterInventory toString on empty LetterInventory");


        lets1 = new LetterInventory("mmmmm");
        expected = "mmmmm";
        actual = lets1.toString();
        showTestResults(expected, actual, 7, " LetterInventory toString");


        LetterInventory lets2 = new LetterInventory("\"Stanford University\"!! Jr<>(())G");
        expected = "adefgiijnnorrrssttuvy";
        actual = lets2.toString();
        showTestResults(expected, actual, 8, " LetterInventory constructor and toString");

        expected = 21;
        actual = lets2.size();
        showTestResults(expected, actual, 9, " LetterInventory size");

        expected = false;
        actual = lets2.isEmpty();
        showTestResults(expected, actual, 10, " LetterInventory isEmpty");

        expected = null;
        actual = lets2.subtract(lets1);
        showTestResults(expected, actual, 11, "LetterInventory subtract");

        lets1 = new LetterInventory("stand ---- ton");
        expected = "efgiijrrrsuvy";
        actual = lets2.subtract(lets1).toString();
        showTestResults(expected, actual, 12, "LetterInventory subtract");

        expected = 13;
        actual = lets2.subtract(lets1).size();
        showTestResults(expected, actual, 13, "LetterInventory size after subtract");

        expected = false;
        actual = lets2.isEmpty();
        showTestResults(expected, actual, 14, "LetterInventory isEmpty after subtract");

        expected = null;
        actual = lets1.subtract(lets2);
        showTestResults(expected, actual, 15, "LetterInventory subtract");

        expected = false;
        actual = lets1.equals(lets2);
        showTestResults(expected, actual, 16, "LetterInventory equals");

        lets1 = new LetterInventory("Ol33vIA33");
        expected = "aadefgiiijlnnoorrrssttuvvy";
        LetterInventory lets3 = lets1.add(lets2);
        actual = lets3.toString();
        showTestResults(expected, actual, 17, "LetterInventory add");

        expected = 26;
        actual = lets3.size();
        showTestResults(expected, actual, 18, "LetterInventory size after add");

        expected = false;
        actual = lets3.isEmpty();
        showTestResults(expected, actual, 19, "LetterInventory isEmpty after add");

        lets3 = lets1.add(lets2);
        LetterInventory lets4 = lets2.add(lets1);
        showTestResults(lets3, lets4, 20, "LetterInventory add and equals");

        expected = false;
        StringBuilder foo = new StringBuilder();
        actual = lets3.equals(foo);
        showTestResults(expected, actual, 21, "LetterInventory equals");

        expected = false;
        String str = "abeeills";
        lets3 = new LetterInventory("ISAbelle!!");
        actual = lets3.equals(str);
        showTestResults(expected, actual, 22, "LetterInventory equals");

        expected = true;
        lets2 = new LetterInventory("\\abeei\"ll0212s");
        lets3 = new LetterInventory("ISAbelle!!");
        actual = lets3.equals(lets2);
        showTestResults(expected, actual, 23, "LetterInventory equals");
    }


    private static void cs314StudentTestsForLetterInventory() {
        // CS314 Students, delete the above tests when you turn in your assignment
        // CS314 Students add your LetterInventory tests here.

        //constructor and size() Tests
        LetterInventory lets1 = new LetterInventory("ぃ謁ゅ");
        Object expected = 0;
        Object actual = lets1.size();
        showTestResults(expected, actual, 1, " size on empty LetterInventory");

        LetterInventory lets2 = new LetterInventory("D@n!c@_P@d|@n");
        expected = 6;
        actual = lets2.size();
        showTestResults(expected, actual, 2, " size on LetterInventory");

        //get() Tests
        expected = 0;
        actual = lets1.get('D');
        showTestResults(expected, actual, 3, " get from empty LetterInventory");

        expected = 2;
        actual = lets2.get('D');
        showTestResults(expected, actual, 4, " get from LetterInventory");

        //isEmpty Tests
        expected = true;
        actual = lets1.isEmpty();
        showTestResults(expected, actual, 5, " isEmpty from empty LetterInventory");

        expected = false;
        actual = lets2.isEmpty();
        showTestResults(expected, actual, 6, " isEmpty from LetterInventory");

        //toString Tests
        expected = "";
        actual = lets1.toString();
        showTestResults(expected, actual, 7, " toString from empty LetterInventory");

        expected = "cddnnp";
        actual = lets2.toString();
        showTestResults(expected, actual, 8, " toString from empty LetterInventory");

        //equals() Tests
        expected = false;
        actual = lets1.equals(3689346);
        showTestResults(expected, actual, 9, " equals from empty LetterInventory");

        lets1 = lets2;
        expected = true;
        actual = lets1.equals(lets2);
        showTestResults(expected, actual, 10, " equals from same LetterInventory");

        //add() Tests
        //gets new LetterInventory
        lets1 = new LetterInventory("|||||!!!#@#$!#@");
        LetterInventory alter = new LetterInventory("Z");
        lets1 = lets1.add(alter);
        expected = "z";
        actual = lets1.toString();
        showTestResults(expected, actual, 11, " add to empty LetterInventory");

        alter = new LetterInventory("pAtn@n@");
        lets2 = lets2.add(alter);
        expected = "acddnnnnppt";
        actual = lets2.toString();
        showTestResults(expected, actual, 12, " add to LetterInventory");

        //subtract() Tests
        //13,14
        alter = new LetterInventory("UT Austin CS Program");
        lets1 = lets1.subtract(alter);
        expected = null;
        actual = lets1;
        showTestResults(expected, actual, 13, " subtract from one-size LetterInventory");

        alter = new LetterInventory("daNpt");
        lets2 = lets2.subtract(alter);
        expected = "cdnnnp";
        actual = lets2.toString();
        showTestResults(expected, actual, 14, " subtract from LetterInventory");
    }

    private static boolean getChoiceToDisplayAnagrams() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter y or Y to display anagrams during tests: ");
        String response = console.nextLine();
        console.close();
        return response.length() > 0 
                && response.toLowerCase().charAt(0) == 'y';
    }

    private static boolean showTestResults(Object expected, Object actual, 
            int testNum, String featureTested) {
        
        System.out.println("Test Number " + testNum + " testing " 
                + featureTested);
        System.out.println("Expected result: " + expected);
        System.out.println("Actual result: " + actual);
        boolean passed = (actual == null && expected == null) 
                || (actual != null && actual.equals(expected));
        if (passed) {
            System.out.println("Passed test " + testNum);
        } else {
            System.out.println("!!! FAILED TEST !!! " + testNum);
        }
        System.out.println();
        return passed;
    }

    /**
     * Method to run tests on Anagram solver itself.
     * pre: the files d3.txt and testCaseAnagrams.txt are in the local directory
     * 
     * assumed format for file is
     * <NUM_TESTS>
     * <TEST_NUM>
     * <MAX_WORDS>
     * <PHRASE>
     * <NUMBER OF ANAGRAMS>
     * <ANAGRAMS>
     */
    private static void runAnagramTests(AnagramSolver solver, 
            boolean displayAnagrams) {
        
        int solverTestCases = 0;
        int solverTestCasesPassed = 0;
        Stopwatch st = new Stopwatch();
        try {
            Scanner sc = new Scanner(new File(testCaseFileName));
            final int NUM_TEST_CASES = Integer.parseInt(sc.nextLine().trim());
            System.out.println(NUM_TEST_CASES);
            for (int i = 0; i < NUM_TEST_CASES; i++) {
                // expected results
                TestCase currentTest = new TestCase(sc);
                solverTestCases++;
                st.start();
                // actual results
                List<List<String>> actualAnagrams 
                    = solver.getAnagrams(currentTest.phrase, currentTest.maxWords);
                st.stop();
                if(displayAnagrams) {
                    displayAnagrams("actual anagrams", actualAnagrams);
                    displayAnagrams("expected anagrams", currentTest.anagrams);
                }


                if(checkPassOrFailTest(currentTest, actualAnagrams))
                    solverTestCasesPassed++;
                System.out.println("Time to find anagrams: " + st.time());
                /* System.out.println("Number of calls to recursive helper method: " 
                        + NumberFormat.getNumberInstance(Locale.US).format(AnagramSolver.callsCount));*/
            }
            sc.close();
        } catch(IOException e) {
            System.out.println("\nProblem while running test cases on AnagramSolver. Check" +
                            " that file testCaseAnagrams.txt is in the correct location.");
            System.out.println(e);
            System.out.println("AnagramSolver test cases run: " + solverTestCases);
            System.out.println("AnagramSolver test cases failed: " 
                        + (solverTestCases - solverTestCasesPassed));
        }
        System.out.println("\nAnagramSolver test cases run: " + solverTestCases);
        System.out.println("AnagramSolver test cases failed: " + (solverTestCases - solverTestCasesPassed));
    }


    // print out all of the anagrams in a list of anagram
    private static void displayAnagrams(String type,
                    List<List<String>> anagrams) {

        System.out.println("Results for " + type);
        System.out.println("num anagrams: " + anagrams.size());
        System.out.println("anagrams: ");
        for (List<String> singleAnagram : anagrams) {
            System.out.println(singleAnagram);
        }
    }


    // determine if the test passed or failed
    private static boolean checkPassOrFailTest(TestCase currentTest,
                    List<List<String>> actualAnagrams) {

        boolean passed = true;
        System.out.println();
        System.out.println("Test number: " + currentTest.testCaseNumber);
        System.out.println("Phrase: " + currentTest.phrase);
        System.out.println("Word limit: " + currentTest.maxWords);
        System.out.println("Expected Number of Anagrams: " 
                    + currentTest.anagrams.size());
        if(actualAnagrams.equals(currentTest.anagrams)) {
            System.out.println("Passed Test");
        } else {
            System.out.println("\n!!! FAILED TEST CASE !!!");
            System.out.println("Recall MAXWORDS = 0 means no limit.");
            System.out.println("Expected number of anagrams: " 
                        + currentTest.anagrams.size());
            System.out.println("Actual number of anagrams:   " 
                        + actualAnagrams.size());
            if(currentTest.anagrams.size() == actualAnagrams.size()) {
                System.out.println("Sizes the same, "
                        + "but either a difference in anagrams or"
                        + " anagrams not in correct order.");
            }
            System.out.println();
            passed = false;
        }  
        return passed;
    }

    // class to handle the parameters for an anagram test 
    // and the expected result
    private static class TestCase {

        private int testCaseNumber;
        private String phrase;
        private int maxWords;
        private List<List<String>> anagrams;

        // pre: sc is positioned at the start of a test case
        private TestCase(Scanner sc) {
            testCaseNumber = Integer.parseInt(sc.nextLine().trim());
            maxWords = Integer.parseInt(sc.nextLine().trim());
            phrase = sc.nextLine().trim();
            anagrams = new ArrayList<>();
            readAndStoreAnagrams(sc);
        }

        // pre: sc is positioned at the start of the resulting anagrams
        // read in the number of anagrams and then for each anagram:
        //  - read in the line
        //  - break the line up into words
        //  - create a new list of Strings for the anagram
        //  - add each word to the anagram
        //  - add the anagram to the list of anagrams
        private void readAndStoreAnagrams(Scanner sc) {
            int numAnagrams = Integer.parseInt(sc.nextLine().trim());
            for (int j = 0; j < numAnagrams; j++) {
                String[] words = sc.nextLine().split("\\s+");
                ArrayList<String> anagram = new ArrayList<>();
                for (String st : words) {
                    anagram.add(st);
                }
                anagrams.add(anagram);
            }
            assert anagrams.size() == numAnagrams 
                    : "Wrong number of angrams read or expected";
        }
    }
}
