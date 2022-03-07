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
}
