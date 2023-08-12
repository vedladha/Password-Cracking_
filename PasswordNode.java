
public class PasswordNode{

    private Password password; // the password data this node stores
    private PasswordNode left; // a reference to node that is the left child
    private PasswordNode right; // a reference to the node that is the right child

    /**
     * 1-argument constructor that sets the only data of the node.
     *
     * @param password the password for this node to store
     * @author Michelle
     */
    public PasswordNode(Password password) {
        this.password = password;
    }

    /**
     * 3-argument constructor that sets all three data field
     *
     * @param password, password the password for this node to store
     * @param left,     the reference to the node that is the left child
     * @param right,    the reference to the node that is the right child
     *
     * @author Michelle
     */
    public PasswordNode(Password password, PasswordNode left, PasswordNode right) {
        this(password);
        this.left = left;
        this.right = right;
    }

    /**
     * Setter for left data field
     *
     * @param left, the reference to the node to be the left child
     *
     * @author Michelle
     */
    public void setLeft(PasswordNode left) {
        this.left = left;
    }

    /**
     * Setter for right data field
     *
     * @param right, the reference to the node to be the right child
     *
     * @author Michelle
     */
    public void setRight(PasswordNode right) {
        this.right = right;
    }

    /**
     * Getter for left data field
     *
     * @return the reference to the node that is the left child
     *
     * @author Michelle
     */
    public PasswordNode getLeft() {
        return this.left;
    }

    /**
     * Getter for right data field
     *
     * @return the reference to the node that is the right child
     *
     * @author Michelle
     */
    public PasswordNode getRight() {
        return this.right;
    }

    /**
     * Getter for password data field
     *
     * @return the password object that this node stores
     *
     * @author Michelle
     */
    public Password getPassword() {
        return this.password;
    }

    /**
     * Determines if the current node is a leaf node
     *
     * @return true if this node is a leaf, false otherwise
     *
     * @author Vedant Ladha
     */
    public boolean isLeafNode() {
        // checking if the root node has a left and right child

       if(left == null && right == null){
           return true;
       }

       // if the root node itself is a leaf node
       else{
           return false;
       }
    }

    /**
     * Determines if the current node has a right child
     *
     * @return true if this node has a right child, false otherwise
     *
     * @author Vedant Ladha
     */
    public boolean hasRightChild() {
        // checking if the right child is not null
       if(right != null ){
           return true;
       }

       // if the right child is null
       else {
           return false;
       }
    }

    /**
     * Determines if the current node has a left child
     *
     * @return true if this node has a left child, false otherwise
     *
     * @author Vedant Ladha
     */
    public boolean hasLeftChild() {
        // checking if the left child is not null
        if(left != null ){
            return true;
        }
        // if the left child is null
        else {
            return false;
        }
    }

    /**
     * Determines how many children nodes this node has. RECALL: Nodes in a binary tree can have AT
     * MOST 2 children
     *
     * @return The number of children this node has
     *
     * @author Vedant Ladha
     */
    public int numberOfChildren() {

        // checking if the left child is not null and increasing the value of the number of children

        int indx =0;
        if(left != null){
            indx++;
        }

        // checking if the right child is not null and increasing the value of the number of children
        if(right !=null){
            indx++;
        }

        return indx;  // returns the total number of children in the tree

    }

}
