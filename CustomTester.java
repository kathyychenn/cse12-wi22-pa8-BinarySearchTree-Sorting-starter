/**
 * Name: Kathy Chen
 * PID: A17030814
 * 
 * This file contains all of the student-written tests. It contains test 
 * methods for the MyBSTNode, MyBST, and MyBSTIterator classes to ensure 
 * accuracy (return correct values, correctly modify sets, exceptions, etc). 
 */

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

public class CustomTester {
    MyBST<Integer, Integer> testTree;

    // 
    /**
     * The setup method create a complete tree with height three
     * The tree has following structure and will be used as testing purpose
     *           4
     *         /  \
     *       2     6
     *     /  |   /
     *    1   3  5
     */
    @Before
    public void setup(){

        MyBST.MyBSTNode<Integer, Integer> root = 
            new MyBST.MyBSTNode(4, 1, null);
        MyBST.MyBSTNode<Integer, Integer> two = 
            new MyBST.MyBSTNode(2, 1, root);
        MyBST.MyBSTNode<Integer, Integer> six = 
            new MyBST.MyBSTNode(6, 1, root);
        MyBST.MyBSTNode<Integer, Integer> one = 
            new MyBST.MyBSTNode(1, 2, two);
        MyBST.MyBSTNode<Integer, Integer> three = 
            new MyBST.MyBSTNode(3, 30, two);
        MyBST.MyBSTNode<Integer, Integer> five = 
            new MyBST.MyBSTNode(5, 50, six);

        this.testTree = new MyBST();
        this.testTree.root = root;
        root.setLeft(two);
        root.setRight(six);
        two.setLeft(one);
        two.setRight(three);
        six.setLeft(five);
        this.testTree.size = 6;
    }

    // ====== MyBSTNode class ======

    // Test predecessor() on a smallest node
    @Test
    public void testNodePredecessorSmallestNode() {
        MyBST.MyBSTNode<Integer, Integer> root = testTree.root;
        assertSame(null, root.getLeft().getLeft().predecessor());

    }

    // ====== MyBST class ======

    // Test insert method when given key is equal to a node key in BST
    @Test
    public void testInsertEqual(){
        MyBST.MyBSTNode<Integer, Integer> root = testTree.root; 
        
        assertEquals(null,  testTree.insert(6, 1));
        assertEquals((Integer)1, root.getRight().getValue());
    }

    // Test insert method when BST is empty
    @Test
    public void testInsertEmpty(){
        MyBST<Integer, Integer> emptyTree = new MyBST<Integer, Integer>();
        
        emptyTree.insert(6, 1);
        assertEquals((Integer)6, emptyTree.root.getKey());
    }

    //Test insert method

    //Test search method when key is null
    @Test
    public void testSearchNull(){
        assertNull(testTree.search(null));
    }

    //Test search method when key does not exist
    @Test
    public void testSearchNonExist(){
        assertNull(testTree.search(7));
    }

    //Test remove method when node has 2 children
    @Test
    public void testRemoveTwoChildren(){
        MyBST.MyBSTNode<Integer, Integer> root = testTree.root; 
        assertEquals((Integer)1, testTree.remove(2));
        assertEquals((Integer)3, root.getLeft().getKey());
        assertEquals(null, root.getLeft().getRight());
    }

    //Test remove method when removing root
    @Test
    public void testRemoveRoot(){
        MyBST.MyBSTNode<Integer, Integer> root = testTree.root; 
        assertEquals((Integer)1, testTree.remove(4));
        assertEquals((Integer)5, root.getKey());
        assertEquals((Integer)6, root.getRight().getKey());
        assertEquals(5,testTree.size());
    }

    //Test remove method when key is null
    @Test
    public void testRemoveKeyNull(){
        assertNull(testTree.remove(null));
        assertEquals(6, testTree.size());
    }

    //Test remove method when key not found
    @Test
    public void testRemoveKeyNotFound(){
        MyBST.MyBSTNode<Integer, Integer> root = testTree.root; 
        assertNull(testTree.remove(19));
        assertEquals(6, testTree.size());
    }    

    //Test inorder method when BST is empty
    @Test
    public void testInorderEmpty(){
        MyBST<Integer, Integer> emptyTree = new MyBST<Integer, Integer>();

        assertEquals(new ArrayList<MyBST.MyBSTNode<Integer, Integer>>(), 
                    emptyTree.inorder());
    }

    // Test inorder method when values of tree are null
    @Test
    public void testInorder(){
        MyBST.MyBSTNode<Integer, Integer> root = testTree.root; 
        root.getLeft().setValue(null);
        root.getRight().setValue(null);

        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes 
            = new ArrayList<>();
            
        expectedRes.add(root.getLeft().getLeft());
        expectedRes.add(root.getLeft());
        expectedRes.add(root.getLeft().getRight());
        expectedRes.add(root);
        expectedRes.add(root.getRight().getLeft());
        expectedRes.add(root.getRight());
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> actualRes 
            = testTree.inorder();

        for (int i=0; i<expectedRes.size(); i++){
            assertSame(expectedRes.get(i), actualRes.get(i));
        }
    }

    // ====== MyBSTIterator class ======

    // Test the nextNode method when there is no next node
    @Test
    public void testNextNodeNoNextNode(){
        MyBSTIterator<Integer, Integer> iterTree = new MyBSTIterator();
        iterTree.root = testTree.root;

        // Initialize the BST value iterator that start from root
        MyBSTIterator<Integer, Integer>.MyBSTValueIterator vi = 
            iterTree.new MyBSTValueIterator(iterTree.root);

        // next should points to the root
        assertSame(iterTree.root, vi.next);

        vi.nextNode();
        vi.nextNode();
        vi.nextNode();
        
        try {
            vi.nextNode();
            fail();
        } catch (NoSuchElementException e) {
            //successfully caught exception
        }
    }

    // Test the book method when start is invalid
    @Test
    public void testCalenderException(){
        MyCalendar cal = new MyCalendar();
        // check MyTreeMap is initialized
        assertNotNull(cal.getCalendar());

        //book throws exception when start < 0
        try {
            cal.book(-1,9);
            fail();
        } catch (IllegalArgumentException e) {
            //successfully caught exception
        }

        //book throws exception when start = end        
        try {
            cal.book(9,9);
            fail();
        } catch (IllegalArgumentException e) {
            //successfully caught exception
        }

        //book throws exception when start > end
        try {
            cal.book(10,9);
            fail();
        } catch (IllegalArgumentException e) {
            //successfully caught exception
        }
    }

    
    // Test book method when trying to double book exactly
    @Test
    public void testDoubleBook(){
        MyCalendar cal = new MyCalendar();
        // check MyTreeMap is initialized
        assertNotNull(cal.getCalendar());

        // Book an event on a calender with no event
        assertTrue(cal.book(10, 20));

        // Book a conflicting event
        assertFalse(cal.book(10,20));
        
    }
}
