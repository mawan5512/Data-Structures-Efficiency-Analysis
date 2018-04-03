package src;
import java.io.*;
/**
 * Created by mohammedawan on 4/7/17.
 */
//This class contains all of the operation methods for the Hash Table
public class HashTable {

    //Initializes a file and buffered writer to write to an output file
    FileWriter writer = null;
    BufferedWriter bwriter = null;

    //This boolean is to check whether or not the hash table has been created
    //so that it doesn't get overwritten if the startHash function is called again
    boolean start = false;

    //Sets the size of the hash table and creates it as an array of linked lists
    public int size = 25;
    LinkedList[] table = new LinkedList[size];

    /**This method is called in the main class and it creates
     * all of the linked lists for the hash table
     */
    public void startHash(){
        if(!start) {
            for (int i = 0; i <= size - 1; i++) {
                table[i] = new LinkedList();
            }
        }

        //Start is set to true so that the program won't create a new hash
        //table over the current one
        start = true;
    }


    /**This is the add function and it creates a new node and
     * adds the person to the hash table by using a hash code on
     * the name as a key and then adding it to the linked list
     *
     * @param name the name of the person to be added to the tree
     * @param number the phone number of the person
     * @param address the address of the person
     */
    public void insert(String name, String number, String address){

        //It takes the name and makes it lower case which will make seraching
        //easier and then it hashes that
        String lowerName = name.toLowerCase();
        Integer stringHash = lowerName.hashCode();

        //Sometimes the hash value is negative and that gives causes an issue
        //when trying to find which list to put it in
        if(stringHash < 0){
            stringHash = (-1 * stringHash);
        }

        //Another part of the hash function that finds which list to put
        //the node into
        Integer myHash = stringHash % size;

        //This adds the node to the list
        table[myHash].add(name.toLowerCase(), number, address);
    }

    /**This is the retrieve function and it takes the name as an input
     * and hashes it to find which list it's in and calls the getPerson
     * function in that specific list
     *
     * @param name the name of the person you want to retrieve
     * @return the entire node of the person you wanted to retrieve
     */
    public PersonNode retrieve(String name){

        //It takes the name and makes it lower case and then it hashes that
        String lowerName = name.toLowerCase();
        Integer stringHash = lowerName.hashCode();

        //Sometimes the hash value is negative and that gives causes an issue
        //when trying to find which list to put it in
        if(stringHash < 0){
            stringHash = (-1 * stringHash);
        }

        //Another part of the hash function that finds which list to look
        //in for the node
        Integer myHash = stringHash % size;

        //Get the node of the person and returns it to the
        //method caller
        return table[myHash].getPerson(name.toLowerCase());
    }

    /**This is the delete function and the User inputs the name
     * of a person that they want to delete and the method
     *  removes it from the hash table
     *
     * @param name The name of the person the User wants to delete
     * @return It returns the deleted node to print which person is deleted
     */
    public PersonNode delete(String name){

        //It takes the name and makes it lower case and then it hashes that
        String lowerName = name.toLowerCase();
        Integer stringHash = lowerName.hashCode();

        //Sometimes the hash value is negative and that gives causes an issue
        //when trying to find which list to put it in
        if(stringHash < 0){
            stringHash = (-1 * stringHash);
        }

        //Another part of the hash function that finds which list to look
        //in for the node
        Integer myHash = stringHash % size;

        //Calls the list method to delete the node and returns it
        //to the method called
        return table[myHash].remove(name.toLowerCase());
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
    public PersonNode update(String name, String newAddress, String newNumber){

        //This just calls the retrieve function with the name given
        PersonNode editPerson = retrieve(name);

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

    /**This method goes through the hash table and prints
     * out each list
     *
     */
    public void print(){

        //This loops each row of the hash table
        for(int i = 0; i <= size - 1; i++){

            //Declares currentList
            LinkedList currentList;

            if(table[i] != null) {

                //This sets the current List and gets all of the nodes in that list
                //from the .printList() method and print's that for each row
                currentList = table[i];
                System.out.println("table[" + i +"] " + currentList.printList());
            }

        }
    }


    /**This method is used to write the data within the Hash Table into
     * an output file using the BufferedWriter
     *
     * @param output The output file is passed in by the User
     */
    public void outputWrite(String output){
        try {

            //Gives the output file to the BufferedWrite and File Writer
            writer = new FileWriter(output);
            bwriter = new BufferedWriter(writer);

            //This loops through each of the rows in the hash table
            for (int i = 0; i <= size - 1; i++) {

                //Starts nptr at the head and writes it to the output file until
                //nptr is null
                PersonNode nptr = table[i].head;
                while (nptr != null) {
                    while (nptr != null) {
                        String name = nptr.getName();
                        String number = nptr.getPhoneNumber();
                        String address = nptr.getAddress();
                        bwriter.write(name);
                        bwriter.write(",");
                        bwriter.write(number);
                        bwriter.write(",");
                        bwriter.write(address);
                        bwriter.write("\n");
                        nptr = nptr.getNext();
                    }
                }
            }

            //Flushes and closes bwriter
            bwriter.flush();
            bwriter.close();
        } catch (IOException e) {

        }


    }

}
