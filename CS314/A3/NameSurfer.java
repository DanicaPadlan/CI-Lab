/*
 * Student information for assignment: Replace <NAME> in the following with your
 * name. You are stating, on your honor you did not copy any other code on this
 * assignment and have not provided your code to anyone. 
 * 
 * On my honor,Danica Padlan , this programming assignment is my own work
 * and I have not provided this code
 * to any other student. 
 * 
 * UTEID: dmp3357
 * email address: danica_padlan@yahoo.com
 * Number of slip days I am using: 0
 */

import javax.naming.Name;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class NameSurfer {


    // CS314 students, explain your menu option 7 here:
    /*
    My option 7 is a random name generator. The user is given the freedom to choose the starting letter of the random name.
    After it displays a random name that has the user’s desired first letter, the user is given the choice to view the
    name’s information or to re-roll for another name under the same criteria.

    The intention to create this option is to allow the indecisive users to be given an option to generate any name.
    In my mind, I think of some clients as new parents who don’t have an idea on what to name their new baby.
    Giving them the freedom to choose the first letter can help concentrate their choices into a smaller list of names.
    If they love the name they can see the name’s popularity ranking over the decades. If they don’t like it,
    they can re-roll for another name, or they can exit and go back to the main menu.

    While this option is similar to option 1,
    I believe option 1 can still show an overwhelming list to look at since it can get names that do not start
    with the user’s given String (Like ISA can result in Luisa or Melisa). My option’s purpose is to spark the user with ideas
    of potential names. It is not guaranteed that users will be completely satisfied solely with Option 7’s given choices
    but if users like parts of the randomly generated name, they can use option 1 to search up other names that have the desired partial name.
    My option 7 is not meant to replace option 1 but to have it aid the user’s ideas on potential names to look up.
     */

    // CS314 students, Explain your interesting search / trend here:
    /*
        An interesting trend I found was how some president’s last names became popular choices for children’s names:
        During the 1960s, the name “Kennedy” appeared in the top 1000 for the first time between 1910 - 2000 with a rank of 752. In the 1990s, “Reagan” appeared in the top 1000 with a rank of 607 and climbed to rank 285 in the 2000s.
        I believe the reason for this trend was due to the president’s popularity during their running term. John F. Kennedy was the hot topic of the 60s due to being the youngest president of the United States and his assassination. Ronald Reagan was popular due to his efforts of communicating to the USSR leading to the end of the Cold War.
        Both presidents were popular enough that their last names became popular children’s names during the decades they were in office.
     */

    // CS314 students, add test code for NameRecord class here:
    /*
    public static void myTests(){
        //NameRecords Test
        String testName1 = "danica";
        int baseDecade1 = 2013;
        int[] arrTestRanks1 = new int[] {50, 62, 107, 290, 381, 571, 668};
        NameRecord test = new NameRecord(testName1, baseDecade1, arrayToList(arrTestRanks1));

        //getName test 1
        String expectedText = "danica";
        String actualText = test.getName();
        System.out.println();
        System.out.println("Test 1: getName(), Expected: " + expectedText + " Actual: " + actualText);
        if(expectedText.equals(actualText)){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //getBaseDecade test 2
        int expected = 2013;
        int actual = test.getBaseDecade();
        System.out.println("Test 2: getBaseDecade(), Expected: " + expected + " Actual: " + actual);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //getRank test 3
        expected = 290;
        actual = test.getRank(3);
        System.out.println("Test 3: getRanks(), Expected: " + expected + " Actual: " + actual);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //bestDecade test 4
        expected = 2013;
        actual = test.bestDecade();
        System.out.println("Test 4: bestDecade(), Expected: " + expected + " Actual: " + actual);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //numTopThousand test 5
        expected = 7;
        actual = test.numTopThousand();
        System.out.println("Test 5: numTopThousand(), Expected: " + expected + " Actual: " + actual);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //topThousandEveryDecade test 6
        boolean exp = true;
        boolean act = test.topThousandEveryDecade();
        System.out.println("Test 6: topThousandEveryDecade(), Expected: " + exp + " Actual: " + act);
        if(exp == act){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //topThousandOnce test 7
        exp = false;
        act = test.topThousandOnce();
        System.out.println("Test 7: topThousandOnce(), Expected: " + exp + " Actual: " + act);
        if(exp == act){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //increaseRank test 8
        exp = false;
        act = test.increaseRank();
        System.out.println("Test 8: increaseRank(), Expected: " + exp + " Actual: " + act);
        if(exp == act){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //decreaseRank test 9
        exp = true;
        act = test.decreaseRank();
        System.out.println("Test 9: decreaseRank(), Expected: " + exp + " Actual: " + act);
        if(exp == act){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //toString method test 10
        expectedText = "danica\n2013: 50\n2023: 62\n2033: 107\n2043: 290\n2053: 381\n2063: 571\n2073: 668\n";
        actualText = test.toString();
        System.out.println("Test 10: toString(),");
        System.out.println("Expected:");
        System.out.println(expectedText);
        System.out.println("Actual:");
        System.out.println(actualText);
        if(expectedText.equals(actualText)){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //new data change
        String testName2 = "Cassie";
        int[] arrTestRanks2 = new int[] {652, 559, 276, 255, 199, 182, 120};
        NameRecord test2 = new NameRecord(testName2, baseDecade1, arrayToList(arrTestRanks2));

        //getName test 11
        expectedText = "Cassie";
        actualText = test2.getName();
        System.out.println();
        System.out.println("Test 11: getName(), Expected: " + expectedText + " Actual: " + actualText);
        if(expectedText.equals(actualText)){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }
        //getBaseDecade test 12
        expected = 2013;
        actual = test2.getBaseDecade();
        System.out.println("Test 12: getBaseDecade(), Expected: " + expected + " Actual: " + actual);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //getRank test 13
        expected = 559;
        actual = test2.getRank(1);
        System.out.println("Test 13: getRank(), Expected: " + expected + " Actual: " + actual);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //bestDecade test 14
        expected = 2073;
        actual = test2.bestDecade();
        System.out.println("Test 14: bestDecade(), Expected: " + expected + " Actual: " + actual);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //numTopThousand test 15
        expected = 7;
        actual = test2.numTopThousand();
        System.out.println("Test 15: numTopThousand(), Expected: " + expected + " Actual: " + actual);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //topThousandEveryDecade test 16
        exp = true;
        act = test2.topThousandEveryDecade();
        System.out.println("Test 16: numTopThousand(), Expected: " + exp + " Actual: " + act);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //topThousandOnce test 17
        exp = false;
        act = test2.topThousandOnce();
        System.out.println("Test 17: numTopThousand(), Expected: " + exp + " Actual: " + act);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //increaseRank test 18
        exp = true;
        act = test2.increaseRank();
        System.out.println("Test 18: increaseRank(), Expected: " + exp + " Actual: " + act);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //decreaseRank test 19
        exp = false;
        act = test2.decreaseRank();
        System.out.println("Test 18: decreaseRank(), Expected: " + exp + " Actual: " + act);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //toString method test 20
        expectedText = "Cassie\n2013: 652\n2023: 559\n2033: 276\n2043: 255\n2053: 199\n2063: 182\n2073: 120\n";
        actualText = test2.toString();
        System.out.println("Test 20: toString(),");
        System.out.println("Expected:");
        System.out.println(expectedText);
        System.out.println("Actual:");
        System.out.println(actualText);
        if(expectedText.equals(actualText)){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //compareTo method test 21
        expected = -33;
        actual = test2.compareTo(test);
        System.out.println("Test 21: compareTo(), Expected: " + expected + " Actual: " + actual);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        test = new NameRecord(testName2, baseDecade1, arrayToList(arrTestRanks2));
        //compareTo method test 22
        expected = 0;
        actual = test.compareTo(test);
        System.out.println("Test 22: compareTo(), Expected: " + expected + " Actual: " + actual);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        testName1 = "LesserBot";
        arrTestRanks1 = new int[] {0, 0, 0, 0, 0, 0, 900};
        test = new NameRecord(testName1, baseDecade1, arrayToList(arrTestRanks1));

        //numTopThousand test 23
        expected = 1;
        actual = test.numTopThousand();
        System.out.println("Test 23: numTopThousand(), Expected: " + expected + " Actual: " + actual);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //topThousandEveryDecade test 24
        exp = false;
        act = test.topThousandEveryDecade();
        System.out.println("Test 24: numTopThousand(), Expected: " + exp + " Actual: " + act);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //topThousandOnce test 25
        exp = true;
        act = test.topThousandOnce();
        System.out.println("Test 25: numTopThousand(), Expected: " + exp + " Actual: " + act);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //increaseRank test 26
        exp = false;
        act = test.increaseRank();
        System.out.println("Test 26: increaseRank(), Expected: " + exp + " Actual: " + act);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //decreaseRank test 27
        exp = false;
        act = test.decreaseRank();
        System.out.println("Test 18: decreaseRank(), Expected: " + exp + " Actual: " + act);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        testName1 = "ZeroBot";
        arrTestRanks1 = new int[] {0, 0, 0, 0, 0, 0, 0};
        test = new NameRecord(testName1, baseDecade1, arrayToList(arrTestRanks1));

        //numTopThousand test 28
        expected = 0;
        actual = test.numTopThousand();
        System.out.println("Test 28: numTopThousand(), Expected: " + expected + " Actual: " + actual);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //topThousandEveryDecade test 29
        exp = false;
        act = test.topThousandEveryDecade();
        System.out.println("Test 29: numTopThousand(), Expected: " + exp + " Actual: " + act);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //topThousandOnce test 30
        exp = false;
        act = test.topThousandOnce();
        System.out.println("Test 30: numTopThousand(), Expected: " + exp + " Actual: " + act);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //increaseRank test 31
        exp = false;
        act = test.increaseRank();
        System.out.println("Test 31: increaseRank(), Expected: " + exp + " Actual: " + act);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }

        //decreaseRank test 32
        exp = false;
        act = test.decreaseRank();
        System.out.println("Test 32: decreaseRank(), Expected: " + exp + " Actual: " + act);
        if(expected == actual){
            System.out.println("Passed test");
        } else{
            System.out.println("*FAILED* test");
        }


    }

    //converts Data to arrayList for testing purposes
    private static ArrayList<Integer> arrayToList(int[] data){
        ArrayList<Integer> temp = new ArrayList<>();
        for(int x = 0; x < data.length; x++){
            temp.add(data[x]);
        }
        return temp;
    }

     */

    // A few simple tests for the Names and NameRecord class.
    public static void simpleTest() {
        // raw data for Jake. Alter as necessary for your NameRecord
        String jakeName = "Jake";
        int[] jRanks = new int[] {262, 312, 323, 479, 484, 630, 751, 453, 225, 117, 97};
        ArrayList<Integer> jakeRanks = new ArrayList<Integer>() ;

        for(int x = 0; x < jRanks.length; x++){
            jakeRanks.add(jRanks[x]);
        }

        int baseDecade = 1900;
        NameRecord jakeRecord = new NameRecord(jakeName, baseDecade, jakeRanks);// complete with your constructor
        String expected = "Jake\n1900: 262\n1910: 312\n1920: 323\n1930: 479\n1940: "
                        + "484\n1950: 630\n1960: 751\n1970: 453\n1980: 225\n1990: "
                        + "117\n2000: 97\n";
        String actual = jakeRecord.toString();
        System.out.println("expected string:\n" + expected);
        System.out.println("actual string:\n" + actual);
        if (expected.equals(actual)) {
            System.out.println("passed Jake toString test.");
        } else {
            System.out.println("FAILED Jake toString test."); 
        }

        // Some getName Tests
        String NAME_FILE2 = "names2.txt";
        Names names = new Names(getFileScannerForNames(NAME_FILE2));
        String[] testNames = {"Isabelle", "isabelle", "sel", 
                "X1178", "ZZ", "via", "kelly"};
        boolean[] expectNull = {false, false, true, true, true, true, false};
        for (int i = 0; i < testNames.length; i++) {
            performGetNameTest(names, testNames[i], expectNull[i]);
        }

    }

    // Checks if given name is present in Names.
    private static void performGetNameTest(Names names, String name, 
            boolean expectNull) {
        
        System.out.println("Performing test for this name: " + name);
        if (expectNull) {
            System.out.println("Expected return value is null");
        } else {
            System.out.println("Expected return value is not null");
        }
        NameRecord result = names.getName(name);
        if ((expectNull && result == null) || (!expectNull && result != null)) {
            System.out.println("PASSED TEST.");
        } else {
            System.out.println("Failed test");
        }
    }

    // main method. Driver for the whole program
    public static void main(String[] args) {
        //personal tests
        //myTests();

        // Alter name of file to try different data sources.
        final String NAME_FILE = "names.txt"; 
        Scanner fileScanner = getFileScannerForNames(NAME_FILE);
        Names namesDatabase = new Names(fileScanner);
        fileScanner.close();
        runOptions(namesDatabase);
    }

    /* pre: namesDatabase != null
     * Ask user for options to perform on the given Names object.
     * Creates a Scanner connected to System.in.
     */
    private static void runOptions(Names namesDatabase) {
        if(namesDatabase == null){
            throw new IllegalArgumentException("Violation of pre-condition: runOptions(), namesDatabase != null");
        }

        Scanner keyboard = new Scanner(System.in);
        MenuChoices[] menuChoices = MenuChoices.values();
        MenuChoices menuChoice;
        do {
            showMenu();
            int userChoice = getChoice(keyboard) - 1;
            menuChoice = menuChoices[userChoice];
            if(menuChoice == MenuChoices.SEARCH) {
                search(namesDatabase, keyboard);
            } else if (menuChoice == MenuChoices.ONE_NAME) {
                oneName(namesDatabase, keyboard);
            } else if (menuChoice == MenuChoices.APPEAR_ONCE) {
                appearOnce(namesDatabase);
            } else if (menuChoice == MenuChoices.APPEAR_ALWAYS) {
                appearAlways(namesDatabase);
            } else if (menuChoice == MenuChoices.ALWAYS_MORE) {
                alwaysMore(namesDatabase);
            } else if (menuChoice == MenuChoices.ALWAYS_LESS) {
                alwaysLess(namesDatabase);
            } else if (menuChoice == MenuChoices.STUDENT_SEARCH) {
                nameGenerator(namesDatabase, keyboard);
            }
        } while(menuChoice != MenuChoices.QUIT);
        keyboard.close();
    }

    /* Create a Scanner and return connected to a File with the given name.
     * pre: fileName != null
     * post: Return a Scanner connected to the file or null
     * if the File does not exist in the current directory.
     */
    private static Scanner getFileScannerForNames(String fileName) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("\n***** ERROR IN READING FILE ***** ");
            System.out.println("Can't find this file " 
                    + fileName + " in the current directory.");
            System.out.println("Error: " + e);
            String currentDir = System.getProperty("user.dir");
            System.out.println("Be sure " + fileName + " is in this directory: ");
            System.out.println(currentDir);
            System.out.println("\nReturning null from method.");
            sc = null;
        }
        return sc;
    }

    /* Display the names that have appeared in ever decade.
     * pre: n != null
     * post: print out names that have appeared in ever decade
     */
    private static void appearAlways(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter namesDatabase cannot be null");
        }
        ArrayList<String> temp = namesDatabase.rankedEveryDecade();

        System.out.print("\n" + temp.size() + " names appear in every decade.");

        if(temp != null){
            System.out.print(" The names are: \n");
            for(int x = 0; x < temp.size(); x++){
                System.out.println(temp.get(x));
            }
        }

    }

    /* Display the names that have appeared in only one decade.
     * pre: n != null
     * post: print out names that have appeared in only one decade
     */
    private static void appearOnce(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }

        ArrayList<String> temp = namesDatabase.rankedOnlyOneDecade();
        System.out.print(temp.size() + " names appear in exactly one decade.");

        if(temp != null){
            System.out.print(" The names are: ");
            for(int x = 0; x < temp.size(); x++){
                System.out.println(temp.get(x));
            }
        }
    }
    
    /* Display the names that have gotten more popular
     * in each successive decade.
     * pre: n != null
     * post: print out names that have gotten more popular in each decade
     */
    private static void alwaysMore(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }

        ArrayList<String> temp = namesDatabase.alwaysMorePopular();

        System.out.println(temp.size() + " names are more popular in every decade. ");

        if(temp != null){
            for(int x = 0; x < temp.size(); x++){
                System.out.println(temp.get(x));
            }
        }
    }
    
    /* Display the names that have gotten less popular 
     * in each successive decade.
     * pre: n != null
     * post: print out names that have gotten less popular in each decade
     */
    private static void alwaysLess(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }

        ArrayList<String> temp = namesDatabase.alwaysLessPopular();
        System.out.print(temp.size() + " names are less popular in every decade. ");
        if(temp != null){
            for(int x = 0; x < temp.size(); x++){
                System.out.println(temp.get(x));
            }
        }
    }

    /* Display the data for one name or state that name has never been ranked.
     * pre: n != null, keyboard != null and is connected to System.in
     * post: print out the data for n or a message that n has never been in the
     * top 1000 for any decade
     */
    private static void oneName(Names namesDatabase, Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (namesDatabase == null || keyboard == null) {
            throw new IllegalArgumentException("The parameters cannot be null");
        }
        System.out.print("Enter a name: ");
        String search = keyboard.next();
        System.out.println();

        NameRecord temp = namesDatabase.getName(search);
        if(temp != null){
            System.out.println(namesDatabase.getName(search).toString());
        } else{
            System.out.println(search + " does not appear in any decade.");
        }

    }

    /* Display all names that contain a substring from the user
     * and the decade they were most popular.
     * pre: n != null, keyboard != null and is connected to System.in
     * post: display the data for each name.
     */
    private static void search(Names namesDatabase, Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (namesDatabase == null || keyboard == null) {
            throw new IllegalArgumentException("The parameters cannot be null");
        }

        System.out.print("Enter a partial name: ");
        String look = keyboard.next();
        ArrayList<NameRecord> result = namesDatabase.getMatches(look);

        Collections.sort(result);
        System.out.println();
        System.out.println("There are " + result.size() + " matches for " + look + ".");

        if(result.size() > 0){
            System.out.println();
            System.out.println("The matches with their highest ranking decade are:");
            for(int x = 0; x < result.size(); x++){
                System.out.println(result.get(x).getName() + " " + result.get(x).bestDecade());
            }
        }
        
    }

    //generates random name based on user's input
    private static void nameGenerator(Names namesDataBase, Scanner keyboard){
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (namesDataBase == null || keyboard == null) {
            throw new IllegalArgumentException("The parameters cannot be null");
        }

        System.out.print("Enter a preferred first letter: ");
        String firstLetter = keyboard.next();
        if(firstLetter.equals(null)){
            System.out.println("Invalid input, try again.");
            nameGenerator(namesDataBase, keyboard);
        }

        //will only take first char of input String (ex: d = d, zack = z)
        firstLetter = Character.toString(firstLetter.charAt(0));

        //gets array list based on met criteria
        ArrayList<NameRecord> namesList = searchFirstLetter(namesDataBase, firstLetter);
        System.out.println("There are " + namesList.size() + " results for the letter: " + firstLetter);

        if(namesList.size() > 0){
            generatedResults(namesList,keyboard);
        } else{
            System.out.println();
            System.out.println("No names can be generated.");
        }
    }

    //displays results
    private static void generatedResults(ArrayList<NameRecord> namesList, Scanner keyboard){
        if (namesList == null || keyboard == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase or Scanner cannot be null");
        }
        boolean roll = true;
        while(roll){
            System.out.println();
            NameRecord random = namesList.get((int) (Math.random() * namesList.size()));
            System.out.println("Name Generated: " + random.getName());

            //gives user a choice to view information or re-roll
            System.out.println("Enter 1 to re-roll for new name.");
            System.out.println("Enter 2 to read current name's data.");
            System.out.println("Enter 3 to go back to main menu.");
            int choice = generatorChoice(keyboard) - 1;

            //checks if user wants to view info based on int value stopping the loop
            //values of VIEW and EXIT ordinal are greater than RE_ROLL's ordinal
            if(choice > GeneratorChoices.RE_ROLL.ordinal()){
                roll = false;
                if(choice == GeneratorChoices.VIEW.ordinal()){
                    System.out.println();
                    System.out.print(random.toString());
                }
            }
        }
    }

    //grabs user's choice, uses different enums from the main menu
    private static int generatorChoice(Scanner keyboard){
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (keyboard == null) {
            throw new IllegalArgumentException("The parameter keyboard cannot be null");
        }
        int choice = getInt(keyboard, "Enter choice: ");
        keyboard.nextLine();
        // Add one due to zero based indexing of enums, but 1 based indexing of menu.
        final int MAX_CHOICE = GeneratorChoices.EXIT.ordinal() + 1;
        while (choice < 1  || choice > MAX_CHOICE) {
            System.out.println();
            System.out.println(choice + " is not a valid choice");
            choice = getInt(keyboard, "Enter choice: ");
            keyboard.nextLine();
        }
        return choice;
    }

    //finds names that start with given letter
    private static ArrayList<NameRecord> searchFirstLetter(Names nameDataBase, String letter){
        if (nameDataBase == null || letter == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase or letter cannot be null");
        }

        ArrayList<NameRecord> temp = nameDataBase.getMatches(letter);
        for(int x = 0; x < temp.size(); x++){

            //checks through if potential names meet first letter criteria
            if(!Character.toString(temp.get(x).getName().charAt(0)).equalsIgnoreCase(letter)){
                temp.remove(x);
                x--;
            }
        }
        return temp;
    }

    /* Get choice from the user keyboard != null and is connected to System.in
     * return an int that is >= MenuChoices.SEARCH.ordinal()
     *  and <= MenuChoices.QUIT.ordinal().
     */
    private static int getChoice(Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (keyboard == null) {
            throw new IllegalArgumentException("The parameter keyboard cannot be null");
        }
        int choice = getInt(keyboard, "Enter choice: ");
        keyboard.nextLine();
        // Add one due to zero based indexing of enums, but 1 based indexing of menu.
        final int MAX_CHOICE = MenuChoices.QUIT.ordinal() + 1;
        while (choice < 1  || choice > MAX_CHOICE) {
            System.out.println();
            System.out.println(choice + " is not a valid choice");
            choice = getInt(keyboard, "Enter choice: ");
            keyboard.nextLine();
        }
        return choice;
    }

    /* Ensure an int is entered from the keyboard.
     * pre: s != null and is connected to System.in
     * post: return the int typed in by the user.
     */
    private static int getInt(Scanner s, String prompt) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (s == null) {
            throw new IllegalArgumentException("The parameter s cannot be null");
        }
        System.out.print(prompt);
        while (!s.hasNextInt()) {
            s.next();
            System.out.println("That was not an int.");
            System.out.print(prompt);
        }
        return s.nextInt();
    }

    // Show the user the menu.
    private static void showMenu() {
        System.out.println();
        System.out.println("Options:");
        System.out.println("Enter 1 to search for names.");
        System.out.println("Enter 2 to display data for one name.");
        System.out.println("Enter 3 to display all names that appear in only "
                + "one decade.");
        System.out.println("Enter 4 to display all names that appear in all "
                + "decades.");
        System.out.println("Enter 5 to display all names that are more popular "
                + "in every decade.");
        System.out.println("Enter 6 to display all names that are less popular "
                + "in every decade.");
        System.out.println("Enter 7 to generate random name.");
        System.out.println("Enter 8 to quit.");
        System.out.println();
    }

    /**
     * An enumerated type to hold the menu choices 
     * for the NameSurfer program.
     */
    private static enum MenuChoices {
        SEARCH, ONE_NAME, APPEAR_ONCE, APPEAR_ALWAYS, ALWAYS_MORE, 
        ALWAYS_LESS, STUDENT_SEARCH, QUIT;
    }

    //enum types for option 7's menu
    private static enum GeneratorChoices{
        RE_ROLL, VIEW, EXIT;
    }
}
