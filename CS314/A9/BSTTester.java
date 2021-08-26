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

/*
Experiment
~*All average times calculated in seconds*~
Binary Tree Results (Random)
n		    avg. time	avg. size	avg.height	min. height
1000		7.131201E-4	1000		21		    18
2000		1.136405E-3	2000		23		    20
4000		1.697932E-3	4000		26		    24
8000		2.897910E-3	7999		29		    26
16000		4.527844E-3	16000		31		    30
32000		9.126715E-3	31999		35		    32
64000		2.026835E-2	63999		38		    35
128000		5.320648E-2	127997		40		    38
256000		1.287165E-1	255993		43		    41
512000		2.972132E-1	511971		45		    44
1,024,000	7.784375E-1	1023876	    51		    48

TreeSet Results (Random)
n		    avg. time 	avg. size
1000		8.982883E-4	1000
2000		1.528403E-3	2000
4000		2.514031E-3	4000
8000		4.020596E-3	7999
16000		5.175924E-3	16000
32000		7.615359E-3	31999
64000		2.109834E-2	63999
128000		5.266801E-2	127996
256000		1.076438E-1	255994
512000		2.819401E-1	511968
1,024,000	8.150091E-1	1023878

How do they compare to your BinarySearchTree?
The TreeSet has a slower adding algorithm than my BinarySearch Tree.

Binary Tree Results (Sorted)
n		    avg. time 	avg. size	avg.height
1000		0.002716	1000		999
2000		0.004152	2000		1999
4000		0.016800	4000		3999
8000		0.068368	8000		7999
16000		0.237653	16000		15999
32000		1.001179	31999		31998
64000		4.230945	63999		63998

n		    prediction	actual
128000		17.879815	18.467550
256000		75.559430	107.21189
512000		319.31132	~~~~~~~
1024000	    1349.397	~~~~~~~

My prediction is that the increase 4x the length of the previous n term. I was only able to get my data up to n = 256000.
The values of 512000 and 1024000 tests are still calculating after running my code for an hour. That means that the actual
times are greater than my predictions.

TreeSet Results (Sorted)
n		    avg. time
1000		0.001084789
2000		3.912294E-4
4000		5.939745E-4
8000		7.517442E-4
16000		0.001580035
32000		0.003170409
64000		0.007801099

How do these times compare to the times it took for your BinarySearchTree class when inserting integers in sorted order?
What do you think is the cause for these differences?

    The TreeMap adds the sorted elements faster than my BinarySearch Tree. The reason for this is because
TreeMap’s put algorithm is faster in searching and inserting than my BinarySearch Tree is. From further
research, the TreeMap’s internal data structure is a Red-Black Tree that keeps itself balanced when
adding new elements. Before adding a new node that increases the height of the tree, it makes sure the
tree is a complete subtree. For my BinarySearch Tree, I did not take the balance of the tree into
consideration. While performing this experiment, my tree will have most of its elements on the right
side of the tree making it unbalanced and taking a long time to search the empty position at the end.
Therefore the TreeMap is faster than my BinarySearch Tree when inserting sorted values.
 */

import java.util.*;
/**
 * Some test cases for CS314 Binary Search Tree assignment.
 *
 */
public class BSTTester {

    /**
     * The main method runs the tests.
     * @param args Not used
     */
    public static void main(String[] args) {

        System.out.println("~Mike's Test~");
        cs314Tests();
        System.out.println();
        //experiment();
        System.out.println("~My Tests~");
        myTests();
        
    }

