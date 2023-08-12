
import java.util.NoSuchElementException;

/**
 * This class models the implementation of the Binary Search Tree
 */

public class PasswordStorage {

    protected PasswordNode root; //the root of this BST that contains passwords
    private int size; //how many passwords are in the BST
    private final Attribute COMPARISON_CRITERIA; //what password information to use to determine order in the tree

    /**
     * Constructor that creates an empty BST and sets the comparison criteria.
     * @param comparisonCriteria, the Attribute that will be used to determine order in the tree
     */
    public PasswordStorage(Attribute comparisonCriteria) {
        this.root = null;
        COMPARISON_CRITERIA = comparisonCriteria;
        size = 0;
    }

    /**
     * Getter for this BST's criteria for determining order in the three
     * @return the Attribute that is being used to make comparisons in the tree
     */
    public Attribute getComparisonCriteria() {
        return COMPARISON_CRITERIA;
    }

    /**
     * Getter for this BST's size.
     * @return the size of this tree
     */
    public int size() {
        return size;
    }

    /**
     * Determines whether or not this tree is empty.
     * @return true if it is empty, false otherwise
     */
    public boolean isEmpty() {
       return  size == 0;
    }

    /**
     * Provides in-order String representation of this BST, with each Password on its own line. The
     * String representation ends with a newline character ('\n')
     * @return this BST as a string
     */
    @Override
    public String toString() {
        return toStringHelper(this.root);
    }

    /**
     * Recursive method the uses an in-order traversal to create the string representation of this tree.
     * @param currentNode, the root of the current tree
     * @return the in-order String representation of the tree rooted at current node
     */
    private String toStringHelper(PasswordNode currentNode) {
        // BASE CASE- if the current node is null,string representation of the tree will be null
        if(currentNode == null){
            return "";
        }
        // getting a string representation of the elements in the left subtree

         String leftSubTreeString = toStringHelper(currentNode.getLeft());
         String passwordString = currentNode.getPassword().toString() + "\n";

        // getting a string representation of the elements in the right subtree
        String rightSubTreeString = toStringHelper(currentNode.getRight());

        // returning the string representation of the complete tree
        return  leftSubTreeString+ passwordString + rightSubTreeString;

    }

    /**
     * Determines whether or not this tree is actually a valid BST.
     * @return true if it is a BST, false otherwise
     */
    public boolean isValidBST() {
        return isValidBSTHelper(this.root, Password.getMinPassword(), Password.getMaxPassword(),COMPARISON_CRITERIA);
    }

    /**
     * Recurisvely determines if the tree rooted at the current node is a valid BST. That is, every
     * value to the left of currentNode is "less than" the value in currentNode and every value to the
     * right of it is "greater than" it.
     *
     * @param currentNode, the root node of the current tree
     * @param lowerBound, the smallest possible password
     * @param upperBound, the largest possible password
     * @return true if the tree rooted at currentNode is a BST, false otherwise
     */
    private boolean isValidBSTHelper(PasswordNode currentNode, Password lowerBound, Password upperBound,Attribute a) {
      // implemented recursively

        // BASE CASE 1: the tree rooted at currentNode is empty, which does not violate any BST rules

        if(currentNode == null){
            return true;
        }

        Password currPassword = currentNode.getPassword();

        // BASE CASE 2: the current Password is outside of the upper OR lower bound for this subtree, which is against
        //              the rules for a valid BST

        if (currentNode.getPassword().compareTo(lowerBound,a) < 0 || currentNode.getPassword().compareTo(upperBound,a) > 0) {
            return false;
        }

        // If we do not have a base case situation, we must use recursion to verify currentNode's child subtrees

        // RECURSIVE CASE 1: Check that the left subtree contains only Passwords greater than lowerBound and less than
        //                   currentNode's Password; return false if this property is NOT satisfied

        if (!isValidBSTHelper(currentNode.getLeft(), lowerBound, currPassword, a)) {
            return false;
        }

        // RECURSIVE CASE 2: Check that the right subtree contains only Passwords greater than currentNode's Password
        //                   and less than upperBound; return false if this property is NOT satisfied

        if (!isValidBSTHelper(currentNode.getRight(), currPassword, upperBound, a)) {
            return false;
        }

        // COMBINE RECURSIVE CASE ANSWERS: this is a valid BST if and only if both case 1 and 2 are valid
        return true;
    }

    /**
     * Returns the password that matches the criteria of the provided key.
     * Ex. if the COMPARISON CRITERIA is OCCURRENCE and the key has an occurrence of 10
     * it will return the password stored in the tree with occurrence of 10
     * @param key, the password that contains the information for the password we are searching for
     * @return the Password that matches the search criteria, if it does not exist in the tree it this will be null
     */
    public Password lookup(Password key) {
        return lookupHelper(key, root);
    }

    /**
     * Recursive helper method to find the matching password in this BST
     * @param key, password containing the information we are searching for
     * @param currentNode, the node that is the current root of the tree
     * @return the Password that matches the search criteria, if it does not exist in the tree it this will be null
     */
    private Password lookupHelper(Password key, PasswordNode currentNode) {

        // if the current node is null, the key and the passwords can never match and so null is returned
        if (currentNode == null) {
            return null;
        }

        // compareTo method used for comparison
        int comparison = key.compareTo(currentNode.getPassword(), getComparisonCriteria());

        // if the key matches with the password,we will simply return the password itself and we are done
        if (comparison == 0) {

            return currentNode.getPassword();
        }
        // if the key is less than the password we will search in the left subtree
        else if (comparison < 0) {

            return lookupHelper(key, currentNode.getLeft());
            // if the key is less than the password we will search in the left subtree
        } else {

            return lookupHelper(key, currentNode.getRight());
        }

    }

