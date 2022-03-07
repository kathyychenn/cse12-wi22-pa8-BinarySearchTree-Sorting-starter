import java.util.ArrayList;


public class MyBST<K extends Comparable<K>,V>{
    MyBSTNode<K,V> root = null;
    int size = 0;

    private final String NULL_EXCEPTION = "invalid null arg";

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
            throw new NullPointerException(NULL_EXCEPTION);
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
        if(key == null){
            return null;
        }
        //helper method search thru tree for given key
        return searchHelper(this.root, key);
    }

    /**
     * private recursive helper method for search
     * @param curr - node representing current location during iteration of BTS
     * @param key - key to be searched for throughout BST
     * @return value of given key within BTS, null if key not found
     */
    private V searchHelper(MyBSTNode<K,V> curr, K key){
        //return null if key not found
        if(curr == null){
            return null;
        }
        //return curr value if equal to given key
        if(curr.getKey().equals(key)){
            return curr.getValue();
        }
        //recursive call with curr right if curr key greater than given key
        if(curr.getKey().compareTo(key) < 0){
            return searchHelper(curr.getRight(), key);
        }
        //recursive call with curr left if curr key less than given key
        else{
            return searchHelper(curr.getLeft(), key);
        }
    }

    public V remove(K key){
        // TODO
        if(key == null){
            return null;
        }
        return null;
    }
    
    public ArrayList<MyBSTNode<K, V>> inorder(){
        // TODO
        if(this.size() == 0){
            return new ArrayList<MyBSTNode<K, V>>();
        }
        return null;
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