    //my tests
    private static void myTests(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        //test 1
        System.out.println("Test 1: empty tree created.");
        showTestResults(tree.size() == 0, 1);
        
        int[] data = {10, 7, 9, 25, 7, 4, 2, 13, 1, 14};
        for(int x = 0; x < data.length; x++){
            tree.add(data[x]);
        }

        //test 2
        System.out.println("Test 2: size()");
        showTestResults(tree.size() == 9, 2);

        //test 3
        System.out.println("Test 3: min()");
        showTestResults(tree.min() == 1, 3);

        //test 4
        System.out.println("Test 4: max().");
        showTestResults(tree.max() == 25, 4);

        //test 5
        System.out.println("Test 5: getAllLessThan(0)");
        showTestResults(tree.getAllLessThan(0).size() == 0, 5);

        //test 6
        System.out.println("Test 6: getAllGreaterThan(26)");
        showTestResults(tree.getAllGreaterThan(26).size() == 0, 6);

        List<Integer> result = new ArrayList<>();
        data = new int[] {1,2};
        for(int x = 0; x < data.length; x++){
            result.add(data[x]);
        }
        //test 7
        System.out.println("Test 7: getAllLessThan(4)");
        showTestResults(tree.getAllLessThan(4).equals(result), 7);

        result = new ArrayList<>();
        data = new int[] {10, 13, 14, 25};
        for(int x = 0; x < data.length; x++){
            result.add(data[x]);
        }

        //test 8
        System.out.println("Test 8: getAllGreaterThan(9)");
        showTestResults(tree.getAllGreaterThan(9).equals(result), 8);

        //test 9
        System.out.println("Test 9: height()");
        showTestResults(tree.height() == 4, 9);

        //test 10
        System.out.println("Test 10: getAllGreaterThan(9)");
        showTestResults(tree.getAllGreaterThan(9).equals(result), 10);

        //test 11
        System.out.println("Test 11: isPresent(13)");
        showTestResults(tree.isPresent(13), 11);

        //test 12
        System.out.println("Test 12: isPresent(15)");
        showTestResults(!tree.isPresent(15), 12);

        //test 13
        System.out.println("Test 13: numNodeAtDepth(5)");
        showTestResults(tree.numNodesAtDepth(5) == 0, 13);

        //test 14
        System.out.println("Test 14: numNodeAtDepth(0)");
        showTestResults(tree.numNodesAtDepth(0) == 1, 14);

        //test 15
        System.out.println("Test 15: numNodeAtDepth(-5)");
        showTestResults(tree.numNodesAtDepth(-5) == 0, 15);

        //test 16
        System.out.println("Test 16: numNodeAtDepth(3)");
        showTestResults(tree.numNodesAtDepth(3) == 2, 16);

        //test 17
        System.out.println("Test 17: numNodeAtDepth(height())");
        showTestResults(tree.numNodesAtDepth(tree.height()) == 1, 17);

        //test 18
        System.out.println("Test 18: numNodeAtDepth(2)");
        showTestResults(tree.numNodesAtDepth(2) == 3, 18);

        //test 19
        System.out.println("Test 19: get(3)");
        showTestResults(tree.get(3) == 7, 19);

        //test 20
        System.out.println("Test 20: get(size() - 1)");
        showTestResults(tree.get(tree.size() - 1) == tree.max(), 20);

        result = new ArrayList<>();
        data = new int[] {1, 2, 4, 7, 9, 10, 13, 14, 25};
        for(int x = 0; x < data.length; x++){
            result.add(data[x]);
        }
        //test 21
        System.out.println("Test 21: getAll()");
        showTestResults(tree.getAll().equals(result), 21);

        //test 22
        System.out.println("Test 22: add(13)");
        showTestResults(!tree.add(13), 22);

        //test 23
        System.out.println("Test 23: add(15)");
        showTestResults(tree.add(15), 23);

        //test 24
        System.out.println("Test 24: iterativeAdd(15)");
        showTestResults(!tree.iterativeAdd(15), 24);

        //test 25
        System.out.println("Test 25: iterativeAdd(30)");
        showTestResults(tree.iterativeAdd(30), 25);

        //test 26
        System.out.println("Test 26: remove(3)");
        showTestResults(!tree.remove(3), 26);

        //test 27
        System.out.println("Test 27: remove(25)");
        showTestResults(tree.remove(25), 27);

        //test 28
        System.out.println("Test 28: remove(2)");
        showTestResults(tree.remove(2), 28);

        //test 29
        tree.add(8);
        tree.add(20);
        System.out.println("Test 29: min()");
        showTestResults(tree.min() == 1, 29);

        //test 30
        System.out.println("Test 30: max()");
        showTestResults(tree.max() == 30, 30);

        //test 31
        System.out.println("Test 31: height()");
        showTestResults(tree.height() == 3, 31);

        //test 32
        System.out.println("Test 32: size()");
        showTestResults(tree.size() == 11, 32);

        //test 33
        result = new ArrayList<>();
        data = new int[] {1, 4, 7, 8, 9, 10, 13, 14, 15, 20, 30 };
        for(int x = 0; x < data.length; x++){
            result.add(data[x]);
        }
        System.out.println("Test 33: getAll()");
        showTestResults(tree.getAll().equals(result), 33);
    }
    
    

