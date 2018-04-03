package src;
import java.io.*;
/**
 * Created by mohammedawan on 4/8/17.
 */
//This class contains the methods for all of the operations of the BST
public class BinaryTree {

    //Initializes FileWriter and BufferedWriter to write to output file
    FileWriter writer = null;
    BufferedWriter bwriter = null;

    //Declaring the root of the tree
    protected TreeNode root;

    //The Default constructor of the BST
    public BinaryTree(){
        root = null;
    }

    /**This is the add function and it creates a new node and
     * adds the person to the tree by using a hash code on
     * the name as a key
     *
     * @param name the name of the person to be added to the tree
     * @param number the phone number of the person
     * @param address the address of the person
     */

    public void add(String name, String number, String address){

        //Creates a new TreeNode and makes the name lower case so that
        //it's easier to search for later
        TreeNode node = new TreeNode(name.toLowerCase(), number, address);

        //Gets the input name, makes it lower case and hashes it
        String lowerName = name.toLowerCase();
        Integer nameInt = lowerName.hashCode();

        //If the tree is empty then the new node will become the root
        if(root == null){
            root = node;
        } else {

            //Creates nptr & nptr_parent to go through the tree and compare the names to the input
            TreeNode nptr = root;
            TreeNode nptr_parent = root;

            //This loops through the tree until a null value is reached
            // and then the node is added there
            while(nptr != null){
                nptr_parent = nptr;
                int nptrInt = nptr.getName().hashCode();
                if(nptrInt >= nameInt){
                    nptr = nptr.getLeft();
                    if(nptr == null){
                        nptr_parent.setleft(node);
                    }
                }
                else{
                    nptr = nptr.getRight();
                    if(nptr == null){
                        nptr_parent.setRight(node);
                    }
                }
            }
        }
    }

    /**This is the retrieve function and it takes the name as an input
     * and hashes it to compare to each node in the tree until
     * the person is found
     *
     * @param name the name of the person you want to retrieve
     * @return the entire node of the person you wanted to retrieve
     */

    public TreeNode retrieve(String name){

        //nptr is created at the root and will be used
        //to find the node
        TreeNode nptr = root;

        //Gets the input name, makes it lower case and hashes it
        String lowerName = name.toLowerCase();
        Integer inputInt = lowerName.hashCode();

        //This will loop through the tree comaring the entered
        //name to the other names until it is found
        while(nptr != null){
            int nptrInt = nptr.getName().hashCode();

            if(nptrInt == inputInt){
                return nptr;
            } else if(inputInt < nptrInt){
                nptr = nptr.getLeft();
            } else if(inputInt > nptrInt){
                nptr = nptr.getRight();
            }
        }
        //If it isn't found then the method will return null
        return null;
    }

    /**This is the update method and it will retrieve the node
     * of the name that was input and will then update the address or
     * phone number
     *
     * @param name The name of the person who's info will be updated
     * @param newAddress The new address
     * @param newNumber The new number
     * @return It returns the updated node so that the new info can be printed
     */
    public TreeNode update(String name, String newAddress, String newNumber){

        //This just calls the retrieve function with the name given
        TreeNode editPerson = retrieve(name.toLowerCase());

        //If the User input any new info then it will be changed
        if(!newAddress.equalsIgnoreCase("na")){
            editPerson.setAddress(newAddress);
        }
        if(!newNumber.equalsIgnoreCase("na")){
            editPerson.setPhoneNumber(newNumber);
        }

        //The method then returns the changed node
        return editPerson;
    }

