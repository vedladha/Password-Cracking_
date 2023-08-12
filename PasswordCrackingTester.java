
/**
 * This class is a tester class
 */


public class PasswordCrackingTester {

    /**
     * Validates the constructor and accessor methods of PasswordStorage, specifically the
     * getComparisonCriteria(), size(), and isEmpty() methods, as well as accessing the
     * protected data field root.
     *
     * Be sure to try making multiple PasswordStorage objects with different Attributes.
     * @return true if the basic accessor methods work as expected, false otherwise
     */
    public static boolean testBasicPasswordStorageMethods() {

        // creating 3 storage objects

        PasswordStorage str1 = new PasswordStorage(Attribute.OCCURRENCE);
        PasswordStorage str2 = new PasswordStorage(Attribute.STRENGTH_RATING);
        PasswordStorage str3 = new PasswordStorage(Attribute.HASHED_PASSWORD);

        // testing the getComparisonCriteria() method

        if(str1.getComparisonCriteria() != Attribute.OCCURRENCE || str2.getComparisonCriteria() != Attribute.STRENGTH_RATING
                || str3.getComparisonCriteria() != Attribute.HASHED_PASSWORD){
            return false;
        }

        // testing the size method
        if(str1.size() != 0 || str2.size() !=0 || str3.size() !=0){
            return false;
        }

        //testing isEmpty() method

        if(!str1.isEmpty() || !str2.isEmpty() || !str3.isEmpty()){
            return false;
        }

        // testing accessing the protected data field root

        if(str1.root != null  || str2.root != null || str3.root != null){
            return false;
        }

        return true; // All test cases passed
    }

    /**
     * Validates the Password class compareTo() method. Create at least two DIFFERENT
     * Password objects and compare them on each of the Attribute values. See the writeup
     * for details on how the various comparisons are expected to work.
     *
     * @return true if Password's compareTo() works as expected, false otherwise
     */
    public static boolean testPasswordCompareTo() {

        // creating 2 random passwords for testing purposes
        Password p1 = new Password("password2",1200);
        Password p2 = new Password("Password1",20);


        // Test comparison by occurrence
        if (p1.compareTo(p2, Attribute.OCCURRENCE) <= 0
                || p2.compareTo(p1, Attribute.OCCURRENCE) >= 0) {
            return false;
        }


        // Test comparison by strength rating
        if (p1.compareTo(p2, Attribute.STRENGTH_RATING) >= 0
                || p2.compareTo(p1, Attribute.STRENGTH_RATING) <= 0) {
            return false;
        }

        // Test comparison by hashed password
        if (p1.compareTo(p2, Attribute.HASHED_PASSWORD) >= 0
                || p2.compareTo(p1, Attribute.HASHED_PASSWORD) <= 0) {
            return false;
        }

        return true; // All test cases passed
    }

    /**
     * Validates the incomplete methods in PasswordNode, specifically isLeafNode(),
     * numberOfChildren(), hasLeftChild() and hasRightChild(). Be sure to test all
     * possible configurations of a node in a binary tree!
     *
     * @return true if the status methods of PasswordNode work as expected, false otherwise
     */
    public static boolean testNodeStatusMethods() {

        // Creates  Password objects to be used in the nodes

        Password p1 = new Password("password293", 590);
        Password p2 = new Password("Password12", 1020);
        Password p3 = new Password("MyHomePassword", 2300);

        // Creates PasswordNode objects to be used in the Binary

        PasswordNode leaf1 = new PasswordNode(p1);
        PasswordNode leaf2 = new PasswordNode(p2);
        PasswordNode internalNode1 = new PasswordNode(p3, leaf1, leaf2);
        PasswordNode internalNode2 = new PasswordNode(p1, null, internalNode1);

        // Testing isLeafNode() method on different nodes

        if (internalNode1.isLeafNode()) {
            return false;
        }

        if (!leaf1.isLeafNode()) {
            return false;
        }


        // Testing numberOfChildren() method on different nodes
        if (leaf1.numberOfChildren() != 0) {
            return false;
        }
        if (leaf2.numberOfChildren() != 0) {
            return false;
        }
        if (internalNode1.numberOfChildren() != 2) {
            return false;
        }
        if (internalNode2.numberOfChildren() != 1) {
            return false;
        }

        // Test hasLeftChild() method on different nodes
        if (leaf1.hasLeftChild()) {
            return false;
        }
        if (leaf2.hasLeftChild()) {
            return false;
        }
        if (!internalNode1.hasLeftChild()) {
            return false;
        }
        if (internalNode2.hasLeftChild()) {
            return false;
        }

        // Test hasRightChild() method on different nodes

        if (leaf1.hasRightChild()) {
            return false;
        }
        if (leaf2.hasRightChild()) {
            return false;
        }
        if (!internalNode1.hasRightChild()) {
            return false;
        }
        if (!internalNode2.hasRightChild()) {
            return false;
        }

        // All tests passed
        return true;
    }