    //Experiment Code
    private static void experiment(){

        int[] n = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 256000, 512000, 1024000};
        //128000, 256000, 512000, 1024000 extra values

        for(int y = 0; y < n.length; y++){
            int cur = n[y];
            //load sorted
            int[] sorted = new int[n[y]];
            for(int z = 1; z <= sorted.length; z++){
                sorted[z-1] = z;
            }
            double timeTotal = 0.0;
            for(int x = 1; x <= 10; x++){
                Stopwatch time = new Stopwatch();
                //Random r = new Random();
                time.start();
                //TreeMap<Integer, Integer> b = new TreeMap<>();
                BinarySearchTree b = new BinarySearchTree();
                for(int i = 0; i < sorted.length; i++ ){
                    b.iterativeAdd(sorted[i]);
                }
                time.stop();
                timeTotal += time.time();
            }
            System.out.println("Average for Experiment: n = " + cur);
            System.out.println("Avg. Time: " + (timeTotal / 10) );
        }
    }




    //Mike's Test
    private static void cs314Tests(){

        BinarySearchTree<String> t = new BinarySearchTree<>();

        //test 1
        System.out.println("Test 1: empty tree created.");
        showTestResults(t.size() == 0, 1);

        //test 2
        System.out.println("Test 2: height of empty tree must be -1.");
        showTestResults(t.height() == -1, 2);

        //test 3
        System.out.println("Test 3: empty tree must " +
                "not contain the String \"abyss\".");
        showTestResults(t.isPresent("abyss") == false, 3);

        t.add("abyss");
        //test 4
        System.out.println("Test 4: added \"abyss\" to the" +
                "tree. Size must be 1.");
        showTestResults(t.size() == 1, 4);

        //test 5
        System.out.println("Test 5: height of tree with 1" +
                "element must be 0.");
        showTestResults(t.height() == 0, 5);

        //test 6
        System.out.println("Test 6: \"abyss\" must be in the tree.");
        showTestResults(t.isPresent("abyss") == true, 6);

        //test 7
        System.out.println("Test 7: tree must " +
                "not contain the String \"beep\".");
        showTestResults(t.isPresent("beep") == false, 7);

        //test 8
        System.out.println("Test 8: min value must be" +
                "\"abyss\" at this point.");
        showTestResults(t.min().equals("abyss"), 8);

        //test 9
        System.out.println("Test 9: max value must be" +
                "\"abyss\" at this point.");
        showTestResults(t.max().equals("abyss"), 9);

        t.add("abyss");
        //test 10
        System.out.println("Test 10: attempt to add \"abyss\"" +
                "again. size must remain 1.");
        showTestResults(t.size() == 1, 10);

        //test 11
        System.out.println("Test 11: attempt to add \"abyss\"" +
                "again. height must remain 0.");
        showTestResults(t.height() == 0, 11);

        //test 12
        System.out.println("Test 12: \"abyss\" must still be" +
                "present.");
        showTestResults(t.isPresent("abyss") == true, 12);

        t.add("beep");
        //test 13
        System.out.println("Test 13: added \"beep\" to the" +
                "tree. Size must be 2.");
        showTestResults(t.size() == 2, 13);

        //test 14
        System.out.println("Test 14: height of tree with 2" +
                "elements must be 1.");
        showTestResults(t.height() == 1, 14);

        //test 15
        System.out.println("Test 15: Removing \"abyss\" from the tree.");
        showTestResults(t.remove("abyss") == true, 15);

        //test 16
        System.out.println("Test 16: Removing \"beep\" from the tree.");
        showTestResults(t.remove("beep") == true, 16);

        //test 17
        System.out.println("Test 17: Tree must be empty at this point.");
        showTestResults(t.size() == 0, 17);

        t.add("beep");
        t.add("abyss");
        t.add("calls");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("abyss");
        expected.add("beep");
        expected.add("calls");

        //test 18
        System.out.println("Test 18: Added \"beep\", \"abyss\", and" +
                "\"calls\" to the tree in that order.\n" +
                "Testing getAll method.");

        showTestResults(expected.equals(t.getAll()) == true, 18);

        //problems
        //test 19
        t.add("bit");
        t.add("dish");
        System.out.println("Test 19: Added \"bit\" and \"dish\" to" +
                "tree. Checking that \"yes\" is not present.");
        showTestResults(t.remove("yes") == false, 19);

        //test 20
        t.add("a");
        System.out.println("Test 20: Added \"a\" and then " +
                "removed it.");
        showTestResults(t.remove("a") == true, 20);

        //test 21
        System.out.println("Test 21: Checking that \"calls\" is still present.");
        showTestResults(t.remove("calls") == true, 21);

        //test 22
        t.remove("abyss");
        System.out.println("Test 22: Removing \"abyss\". " +
                "Checking that \"beep\" is still present.");
        showTestResults(t.remove("beep") == true, 22);

        // Test 23 - Adding unbalanced
        BinarySearchTree<Integer>  actualTree = new BinarySearchTree<>();
        ArrayList<Integer> expectedValues = new ArrayList<>();
        actualTree.add(1);
        actualTree.iterativeAdd(2);
        actualTree.iterativeAdd(3);
        actualTree.add(-1);
        expectedValues.add(-1);
        expectedValues.add(1);
        expectedValues.add(2);
        expectedValues.add(3);
        System.out.println("Test 23: Adding unbalanced");
        showTestResults(expectedValues.equals(actualTree.getAll()) == true, 23);
        //System.out.println(actualTree.getAll());
        //actualTree.printTree();

        // Test 24 - Adding unbalanced
        actualTree.iterativeAdd(-2);
        expectedValues.add(0, -2);
        System.out.println("Test 24: Adding unbalanced");
        showTestResults(expectedValues.equals(actualTree.getAll()) == true, 24);
        //System.out.println(actualTree.getAll());
        //actualTree.printTree();

        // Test 25 - Removing root [-2, -1, 2, 3]
        actualTree.remove(1);
        expectedValues.remove(Integer.valueOf(1));
        System.out.println("Test 25: Removing root");
        showTestResults(expectedValues.equals(actualTree.getAll()) == true, 25);
        //System.out.println(actualTree.getAll());
        //actualTree.printTree();

        // Test 26 - Removing new root [-2, 2, 3]
        actualTree.remove(-1);
        expectedValues.remove(Integer.valueOf(-1));
        System.out.println("Test 26: Removing new root");
        showTestResults(expectedValues.equals(actualTree.getAll()) == true, 26);
        //System.out.println(actualTree.getAll());
        //actualTree.printTree();

        // Test 27 - IsPresent new root
        System.out.println("Test 27: IsPresent new root");
        showTestResults(actualTree.isPresent(-2) == true, 27);
        //System.out.println(actualTree.getAll());
        //actualTree.printTree();

        // Test 28 - IsPresent rightmost minimum
        System.out.println("Test 28: isPresent, root of tree");
        showTestResults(actualTree.isPresent(2) == true, 28);
        //System.out.println(actualTree.getAll());
        //actualTree.printTree();

        // Test 29 - Size
        System.out.println("Test 29: Size of tree");
        showTestResults(actualTree.size() == 3, 29);
        //System.out.println(actualTree.getAll());
        //actualTree.printTree();

        // Test 30 - Size removing rightmost minimum [-2, 3]
        actualTree.remove(2);
        expectedValues.remove(Integer.valueOf(2));
        System.out.println("Test 30: Size of tree");
        showTestResults(actualTree.size() == 2, 30);
        //System.out.println(actualTree.getAll());
        //actualTree.printTree();

        // Test 31 - Height
        System.out.println("Test 31: Height of tree");
        showTestResults(actualTree.height() == 1, 31);
        //System.out.println(actualTree.getAll());
        //actualTree.printTree();

        // Test 32 - Height removing root [3]
        actualTree.remove(-2);
        expectedValues.remove(Integer.valueOf(-2));
        System.out.println("Test 32: Height of tree");
        showTestResults(actualTree.height() == 0, 32);
        //System.out.println(actualTree.getAll());
        //actualTree.printTree();

        // Test 33 - Max of tree
        System.out.println("Test 33: Max of tree");
        showTestResults(actualTree.max().equals(Integer.valueOf(3)), 33);
        //System.out.println(actualTree.getAll());
        //actualTree.printTree();

        // Test 34 - Max of tree [-1, 3]
        actualTree.add(-1);
        System.out.println("Test 34: Max of tree");
        showTestResults(actualTree.max().equals(Integer.valueOf(3)), 34);
        //System.out.println(actualTree.getAll());
        //actualTree.printTree();

        // Test 35 - Min of tree
        System.out.println("Test 35: Min of tree");
        showTestResults(actualTree.min().equals(Integer.valueOf(-1)), 35);
        //System.out.println(actualTree.getAll());
        //actualTree.printTree();

        // Test 36 - Min of tree
        actualTree.add(4);
        System.out.println("Test 36: Min of tree");
        showTestResults(actualTree.min().equals(Integer.valueOf(-1)), 36);
        //System.out.println(actualTree.getAll());
        //actualTree.printTree();

        // Test 37 - Number of Nodes at Depth
        System.out.println("Test 37: Number of Nodes at depth of tree");
        showTestResults(actualTree.numNodesAtDepth(0) == 1, 37);
        //System.out.println(actualTree.getAll());
        //actualTree.printTree();

        // Test 38 - Number of Nodes at Depth
        System.out.println("Test 38: Number of Nodes at depth of tree");
        showTestResults(actualTree.numNodesAtDepth(1) == 2, 38);
        //System.out.println(actualTree.getAll());
        //actualTree.printTree();

        // Test 39 - height
        int[] values = {50, 25, -10, 10, 5, 0, 23, 30, 35, 40, 100, 75, 200};
        BinarySearchTree<Integer> t2 = new BinarySearchTree<>();
        for (int i : values) {
            t2.add(i);
        }
        System.out.println("Height again for non trivial tree");
        showTestResults(t2.height() == 5, 39);
        //System.out.println(t2.getAll());
        //t2.printTree();

        // Test 40 - 52: get kth
        System.out.println("getKth Tests");
        Arrays.sort(values);
        for (int i = 0; i < values.length; i++) {
            showTestResults(t2.get(i).equals(Integer.valueOf(values[i])), 40 + i);
        }

        // Test 53: getAllLessThan
        System.out.println("get all less than -50");
        showTestResults(t2.getAllLessThan(-50).equals(new ArrayList<Integer>()), 53);
        //System.out.println(t2.getAll());
        //t2.printTree();


        // Test 54: getAllLessThan
        System.out.println("get all less than 25");
        ArrayList<Integer> expectedList = new ArrayList<>();
        int cutoff = 25;
        int index = 0;
        while (index < values.length && values[index] < cutoff) {
            expectedList.add(Integer.valueOf(values[index]));
            index++;
        }
        List<Integer> actual = t2.getAllLessThan(cutoff);
        showTestResults(actual.equals(expectedList), 54);
        //System.out.println(t2.getAll());
        //t2.printTree();

        // Test 55: getAllLessThan
        System.out.println("get all less than 1000");
        expectedList.clear();
        cutoff = 1000;
        index = 0;
        while (index < values.length && values[index] < cutoff) {
            expectedList.add(Integer.valueOf(values[index]));
            index++;
        }
        actual = t2.getAllLessThan(cutoff);
        showTestResults(actual.equals(expectedList), 55);
        System.out.println("expected list: " + expectedList);
        System.out.println("actual list:   " + actual);
        //System.out.println(expectedList);
        //System.out.println(t2.getAll());
        //t2.printTree();


        // Test 57: getAllGreaterThan
        System.out.println("get all greater than 1000");
        expectedList.clear();
        cutoff = 1000;
        index = values.length - 1;
        while (index >= 0 && values[index] > cutoff) {
            expectedList.add(Integer.valueOf(values[index]));
            index--;
        }
        Collections.reverse(expectedList);
        actual = t2.getAllGreaterThan(cutoff);
        showTestResults(actual.equals(expectedList), 57);
        //System.out.println(expectedList);
        //System.out.println(t2.getAll());
        //t2.printTree();

        // Test 58: getAllGreaterThan
        System.out.println("get all greater than 25");
        expectedList.clear();
        cutoff = 25;
        index = values.length - 1;
        while (index >= 0 && values[index] > cutoff) {
            expectedList.add(Integer.valueOf(values[index]));
            index--;
        }
        Collections.reverse(expectedList);
        actual = t2.getAllGreaterThan(cutoff);
        showTestResults(actual.equals(expectedList), 58);
        System.out.println("expected list: " + expectedList);
        System.out.println("actual list:   " + actual);
        //t2.printTree();


        // Test 59: getAllGreaterThan
        System.out.println("get all greater than -1000");
        expectedList.clear();
        cutoff = -1000;
        index = values.length - 1;
        while (index >= 0 && values[index] > cutoff) {
            expectedList.add(Integer.valueOf(values[index]));
            index--;
        }
        Collections.reverse(expectedList);
        actual = t2.getAllGreaterThan(cutoff);
        showTestResults(actual.equals(expectedList), 59);
        System.out.println("expected list: " + expectedList);
        System.out.println("actual list:   " + actual);
        //t2.printTree();


        // Test 60, stress test
        System.out.println("Stress test, comparing size to HashSet");
        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
        HashSet<Integer> hs = new HashSet<>();
        Random r = new Random();
        int numValues = 500_000;
        for (int i = 0; i < numValues; i++) {
            int temp = r.nextInt();
            bst1.add(temp);
            hs.add(temp);
        }
        showTestResults(hs.size() == bst1.size(), 60);

        // Test 61, stress test
        System.out.println("Stress test, comparing size to HashSet");
        bst1 = new BinarySearchTree<>();
        hs = new HashSet<>();
        numValues = 1_000_000;
        for (int i = 0; i < numValues; i++) {
            int temp = r.nextInt();
            bst1.add(temp);
            hs.add(temp);
        }
        showTestResults(hs.size() == bst1.size(), 61);
    }

    private static void showTestResults(boolean passed, int testNum) {
        if (passed) {
            System.out.println("Test " + testNum + " passed.");
        } else {
            System.out.println("TEST " + testNum + " FAILED.");
        }
    }
}
