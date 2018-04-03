package src;
/**
 * Created by mohammedawan on 4/8/17.
 */
//This class is used to store and retrieve the node information for the Binary Tree
public class TreeNode {

    //These are each of the fields in the TreeNode

    private String name;
    private String phoneNumber;
    private String address;
    private TreeNode left;
    private TreeNode right;

    //This is the default constructor for a TreeNode
    public TreeNode(){
        name = "";
        phoneNumber = "";
        address = "";
        left = null;
        right = null;
    }

    //This is the overloaded constructor for the TreeNode
    public TreeNode(String name, String number, String address){
        this.name = name;
        this.phoneNumber = number;
        this.address = address;
        this.left = null;
        this.right = null;
    }

    //These are all of the getter and setter method for each field in the TreeNode

    public String getName(){

        return this.name;
    }

    public String getPhoneNumber(){

        return this.phoneNumber;
    }

    public String getAddress(){

        return this.address;
    }

    public void setAddress(String address){

        this.address = address;
    }

    public void setPhoneNumber(String number) {
        this.phoneNumber = number;
    }

    public TreeNode getLeft(){

        return this.left;
    }

    public void setleft(TreeNode left){

        this.left = left;
    }

    public TreeNode getRight(){
        return this.right;
    }

    public void setRight(TreeNode right){
        this.right = right;
    }
}
