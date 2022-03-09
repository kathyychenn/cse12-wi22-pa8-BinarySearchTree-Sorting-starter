/**
 * Name: Kathy Chen
 * PID: A17030814
 * Sources:
 * https://learn.zybooks.com/zybook/UCSDCSE12Winter2022/chapter/7/section/6
 * Lecture Notes Week 7 Trees and BSTs
 * 
 * This is the MyBST java file for PA 8. It contains the MyBST generic class 
 * which extends the generic Comparable interface and the static MyBSTNode 
 * generic class within.
 */

import java.util.ArrayList;

/**
 * A MyBST class extends the generic interface Comparable and holds the BST
 * size and a reference to the root node. It contains modifier methods to add
 * new nodes, remove nodes, search for nodes, and conduct a traversal.
 */
public class MyBST<K extends Comparable<K>,V>{
    //initialize instance variables
    MyBSTNode<K,V> root = null;
    int size = 0;

    /**
     * accessor method to return the number of nodes in the BST
     * @return number of nodes in the BST
     */
    public int size(){
        return size;
    }

    /**
     * Insert new node with args key and value into BST according to BST 
     * properties, updating tree size accordingly
     * @param key - key arg of new Node to be inserted
     * @param value - value arg of new Node to be inserted
     * @return value of node that was replaced by new value
     */
    public V insert(K key, V value){
        //throw null exception if key is null
        if(key == null){
            throw new NullPointerException();
        }
        //if BST empty, add new node as root
        if(this.size()==0){
            this.root = new MyBSTNode<K,V>(key,value,null);
            this.size++;
            return null;
        }

        MyBSTNode<K,V> curr = this.root;
        while(curr.predecessor() != null && curr.successor() != null){
            //if node with equal key exists, replace key with new value and
            //return value that was replaced
            if(curr.getKey().equals(key)){
                MyBSTNode<K,V> ret = curr;
                curr.setValue(value);
                return ret.getValue();
            }

            //instance variables for predeccessor and successor of curr
            K currPreKey = curr.predecessor().getKey();
            K currSucKey = curr.successor().getKey();

            //if key is between curr key and either pre key or suc key, exit
            if(key.compareTo(curr.getKey())<0 && key.compareTo(currPreKey)>0 ||
                key.compareTo(curr.getKey())>0 && key.compareTo(currSucKey)<0){
                continue;
            }
            if(key.compareTo(curr.getKey())>0){
                curr = curr.successor();
            }
            else{
                curr = curr.predecessor();
            }
        }

        //node to insert
        MyBSTNode<K,V> newNode = new MyBSTNode<K,V>(key, value, curr);

        //set left and parent variables if key is less than curr key
        if(key.compareTo(curr.getKey()) < 0){     
            if(curr.getLeft() != null){
                newNode.setLeft(curr.getLeft());
                newNode.getLeft().setParent(newNode);
            }
            newNode.parent.setLeft(newNode);
        }
        
        //set right and parent variables if key is greater than curr key
        else if(key.compareTo(curr.getKey()) > 0){
            if(curr.getRight() != null)
            {
                newNode.setRight(curr.getRight());
                newNode.getRight().setParent(newNode);
            }
            newNode.parent.setRight(newNode);
        }

        this.size++;
        return null;
    }

    /**
     * Search for node with equal key to given key and return the node's value 
     * @param key - key to be searched for throughout BST
     * @return value of given key within BTS, null if key is null or not found
     */
    public V search(K key){
        //return null if key null
        if(key == null){
            return null;
        }
       
        //initialize curr node variable as BST root
        MyBSTNode<K,V> curr = this.root;
        while (curr != null) {
            //Node with key equal to given key found
            if (key.equals(curr.getKey())) {
                return curr.getValue(); 
            }
            //search left if curr key is less than given key
            else if (key.compareTo(curr.getKey()) < 0) {
                curr = curr.getLeft();
            }
            //search right if curr key is greater than given key
            else {
                curr = curr.getRight();
            }
        }
        //node with same key as given key not found
        return null;
    }

    /**
     * remove a node with key equal to  given key according to BST properties
     * and return the value of removed node, updating tree size accoordingly
     */
    public V remove(K key){
        //initialize instance variables for parent and current node
        MyBSTNode<K,V> par = null;
        MyBSTNode<K,V> curr = this.root;
        
        //return null if key is null
        if(key == null){
            return null;
        }
        //search for node or until whole BST has been searched
        while (curr != null) { 
            V ogCurrValue = curr.getValue();
            //Node found
            if (curr.getKey().equals(key)) { 
                //remove leaf node - no replacement needed
                if (curr.getLeft() == null && curr.getRight() == null) {
                    if (par == null) // Node is root
                        this.root = null;
                    else if (par.getLeft() == curr) //remove left child
                        par.setLeft(null);
                    else par.setRight(null); //remove right child 
                }

                // Remove node with one left child
                else if (curr.getRight() == null) {                
                    if (par == null) // Node is root
                        this.root = curr.getLeft();
                    else if (par.getLeft() == curr) 
                        par.setLeft(curr.getLeft());
                    else
                        par.setRight(curr.getLeft());
                }
                // Remove node with one right child
                else if (curr.getLeft() == null) {                
                    if (par == null) // Node is root
                        this.root = curr.getRight();
                    else if (par.getLeft() == curr) 
                        par.setLeft(curr.getRight());
                    else
                        par.setRight(curr.getRight());
                }
                // Remove node with two children - replace with successor
                else {                                      
                    // Find successor (leftmost child of right subtree)                        
                    MyBSTNode<K,V> sucNode = curr.successor();

                    curr.setKey(sucNode.getKey());
                    curr.setValue(sucNode.getValue());

                    //remove successor from initial position
                    if(sucNode == sucNode.getParent().getLeft()){
                        sucNode.getParent().setLeft(null);
                    }
                    else sucNode.getParent().setRight(null);
                }
                this.size--;
                return ogCurrValue;// Node found and removed
            }
            // Search right
            else if (curr.getKey().compareTo(key) < 0) { 
                par = curr;
                curr = curr.getRight();
            }
            // Search left
            else {                     
                par = curr;
                curr = curr.getLeft();
            }
        }
        // Node with given key not found
        return null;
    }
    
