/**
 * TODO: Add your file header
 * Name:
 * ID:
 * Email:
 * Sources used: Put "None" if you did not have any external help
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * 2-4 sentence file description here
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

//TODO Add class header for this and following inner classes
public class MyBSTIterator<K extends Comparable<K>,V> extends MyBST<K,V>
{
    abstract class MyBSTNodeIterator<T> implements Iterator<T>{
        MyBSTNode<K,V> next;
        MyBSTNode<K,V> lastVisited;

        /**
         * Constructor that initializes the node iterator
         * @param first The initial node that next points
         */
        MyBSTNodeIterator(MyBSTNode<K,V> first){
            next = first;
            lastVisited = null;
        }

        /**
         * This method is used for determining if the next pointer in the 
         * iterator points to null.
         * @return If next is null based on the current position of iterator
         */
        public boolean hasNext(){
            return next != null;
        }

        // TODO add method header
        MyBSTNode<K,V> nextNode(){
            // TODO
            return null;
        }
        
        /**
         * This method removes the node returned by prevous call to nextNode
         */
        public void remove(){
            //TODO Understand this method add your own comment for each 
            /// line of code. Then delete "TODO".
            if(lastVisited == null){throw new IllegalStateException();}
            if(lastVisited.getRight() != null&& lastVisited.getLeft() != null){
                next = lastVisited;
            }
            MyBSTIterator.this.remove(lastVisited.getKey());
            lastVisited = null;
        }
    }

    // BST Key iterator class that extends the node iterator
    class MyBSTKeyIterator extends MyBSTNodeIterator<K>{

        MyBSTKeyIterator(MyBSTNode<K,V> first){
            super(first);
        }

        /**
         * This method advance the iterator and returns a node key
         * @return K the next key
        */
        public K next(){
            return super.nextNode().getKey();
        }
    }

    // BST value iterator class that extends the node iterator
    class MyBSTValueIterator extends MyBSTNodeIterator<V>{

        /**
         * Call the constructor method from node iterator
         * @param first The initial value that next points
         */
        MyBSTValueIterator(MyBSTNode<K,V> first){
            super(first);
        }

        /**
         * This method advance the iterator and returns a node value
         * @return V the next value
         */
        public V next(){
            return super.nextNode().getValue();
        }
    }

    /**
     * This method is used to obtain an iterator that iterates through the 
     * value of BST.
     * @return The value iterator of BST.
     */
    public MyBSTKeyIterator getKeyIterator(){
        MyBSTNode<K,V> curr = root;
        if(curr != null){
            while(curr.getLeft() != null){
                curr = curr.getLeft();
            }
        }
        return new MyBSTKeyIterator(curr);
    }

    /**
     * This method is used to obtain an iterator that iterates through the 
     * value of BST.
     * @return The value iterator of BST.
     */
    public MyBSTValueIterator getValueIterator(){
        MyBSTNode<K,V> curr = root;
        if(curr != null){
            while(curr.getLeft() != null){
                curr = curr.getLeft();
            }
        }
        return new MyBSTValueIterator(curr);
    }
}