    // GIVE TO STUDENTS
    public static boolean testToString() {
        try {
            PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

            // empty is empty string
            String expected = "";
            String actual = bst.toString();
            if (!actual.equals(expected)) {
                System.out.println("toString() does not return the proper value on an empty tree!");
                return false;
            }

            // size one only returns 1 thing
            Password p = new Password("1234567890", 15000);
            PasswordNode rootNode = new PasswordNode(p);

            bst.root = rootNode; // here I am manually building the tree by editing the root node
            // directly to be the node of my choosing

            expected = p.toString() + "\n";
            actual = bst.toString();
            if (!actual.equals(expected))
                return false;


            // big tree returns in-order traversal
            Password p2 = new Password("test", 500);
            Password p3 = new Password("iloveyou", 765);
            Password p4 = new Password("qwerty", 250);
            Password p5 = new Password("admin", 1002);
            Password p6 = new Password("password", 2232);
            Password p7 = new Password("abc123", 2090);

            PasswordNode p4Node = new PasswordNode(p4);
            PasswordNode p3Node = new PasswordNode(p3);
            PasswordNode p7Node = new PasswordNode(p7);
            PasswordNode p6Node = new PasswordNode(p6, p7Node, null);
            PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
            PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
            rootNode = new PasswordNode(p, p2Node, p5Node);
            bst.root = rootNode;

            expected = p4.toString() + "\n" + p2.toString() + "\n" + p3.toString() + "\n" + p.toString()
                    + "\n" + p5.toString() + "\n" + p7.toString() + "\n" + p6.toString() + "\n";
            actual = bst.toString();

            if (!actual.equals(expected))
                return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // GIVE TO STUDENTS
    public static boolean testIsValidBST() {
        try {
            PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

            if (!bst.isValidBST()) {
                System.out.println("isValidBST() says that an empty tree is not a valid BST!");
                return false;
            }


            Password p = new Password("1234567890", 1000);
            PasswordNode rootNode = new PasswordNode(p);

            bst.root = rootNode;

            if (!bst.isValidBST()) {
                System.out.println("isValidBST() says that a tree of size 1 is not a valid BST!");
                return false;
            }

            Password p2 = new Password("Mine", 590);
            Password p3 = new Password("Hey", 720);
            Password p4 = new Password("Pass", 280);
            Password p5 = new Password("Work", 1200);
            Password p6 = new Password("Pass2", 2400);
            Password p7 = new Password("Pass3", 2090);

            // works on indentifying small obviously invalid tree
            PasswordNode p7Node = new PasswordNode(p7);
            PasswordNode p3Node = new PasswordNode(p3);
            rootNode = new PasswordNode(p, p7Node, p3Node);
            bst.root = rootNode;
            if (bst.isValidBST())
                return false;

            // tree with only one subtree being valid, other subtree has a violation a couple layers deep


            PasswordNode p4Node = new PasswordNode(p4);
            p7Node = new PasswordNode(p7);
            p3Node = new PasswordNode(p3);
            PasswordNode p6Node = new PasswordNode(p6, null, p7Node);
            PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
            PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
            rootNode = new PasswordNode(p, p2Node, p5Node);
            bst.root = rootNode;

            if (bst.isValidBST()) {
                System.out
                        .println("isValidBST() says that a tree with only one valid subtree is a valid bst");
                return false;
            }


            // works on valid large tree
            p4Node = new PasswordNode(p4);
            p3Node = new PasswordNode(p3);
            p7Node = new PasswordNode(p7);
            p6Node = new PasswordNode(p6, p7Node, null);
            p5Node = new PasswordNode(p5, null, p6Node);
            p2Node = new PasswordNode(p2, p4Node, p3Node);
            rootNode = new PasswordNode(p, p2Node, p5Node);
            bst.root = rootNode;

            if (!bst.isValidBST())
                return false;

            PasswordNode one = new PasswordNode(p4);
            PasswordNode three = new PasswordNode(p3, one, null);
            PasswordNode two = new PasswordNode(p2, null, three);
            bst.root = two;

            if (bst.isValidBST()) {
                System.out.println("bad bst is valid");
                return false;
            }


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Tests the lookup method of the Password Storage Class
     * @return true,if the lookup() method works correctly ,false otherwise
     */
    public static boolean testLookup() {

        PasswordStorage str = new PasswordStorage(Attribute.OCCURRENCE);

        Password p1 = new Password("MyPassword", 2);
        Password p2 = new Password("MyPassword1", 3);
        Password p3 = new Password("MyPassword2", 4);
        Password p4 = new Password("MyPassword3", 6);
        Password p5 = new Password("MyPassword4", 5);
        Password p6 = new Password("MyPassword5", 1);

        str.addPassword(p1);
        str.addPassword(p2);
        str.addPassword(p3);
        str.addPassword(p4);
        str.addPassword(p5);
        str.addPassword(p6);

        // Test lookup() method
        if (!p1.equals(str.lookup(new Password("MyPassword", 2)))
                || !p2.equals(str.lookup(new Password("MyPassword1", 3)))
                || !p3.equals(str.lookup(new Password("MyPassword2", 4)))
                || !p4.equals(str.lookup(new Password("MyPassword3", 6)))
                || !p5.equals(str.lookup(new Password("MyPassword4", 5)))
                || !p6.equals(str.lookup(new Password("MyPassword5", 1)))) {
            return false;
        }

        if (str.lookup(new Password("DNE", 7)) != null) {
            return false;
        }

        return true;
    }

    /**
     *Tests the addPassword() method of the PasswordStorage class
     * @return true,if the tests pass and false otherwise
     */
    public static boolean testAddPassword() {

        PasswordStorage str = new PasswordStorage(Attribute.OCCURRENCE);

        // creates 3 password objects

        Password p1 = new Password("MyOwPassword", 2);
        Password p2 = new Password("MyOwnPassword2", 3);
        Password p3 = new Password("MyOwnPassword3", 4);

        // Testing the  addPassword() method
        str.addPassword(p1);
        str.addPassword(p2);
        str.addPassword(p3);

        if (!p1.equals(str.lookup(new Password("MyOwPassword", 2)))
                ||!p3.equals(str.lookup(new Password("MyOwPassword", 4)))
                || !p2.equals(str.lookup(new Password("MyOwPassword", 3)))
                ) {
            return false;
        }

        try {
            str.addPassword(p1);
            return false;
        } catch (IllegalArgumentException e) {
            // expected exception
        }

        return true;
    }


    /**
     *Tests the removePassword() method of PasswordStorage class.
     * @return true,if the tests pass and false otherwise
     */
    public static boolean testRemovePassword() {
        try {
            PasswordStorage str = new PasswordStorage(Attribute.OCCURRENCE);

            //creating password objects
            
            Password p1 = new Password("MyOwnPassword", 1);
            Password p2 = new Password("MyOwnPassword2", 2);
            Password p3 = new Password("MyOwnPassword3", 3);
            Password p4 = new Password("MyOwnPassword4", 4);
            Password p5 = new Password("MyOwnPassword5", 5);
            Password p6 = new Password("MyOwnPassword6", 6);
            Password p7 = new Password("MyOwnPassword7", 7);

            // Adding the created  passwords to the storage
            str.addPassword(p4);
            str.addPassword(p2);
            str.addPassword(p6);
            str.addPassword(p1);
            str.addPassword(p3);
            str.addPassword(p5);
            str.addPassword(p7);


            str.removePassword(new Password("Westwood", 7));
            if (str.size() != 6) {
                return false;
            }
            if (str.root.getRight().hasRightChild()) {
                return false;
            }


            str.removePassword(new Password("Westwood", 6));
            if (str.size() != 5) {
                return false;
            }
            if (!str.root.getRight().isLeafNode()
                    || !str.root.getRight().getPassword().equals(p5)) {
                return false;
            }


            str.removePassword(new Password("Westwood", 4));
            if (str.size() != 4) {
                return false;
            }
            if (!str.root.getPassword().equals(p3)) {
                return false;
            }
            if (!str.root.getLeft().getPassword().equals(p2)
                    || !str.root.getLeft().getLeft().getPassword().equals(p1)
                    || !str.root.getRight().getPassword().equals(p5)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true; // tests passed
    }


    public static void main(String[] args) {
        runAllTests();
    }


    public static boolean runAllTests() {
        boolean compareToPassed = testPasswordCompareTo();
        boolean nodeStatusPassed = testNodeStatusMethods();
        boolean basicMethodsPassed = testBasicPasswordStorageMethods();
        boolean toStringPassed = testToString();
        boolean isValidBSTPassed = testIsValidBST();
        boolean lookupPassed = testLookup();
        boolean addPasswordPassed = testAddPassword();
        boolean removePasswordPassed = testRemovePassword();

        System.out.println("Password compareTo: " + (compareToPassed ? "PASS" : "FAIL"));
        System.out.println("PasswordNode Status Methods: " + (nodeStatusPassed ? "PASS" : "FAIL"));
        System.out.println("PasswordStorage Basic Methods: " + (basicMethodsPassed ? "PASS" : "FAIL"));
        System.out.println("PasswordStorage toString: " + (toStringPassed ? "PASS" : "FAIL"));
        System.out.println("PasswordStorage isValidBST: " + (isValidBSTPassed ? "PASS" : "FAIL"));
        System.out.println("PasswordStorage lookup: " + (lookupPassed ? "PASS" : "FAIL"));
        System.out.println("PasswordStorage addPassword: " + (addPasswordPassed ? "PASS" : "FAIL"));
        System.out.println("PasswordStorage removePassword: " + (removePasswordPassed ? "PASS" : "FAIL"));

        // AND ANY OTHER ADDITIONAL TEST METHODS YOU MAY WANT TO WRITE!

        return compareToPassed && nodeStatusPassed && basicMethodsPassed && toStringPassed
                && isValidBSTPassed && lookupPassed && addPasswordPassed && removePasswordPassed;
    }

}