    /**
     * Conduct in-order traversal of BST, adding each node to the end of an 
     * ArrayList which is then returned
     * @return - ArrayList of all nodes sorted in key order, empty if BST empty
     */
    public ArrayList<MyBSTNode<K, V>> inorder(){
        // initialize ArrayList which will be returned
        ArrayList<MyBSTNode<K, V>> ret = new ArrayList<MyBSTNode<K, V>>();

        //return empty ArrayList if BST size is 0
        if(this.size() == 0){
            return ret;
        }

        /**
         * iterate through BST in key order starting at smallest key
         * and add each node to end of ArrayList
         */
        MyBSTNode<K,V> curr = this.min(this.root);
        while(curr.successor()!=null){
            ret.add(curr);
            curr = curr.successor();
        }

        //add last node of BST then return Arraylist
        ret.add(curr);
        return ret;
    }

    //helper method - find smallest (leftmost) key of BST
    private MyBSTNode<K,V> min(MyBSTNode<K,V> curr){
        if(curr.getLeft()==null){
            return curr;
        }
        else{
            return min(curr.getLeft());
        }
    }

    static class MyBSTNode<K,V>{
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K,V> parent;
        private MyBSTNode<K,V> left = null;
        private MyBSTNode<K,V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         * @param key the key the MyBSTNode<K,V> will
         * @param value the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent){
            this.key = key;
            this.value = value;
            this.parent = parent; 
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey(){
            return key;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue(){
            return value;
        }

        /**
         * Return the parent
         * @return the parent
         */
        public MyBSTNode<K,V> getParent(){
            return parent;
        }

        /**
         * Return the left child 
         * @return left child
         */
        public MyBSTNode<K,V> getLeft(){
            return left;
        }

        /**
         * Return the right child 
         * @return right child
         */
        public MyBSTNode<K,V> getRight(){
            return right;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         * @param newKey the key to be stored
         */
        public void setKey(K newKey){
            this.key = newKey;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         * @param newValue the data to be stored
         */
        public void setValue(V newValue){
            this.value = newValue;
        }

        /**
         * Set the parent
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K,V> newParent){
            this.parent = newParent;
        }

        /**
         * Set the left child
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K,V> newLeft){
            this.left = newLeft;
        }

        /**
         * Set the right child
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K,V> newRight){
            this.right = newRight;
        }

        /**
         * This method returns the in order successor of current node object.
         * It can be served as a helper method when implementing inorder().
         * @return the successor of current node object
         */
        public MyBSTNode<K, V> successor(){
            //if right isn't null check right subtree for smallest key still
            //greater than this key
            if(this.getRight() != null){
                MyBSTNode<K,V> curr = this.getRight();
                //check left branch for return node
                while(curr.getLeft() != null){
                    curr = curr.getLeft();
                }
                return curr;
            }
            //if this node has no right child, return parent node if greater
            //if not then this node is greatest key of BST, return null
            else{
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                //iterate to root if this key is greatest key in BST
                while(parent != null && curr == parent.getRight()){
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /**
         * This method returns the in order predecessor of current node object.
         * It can be served as a helper method when implementing inorder().
         * @return the predecessor of current node object
         */
        public MyBSTNode<K, V> predecessor(){
            //if left isn't null check left subtree for smallest key still
            //greater than this key
            if(this.getLeft() != null){
                MyBSTNode<K,V> curr = this.getLeft();
                //check right branch for return node
                while(curr.getRight() != null){
                    curr = curr.getRight();
                }
                return curr;
            }
            //if this node has no left child, return parent node if greater
            //if not then this node is smallest key of BST, return null
            else{
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                //iterate to root if this key is greatest key in BST
                while(parent != null && curr == parent.getLeft()){
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /** This method compares if two node objects are equal.
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj){
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K,V> comp = (MyBSTNode<K,V>)obj;
            
            return( (this.getKey() == null ? comp.getKey() == null : 
                this.getKey().equals(comp.getKey())) 
                && (this.getValue() == null ? comp.getValue() == null : 
                this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         * @return "Key:Value" that represents the node object
         */
        public String toString(){
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}