    /**
     * Returns the best (max) password in this BST
     *
     * @return the best password in this BST
     * @throws NoSuchElementException if the BST is empty
     */
    public Password getBestPassword() {

       if(isEmpty()){
           throw new NoSuchElementException("BST is empty");
       }
       PasswordNode currNode = root;
       while(currNode.getRight() != null){
           // if the current node's right subtree element is not null, we will search for the max password in the right tree
           currNode = currNode.getRight();
       }
       return currNode.getPassword();
    }

    /**Returns the worst password in this BST
     *
     * @return the worst password in this BST
     * @throws NoSuchElementException if the BST is empty
     */
    public Password getWorstPassword() {

        if(isEmpty()){
            throw new NoSuchElementException("BST is empty");
        }
        PasswordNode currNode = root;
        // if the current node's left subtree element is not null, we will search for the min password in the left tree
        while(currNode.getLeft() != null){
            currNode = currNode.getLeft();
        }
        return currNode.getPassword();

    }

    /**
     * Adds the Password to this BST.
     *
     * @param toAdd, the password to be added to the tree
     * @throws IllegalArgumentException if the (matching) password object is already in the tree
     */
    public void addPassword( Password toAdd) {

        if(root == null){
            // adding a new node as a root node itself
            root = new PasswordNode(toAdd);
            size++;
        }
        else {
            if (addPasswordHelper(toAdd, root)) {
                size++;
            }else {
                throw new IllegalArgumentException();
            }
        }

    }

    /**
     * Recursive helper that traverses the tree and adds the password where it belongs
     * @param toAdd, the password to add to the tree
     * @param currentNode, the node that is the current root of the (sub)tree
     * @return true if it was successfully added, false otherwise
     */
    private boolean addPasswordHelper(Password toAdd, PasswordNode currentNode) {

        Password currentNodePassword = currentNode.getPassword();

        int checking = currentNodePassword.compareTo(toAdd, this.COMPARISON_CRITERIA);
        if (checking > 0) {
            if (currentNode.getLeft() == null) {
                currentNode.setLeft(new PasswordNode(toAdd));
                return true;
            } else {
                return addPasswordHelper(toAdd, currentNode.getLeft());
            }
        } else if (checking < 0) {
            if (currentNode.getRight() == null) {
                currentNode.setRight(new PasswordNode(toAdd));
                return true;
            } else {
                return addPasswordHelper(toAdd, currentNode.getRight());
            }
        } else {
            return false;
        }
    }


    /**
     * Removes the matching password from the tree
     * @param toRemove, the password to be removed from the tree
     * @throws NoSuchElementException if the password is not in the tree
     */
    public void removePassword(Password toRemove){
      if(isEmpty()){
          throw new NoSuchElementException("Tree is empty");
      }
      root = removePasswordHelper(toRemove,root);
      size--;

    }

    /**
     * Recursive helper method to that removes the password from this BST.
     * @param toRemove, the password to be removed from the tree
     * @param currentNode, the root of the tree we are removing from
     * @return the PasswordNode representing the NEW root of this subtree now that toRemove
     * has been removed. This may still be currentNode, or it may have changed!
     */
    private PasswordNode removePasswordHelper(Password toRemove, PasswordNode currentNode) {

        //BASE CASE: current tree is empty
        if(currentNode == null){
            return null;
        }

        int comparingPasswords = toRemove.compareTo(currentNode.getPassword(),COMPARISON_CRITERIA);
        //RECURSIVE CASE: toRemove is in the left subtree, continue searching
        if(comparingPasswords <0){
            currentNode.setLeft(removePasswordHelper(toRemove, currentNode.getLeft()));
        } else if (comparingPasswords >0) {
            currentNode.setRight(removePasswordHelper(toRemove, currentNode.getRight()));
        }
        //RECURSIVE CASE: toRemove is in the right subtree, continue searching
        //otherwise we found the node to remove!
        else {
            if(!currentNode.hasLeftChild()){
                currentNode = currentNode.getRight();
            } else if (!currentNode.hasRightChild()) {
                currentNode = currentNode.getLeft();
            }
           else{
                Password predecessor1 = getPredecessor(currentNode);
                currentNode = new PasswordNode(predecessor1, currentNode.getLeft(), currentNode.getRight());
                currentNode.setLeft(removePasswordHelper(predecessor1, currentNode.getLeft()));
            }
            }
        return currentNode;
        }

        //BASE CASE: current node has no children
        //BASE CASE(S): current node has one child (one for the left and right respectively)
        //RECURSIVE CASE: currentNode has 2 children

        //3)Replace currentNode with the new predecessor node
        //4)Remove the (duplicate) predecessor from the current tree and update the left subtree

    //1)Find the predecessor password [HINT: Write a private helper method!]
    private Password getPredecessor(PasswordNode currentNode) {
        //2)Make new node for the predecessor password. It should have same left and right subtree as the current node.
        PasswordNode nodePredecessor = currentNode.getLeft();
        while (nodePredecessor.hasRightChild()) {
            nodePredecessor = nodePredecessor.getRight();
        }
        return nodePredecessor.getPassword();
    }

    }

