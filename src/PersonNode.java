package src;
/**
 * Created by mohammedawan on 4/7/17.
 */

//This class is used to store and retrieve for the PersonNode
public class PersonNode {

    //These are each of the fields in the PersonNode

    private String name;
    private String phoneNumber;
    private String address;
    private PersonNode next;

    //This is the default constructor for a PersonNode
    public PersonNode(){
        name = "";
        phoneNumber = "";
        address = "";
        next = null;
    }

    //This is the overloaded constructor for the PersonNode
    public PersonNode(String name, String number, String address, PersonNode next){
        this.name = name;
        this.phoneNumber = number;
        this.address = address;
        this.next = next;
    }


    //These are all of the getter and setter methods for each field in the PersonNode

    public String getName(){
        return this.name;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public String getAddress(){
        return this.address;
    }

    public PersonNode getNext(){
        return this.next;
    }

    public void setNext(PersonNode next){
        this.next = next;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setPhoneNumber(String number){
        this.phoneNumber = number;
    }


}
