/**
 * Name: Kathy Chen
 * PID: A17030814
 * Sources: None
 * 
 * This is the MyBSTIterator java file for PA 8. It contains the MyBSTIterator
 * generic class which contains the MyBSTKeyIterator class, the 
 * MyBSTValueIterator class, and the MyBSTNodeIterator generic abstract class 
 * which extends MyBST within.
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A MyBSTIterator class that contains accessor methods which return the key 
 * iterator and the value iterator of the BST. It contains more classes within
 * which specify the functionality of the class such as MyBSTNodeIterator,
 * MyBSTKeyIterator, and MyBSTValueIterator.
 */
public class MyBSTIterator<K extends Comparable<K>, V> extends MyBST<K, V> {
    /**
     * A MyBSTNodeIterator abstract class that holds references to the next and
     * lastVisitied nodes of the iterator. It contains a constructor method to
     * initialize the references and methods to advance the iterator or remove
     * nodes from the BST.
     */
    abstract class MyBSTNodeIterator<T> implements Iterator<T> {
        MyBSTNode<K, V> next;
        MyBSTNode<K, V> lastVisited;

        /**
         * Constructor that initializes the node iterator
         *
         * @param first The initial node that next points
         */
        MyBSTNodeIterator(MyBSTNode<K, V> first) {
            next = first;
            lastVisited = null;
        }

        /**
         * This method is used for determining if the next pointer in the
         * iterator points to null.
         *
         * @return If next is null based on the current position of iterator
         */
        public boolean hasNext() {
            return next != null;
        }

        /**
         * This method advances the iterator to the next node and returns the
         * node that was advanced to
         * @return the node that was advanced to 
         */
        MyBSTNode<K, V> nextNode() {
            //throw null exception if there is no next node
            if(!this.hasNext()){
                throw new NullPointerException();
            }
            //initialize variable to store initial next Node
            MyBSTNode<K,V> initNext = next;
           
            //advance iterator to next node
            next = next.successor();
            lastVisited = initNext;

            //return node we advanced to
            return initNext;
        }

        /**
         * This method removes the last visited node from the tree.
         */
        public void remove() {
            if (lastVisited == null) { //iterator has not advanced or remove 
                                       //was just called
                throw new IllegalStateException(); // throw illegal exception
            }
            if (lastVisited.getRight() != null &&
                    lastVisited.getLeft() != null) { 
                    // lastVisited is not a leaf node
                next = lastVisited; // move iterator position back
            }
            //remove lastVisited node from BST
            MyBSTIterator.this.remove(lastVisited.getKey());
            lastVisited = null; // set to null - cannot remove again until 
                                //iterator is advanced
        }
    }

    /**
     * BST Key iterator class that extends the node iterator.
     */
    class MyBSTKeyIterator extends MyBSTNodeIterator<K> {

        MyBSTKeyIterator(MyBSTNode<K, V> first) {
            super(first);
        }

        /**
         * This method advance the iterator and returns a node key
         *
         * @return K the next key
         */
        public K next() {
            return super.nextNode().getKey();
        }
    }

    /**
     * BST value iterator class that extends the node iterator.
     */
    class MyBSTValueIterator extends MyBSTNodeIterator<V> {

        /**
         * Call the constructor method from node iterator
         *
         * @param first The initial value that next points
         */
        MyBSTValueIterator(MyBSTNode<K, V> first) {
            super(first);
        }

        /**
         * This method advance the iterator and returns a node value
         *
         * @return V the next value
         */
        public V next() {
            return super.nextNode().getValue();
        }
    }

    /**
     * This method is used to obtain an iterator that iterates through the
     * value of BST.
     *
     * @return The key iterator of BST.
     */
    public MyBSTKeyIterator getKeyIterator() {
        MyBSTNode<K, V> curr = root;
        if (curr != null) {
            while (curr.getLeft() != null) {
                curr = curr.getLeft();
            }
        }
        return new MyBSTKeyIterator(curr);
    }

    /**
     * This method is used to obtain an iterator that iterates through the
     * value of BST.
     *
     * @return The value iterator of BST.
     */
    public MyBSTValueIterator getValueIterator() {
        MyBSTNode<K, V> curr = root;
        if (curr != null) {
            while (curr.getLeft() != null) {
                curr = curr.getLeft();
            }
        }
        return new MyBSTValueIterator(curr);
    }
}