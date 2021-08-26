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

import java.util.ArrayList;
import java.util.List;

/**
 * Shell for a binary search tree class.
 * @author scottm
 * @param <E> The data type of the elements of this BinarySearchTree.
 * Must implement Comparable or inherit from a class that implements
 * Comparable.
 *
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

    private BSTNode<E> root;
    // CS314 students. Add any other instance variables you want here
    private int size;

    // CS314 students. Add a default constructor here if you feel it is necessary.
    public BinarySearchTree(){
        root = null;
        size = 0;
    }

    /**
     *  Add the specified item to this Binary Search Tree if it is not already present.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: Add value to this tree if not already present. Return true if this tree
     *  changed as a result of this method call, false otherwise.
     *  @param value the value to add to the tree
     *  @return false if an item equivalent to value is already present
     *  in the tree, return true if value is added to the tree and size() = old size() + 1
     */
    //Mike's code from lecture
    public boolean add(E value) {
        if(value == null){
            throw new IllegalStateException("Pre-condition not met: value != null");
        }
        int oldSize = size;
        root = addHelp(root, value);
        return oldSize != size;
    }

    //Mike's code from lecture
    //performs recursive insertion algorithm to binary tree
    private BSTNode<E> addHelp(BSTNode<E> n, E val){
        //base case, node is null
        if(n == null){
            size++;
            return new BSTNode<>(val);
        }
        //keep searching recursively, n != null
        //search for right placement
        int difference = val.compareTo(n.data);
        //go left
        if(difference < 0){
            //connects left node to potential new node
            n.setLeft(addHelp(n.getLeft(), val));
        } else if(difference > 0){
            //connects right node to potential new node
            n.setRight(addHelp(n.getRight(), val));
        }
        //data matches value, no need to add
        //returns n unchanged
        return n;

    }

    /**
     *  Remove a specified item from this Binary Search Tree if it is present.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: Remove value from the tree if present, return true if this tree
     *  changed as a result of this method call, false otherwise.
     *  @param value the value to remove from the tree if present
     *  @return false if value was not present
     *  returns true if value was present and size() = old size() - 1
     */
    //Mike's code from lecture
    public boolean remove(E value) {
        if(value == null){
            throw new IllegalStateException("Pre-condition not met: value != null");
        }
        int oldSize = size;
        root = removeHelp(root, value);
        return oldSize - 1 == size;
    }

    //recursively searches and removes given value
    private BSTNode<E> removeHelp(BSTNode<E> n, E val){
        //checks in case of empty tree and current empty node
        if(n == null){
            return n;
        }
        //n is not null
        int difference = val.compareTo(n.data);
        if(difference < 0){
            //val is left subtree
            n.setLeft(removeHelp(n.getLeft(), val));

        } else if(difference > 0){
            //val is right
            n.setRight(removeHelp(n.getRight(), val));
        } else{
            //diff == 0, we found it!
            size--;
            if(n.getLeft() == null & n.getRight() == null){
                //n is a leaf, null out
                return null;
            } else if(n.getLeft() == null){
                //single right to child
                return n.getRight();
            } else if(n.getRight() == null){
                //single left to child
                return n.getLeft();
            } else{
                //2 children
                //finds max from certain node position
                E maxLeftSubtree = maxFromNode(n.getLeft());
                n.setData(maxLeftSubtree);
                n.setLeft(removeHelp(n.getLeft(), maxLeftSubtree));
                size++;
            }
        }
        return n;
    }

    //Mike's code from lecture
    //finds the max node from the given starting node
    private E maxFromNode(BSTNode<E> n){
        while(n.getRight() != null){
            n = n.getRight();
        }
        return n.getData();
    }

    /**
     *  Check to see if the specified element is in this Binary Search Tree.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: return true if value is present in tree, false otherwise
     *  @param value the value to look for in the tree
     *  @return true if value is present in this tree, false otherwise
     */
    public boolean isPresent(E value) {
        if(value == null){
            throw new IllegalStateException("Pre-condition not met: value != null");
        }
        BSTNode<E> cur = root;
        while(cur != null){
            int difference = value.compareTo(cur.getData());
            //checks left subtree
            if(difference < 0){
                cur = cur.getLeft();
            //checks right subtree
            } else if(difference > 0){
                cur = cur.getRight();
            } else{
                //value is equal to data, we found it!
                return true;
            }
        }
        return false;
    }


    /**
     *  Return how many elements are in this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return the number of items in this tree
     *  @return the number of items in this Binary Search Tree
     */
    public int size() {
        return size;
    }


    /**
     *  return the height of this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return the height of this tree.
     *  If the tree is empty return -1, otherwise return the
     *  height of the tree
     *  @return the height of this tree or -1 if the tree is empty
     */
    //Mike's code from lecture
    public int height() {
        return htHelp(root);
    }

    //Mike's code from lecture
    //counts and determines max height of the tree
    private int htHelp(BSTNode<E> n){
        //empty node/tree check
        if(n == null){
            return -1;
        } else{
            //recursive case checks both sides for max height
            return 1 + Math.max(htHelp(n.left), htHelp(n.right));
        }
    }


    /**
     *  Return a list of all the elements in this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return a List object with all data from the tree in ascending order.
     *  If the tree is empty return an empty List
     *  @return a List object with all data from the tree in sorted order
     *  if the tree is empty return an empty List
     */
    public List<E> getAll() {
        ArrayList<E> result = new ArrayList<>();
        allHelp(root, result);
        return result;
    }

    //adds elements in in-order traversal path recursively
    private boolean allHelp(BSTNode<E> n, List<E> list){
        //base case, no more nodes to go further
        if(n == null){
            return true;
        } else{
            //checks left subtree
            allHelp(n.getLeft(), list);
            //checks current node
            list.add(n.getData());
            //checks right subtree
            allHelp(n.getRight(), list);
            //done going through subtree
            return true;
        }
    }


    /**
     * return the maximum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the largest value in this Binary Search Tree
     * @return the maximum value in this tree
     */
    public E max() {
        if(size <= 0){
            throw new IllegalStateException("Pre-condition not met: size() > 0");
        }
        //not empty tree pass this point
        BSTNode<E> cur = root;
        E max = null;
        //goes to farthest right node/element
        while(cur != null){
            max = cur.getData();
            cur = cur.right;
        }
        return max;
    }

    /**
     * return the minimum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the smallest value in this Binary Search Tree
     * @return the minimum value in this tree
     */
    public E min() {
        if(size <= 0){
            throw new IllegalStateException("Pre-condition not met: size() > 0");
        }
        //not empty tree pass this point
        BSTNode<E> cur = root;
        E min = null;
        while(cur != null){
            min = cur.getData();
            cur = cur.left;
        }
        return min;
    }


    /**
     * An add method that implements the add algorithm iteratively 
     * instead of recursively.
     * <br>pre: data != null
     * <br>post: if data is not present add it to the tree, 
     * otherwise do nothing.
     * @param data the item to be added to this tree
     * @return true if data was not present before this call to add, 
     * false otherwise.
     */
    public boolean iterativeAdd(E data) {
        if(data == null){
            throw new IllegalStateException("Pre-condition not met: data != null");
        }
        //special check for empty tree
        if(root == null){
            root = new BSTNode<>(data);
        }
        //keeps track of previous and current node
        BSTNode<E> prev = null;
        BSTNode<E> cur = root;
        while(cur != null){
            int difference = data.compareTo(cur.data);
            //no duplicates allowed
            if(difference == 0){
                return false;
            }
            //can move on, must be either left or right of tree
            prev = cur;
            if(difference < 0){
                cur = cur.left;
            } else{
                cur = cur.right;
            }
        }
        size++;
        //found null spot, can fill in!
        cur = new BSTNode<>(data);
        //checks which direction to connect previous node to
        if(cur.data.compareTo(prev.data) < 0){
            prev.setLeft(cur);
        } else {
            prev.setRight(cur);
        }
        return true;
    }


    /**
     * Return the "kth" element in this Binary Search Tree. If kth = 0 the
     * smallest value (minimum) is returned.
     * If kth = 1 the second smallest value is returned, and so forth.
     * <br>pre: 0 <= kth < size()
     * @param kth indicates the rank of the element to get
     * @return the kth value in this Binary Search Tree
     */
    public E get(int kth) {
        if(kth < 0 || kth >= size){
            throw new IllegalStateException("Pre-condition not met: 0 <= kth < size()");
        }
        int[] count = new int[1];
        return getHelper(root, count, kth);
    }


    //performs inorder-traversal to find the kth element
    private E getHelper(BSTNode<E> n, int[] count, int goal){
        //base case of empty null
        if(n == null){
            return null;
        }
        //check left
        E val = getHelper(n.getLeft(), count, goal);
        if(val != null && count[0] == goal){
            return val;
        }
        //check cur
        if(count[0] == goal){
            return n.getData();
        }
        count[0]++;
        //check right
        val = getHelper(n.getRight(), count, goal);
        if(val != null && count[0] == goal){
            return val;
        }
        //kth term is not in this subtree
        return null;
    }


    /**
     * Return a List with all values in this Binary Search Tree
     * that are less than the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * @param value the cutoff value
     * @return a List with all values in this tree that are less than
     * the parameter value. If there are no values in this tree less
     * than value return an empty list. The elements of the list are
     * in ascending order.
     */
    public List<E> getAllLessThan(E value) {
        if(value == null){
            throw new IllegalStateException("Pre-condition not met: value != null");
        }
        ArrayList<E> result = new ArrayList<>();
        //check for non-empty list and if value is greater than min
        if(size != 0 && value.compareTo(min()) > 0){
            //value is greater than max
            if(value.compareTo(max()) > 0){
                //meaning whole tree is less than value
                return getAll();
            }
            getLessHelper(root, result, value);
            return result;
        }
        //returns empty list for special
        //case of empty tree and value < min
        return result;
    }


    //recursively searches and adds elements less than given value
    private boolean getLessHelper(BSTNode<E> n, List<E> list, E value){
        //done searching in this path if no other node to go forward to
        if(n == null){
            return true;
        }
        //check left side
        getLessHelper(n.getLeft(), list, value);
        //checks current node if lesser than val
        if(value.compareTo(n.getData()) > 0){
            list.add(n.getData());
        }
        //check right side
        getLessHelper(n.getRight(), list, value);
        //after checking all we're done with this subtree
        return true;
    }


    /**
     * Return a List with all values in this Binary Search Tree
     * that are greater than the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * @param value the cutoff value
     * @return a List with all values in this tree that are greater
     *  than the parameter value. If there are no values in this tree
     * greater than value return an empty list.
     * The elements of the list are in ascending order.
     */
    public List<E> getAllGreaterThan(E value) {
        if(value == null){
            throw new IllegalStateException("Pre-condition not met: value != null");
        }
        ArrayList<E> result = new ArrayList<>();
        //checks for non-empty tree and if val < max
        if(size != 0 && value.compareTo(max()) < 0){
            //value is less than min
            if(value.compareTo(min()) < 0){
                //meaning all of tree is greater than value
                return getAll();
            }
            getGreaterHelper(root, result, value);
            //returns non-empty list
            return result;
        }
        //returns empty list
        return result;
    }


    //recursive go through right side of tree?
    private boolean getGreaterHelper(BSTNode<E> n, List<E> list, E value){
        //done searching in this path if no other node to go forward to
        if(n == null){
            return true;
        }
        //check left side
        getGreaterHelper(n.getLeft(), list, value);
        //checks current node
        if(value.compareTo(n.data) < 0){
            list.add(n.data);
        }
        //check right side
        getGreaterHelper(n.getRight(), list, value);
        //after checking all we're done with this subtree
        return true;
    }

    /**
     * Find the number of nodes in this tree at the specified depth.
     * <br>pre: none
     * @param d The target depth.
     * @return The number of nodes in this tree at a depth equal to
     * the parameter d.
     */
    public int numNodesAtDepth(int d) {
        if(d >= 0 && root != null){
            return numNodesHelp(root, d);
        }
        //returns for negative number and empty tree
        return 0;
    }

    //finds nodes from given starting node with given max links
    private int numNodesHelp(BSTNode<E> n, int pathLinks){
        //cannot go any further
        if(n == null){
            return 0;
        }
        if(pathLinks == 0 && n != null){
            return 1;
        } else{
            int sum = 0;
            sum += numNodesHelp(n.left, pathLinks - 1);
            sum += numNodesHelp(n.right, pathLinks - 1);
            return sum;
        }
    }


    /**
     * Prints a vertical representation of this tree.
     * The tree has been rotated counter clockwise 90
     * degrees. The root is on the left. Each node is printed
     * out on its own row. A node's children will not necessarily
     * be at the rows directly above and below a row. They will
     * be indented three spaces from the parent. Nodes indented the
     * same amount are at the same depth.
     * <br>pre: none
     */
    public void printTree() {
        printTree(root, "");
    }

    private void printTree(BSTNode<E> n, String spaces) {
        if(n != null){
            printTree(n.getRight(), spaces + "  ");
            System.out.println(spaces + n.getData());
            printTree(n.getLeft(), spaces + "  ");
        }
    }

    private static class BSTNode<E extends Comparable<? super E>> {
        private E data;
        private BSTNode<E> left;
        private BSTNode<E> right;

        public BSTNode() {
            this(null);
        }

        public BSTNode(E initValue) {
            this(null, initValue, null);
        }

        public BSTNode(BSTNode<E> initLeft,
                E initValue,
                BSTNode<E> initRight) {
            data = initValue;
            left = initLeft;
            right = initRight;
        }

        public E getData() {
            return data;
        }

        public BSTNode<E> getLeft() {
            return left;
        }

        public BSTNode<E> getRight() {
            return right;
        }

        public void setData(E theNewValue) {
            data = theNewValue;
        }

        public void setLeft(BSTNode<E> theNewLeft) {
            left = theNewLeft;
        }

        public void setRight(BSTNode<E> theNewRight) {
            right = theNewRight;
        }
    }
}