    /**This is the delete function and the User inputs the name
     * of a person that they want to delete and the method
     *  removes it from the tree
     *
     * @param name The name of the person the User wants to delete
     * @return It returns the deleted node to print which person is deleted
     */
    public TreeNode delete(String name) {

        //Make a nptr and nptr parent to use to delete the node
        TreeNode nptr = root;
        TreeNode nptr_parent = root;

        //Gets the input name, makes it lower case and hashes it
        String lowerName = name.toLowerCase();
        Integer inputInt = lowerName.hashCode();

        //Hashes the current nodes name for comparison
        int nptrInt = nptr.getName().hashCode();

        //Loops thorugh the tree and compares the name to each node
        //until it is found or it breaks the method
        while (nptrInt != inputInt) {

            nptr_parent = nptr;

            if (nptrInt > inputInt) {
                nptr = nptr.getLeft();
            } else if (nptrInt < inputInt) {
                nptr = nptr.getRight();
            }
            if (nptr == null) {
                System.out.println("key is not found.");
                break;
            }

            nptrInt = nptr.getName().hashCode();
        }


        //Once the nptr and nptr_parent is set to the right node
        //The method will remove it from the tree
        if (nptr != null) {

                //1st case: If the node has no children
                if (nptr.getLeft() == null && nptr.getRight() == null) {

                    //If it's the root then the root is set to null
                    if (nptr == root) {
                        root = null;
                    }

                    //This checks if it is the left or right child
                    if (nptr_parent.getRight() == nptr) {
                        nptr_parent.setRight(null);
                    } else if (nptr_parent.getLeft() == nptr) {
                        nptr_parent.setleft(null);
                    }
                }

                // 2nd Case: If the node has only one child
                if (nptr.getLeft() != null && nptr.getRight() == null || nptr.getLeft() == null && nptr.getRight() != null) {

                    //If the root only has one child and we want to delete the root
                    //then the child becomes the root
                    if (nptr == root) {
                        if (nptr.getLeft() != null) {
                            root = nptr.getLeft();
                        } else {
                            root = nptr.getRight();
                        }
                    }

                    //Two cases for if the node we want to delete is
                    //the left or right child
                    if (nptr_parent.getLeft() == nptr) {
                        if (nptr.getRight() != null) {
                            nptr_parent.setleft(nptr.getRight());
                            nptr.setRight(null);
                        } else {
                            nptr_parent.setleft(nptr.getLeft());
                            nptr.setleft(null);
                        }

                    } else if (nptr_parent.getRight() == nptr) {
                        if (nptr.getRight() != null) {
                            nptr_parent.setRight(nptr.getRight());
                            nptr.setRight(null);
                        } else {
                            nptr_parent.setRight(nptr.getLeft());
                            nptr.setleft(null);
                        }

                    }

                }
                //3rd Case: If the node has 2 children
                if (nptr.getLeft() != null && nptr.getRight() != null) {

                    //I added two objects to make it easier to move around the successor
                    TreeNode nptr_successor = successor(nptr);
                    TreeNode nptr_successor_parent = null;

                    //This is a check to see if the successor is the right child
                    //of nptr, because if it is then the opertaions are a little different
                    if (nptr.getRight() != nptr_successor) {
                        nptr_successor_parent = nptr.getRight();
                    }

                    if (nptr_successor_parent == null) {
                        if (nptr == root) {
                            root = nptr_successor;
                        }

                        if (nptr_parent.getLeft() == nptr) {
                            nptr_parent.setleft(nptr_successor);

                        } else if (nptr_parent.getRight() == nptr) {
                            nptr_parent.setRight(nptr_successor);
                        }


                        nptr_successor.setleft(nptr.getLeft());
                        nptr.setleft(null);
                        nptr.setRight(null);

                        //Otherwise if the successor is anything other than the right child
                        //the method will run the normal delete operations
                    } else {

                        //This finds the parent of the successor to make it easier to replace
                        //nptr with.
                        while (nptr_successor_parent.getLeft() != nptr_successor) {
                            nptr_successor_parent = nptr_successor_parent.getLeft();
                        }

                        if (nptr_successor.getRight() == null) {
                            nptr_successor_parent.setleft(null);
                        } else {
                            nptr_successor_parent.setleft(nptr_successor.getRight());
                        }

                        //These are just checks for if nptr is the root, left child, or right child
                        if (nptr == root) {
                            root = nptr_successor;
                        } else if (nptr_parent.getLeft() == nptr) {
                            nptr_parent.setleft(nptr_successor);
                        } else if (nptr_parent.getRight() == nptr) {
                            nptr_parent.setRight(nptr_successor);
                        }

                        nptr_successor.setleft(nptr.getLeft());
                        nptr_successor.setRight(nptr.getRight());

                        nptr.setleft(null);
                        nptr.setRight(null);

                    }


                }

        }

        //This send nptr back to display which node was just deleted
        return nptr;
    }

    /**This method just displays the tree by using the
     * recursion for the inorder traversal
     *
     * @param root the starting point for the traversal print
     */
    public void InTraverse(TreeNode root){
        if(root != null){
            InTraverse(root.getLeft());
            System.out.print("-->"+ root.getName() + " " + root.getPhoneNumber());
            InTraverse(root.getRight());
        }
    }

    /**Thie is the successor method which takes an input node and
     * finds it's successor, which is just finding the leftmost
     * leaf of x's right child.
     *
     * @param x The node that you want to find the successor of
     * @return returns the successor to be used to delete the node
     */
    public TreeNode successor(TreeNode x){
        TreeNode nptr = x.getRight();
        while(nptr.getLeft() != null){
            nptr = nptr.getLeft();
        }
        return nptr;
    }

    /**This method is used to write the data within the BST into
     * an output file using the BufferedWriter
     *
     * @param output The output file is passed in by the User
     */
    public void writeOutput(String output){
        try {

            //sets the buffered write to the output file
            writer = new FileWriter(output);
            bwriter = new BufferedWriter(writer);

            //This is a function that will recursively write all of the
            //information for each node to the output file
            nodeWrite(root);

            //This is just to flush and close the bwriter
            bwriter.flush();
            bwriter.close();

        } catch(IOException e){

        }

    }

    /**This is a recursive method that is called by the writeOutput method
     *to write the info of the given node into the output file then
     * recursively call the method for it's left and right child
     *
     * @param node the node that you want to write to the output file
     */
    public void nodeWrite(TreeNode node){
        if(node != null){
            try {
                //Writes each field of info to the output file
                bwriter.write(node.getName());
                bwriter.write(",");
                bwriter.write(node.getPhoneNumber());
                bwriter.write(",");
                bwriter.write(node.getAddress());
                bwriter.write("\n");

                //Recursive call for left and right child
                nodeWrite(node.getLeft());
                nodeWrite(node.getRight());
            } catch(IOException e){

            }
        }

    }
}
