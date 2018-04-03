package src;
/**
 * Created by mohammedawan on 4/7/17.
 */
//This class contains all the methods to operate on a list from the hash table
public class LinkedList {

    //Declaring the head and tail of the list and the size of the list
    protected PersonNode head;
    protected PersonNode tail;
    public int size;

    //The default constructor for the linked list
    public LinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    /**This is the add function and it creates a new node and
     * adds the person to the front of the list
     *
     * @param name the name of the person to be added to the tree
     * @param number the phone number of the person
     * @param address the address of the person
     */
    public void add(String name, String number, String address){

        //Creates the new node for the person and increases the size of the list by 1
        PersonNode node = new PersonNode(name, number, address, null);
        size++;

        //Adds the new node to the front
        if(head == null){
            head = node;
            tail = node;
        } else {
            node.setNext(head);
            head = node;
        }
    }

    /**This is the retrieve function and it takes the name as an input
     * and goes from the front of the list to the end to see if the person exists
     *
     * @param name the name of the person you want to retrieve
     * @return the entire node of the person you wanted to retrieve
     */
    public PersonNode getPerson(String name){

        //Makes a nptr node to find the node
        PersonNode nptr;
        nptr = head;
        while(nptr != null){
            if(nptr.getName().equalsIgnoreCase(name)){
                return nptr;
            }
            nptr = nptr.getNext();
        }
        //If it isn't found then the method will return null
        return null;
    }

    /**This is the delete function and it takes in a name and the
     * method removes it from the list
     *
     * @param name The name of the person the User wants to delete
     * @return It returns the deleted node to print which person is deleted
     */
    public PersonNode remove(String name){

        //Make nptr and nptr_ahead to remove the node
        PersonNode nptr = head;
        PersonNode nptr_ahead;

        //Set both nptr and nptr_ahead at the head
        nptr_ahead = head;
        if(head != null){
          if(head.getNext() != null) {
              nptr = head.getNext();
          }
        }

        //Loops through list until the node is found or it returns a null
        while(nptr != null){

            //Checks if the node we want to delete is the head
            if(nptr_ahead.getName().equalsIgnoreCase(name)){
                nptr_ahead.setNext(null);
                head = nptr.getNext();
                size--;
                return nptr_ahead;
            } else if (nptr.getName().equalsIgnoreCase(name)){

                //Checks if the node is the tail
                if(tail == nptr) {
                    tail = nptr_ahead;
                    nptr_ahead.setNext(nptr.getNext());
                    nptr.setNext(null);
                    size--;
                    return nptr;
                } else {

                    //If the node is neither the head or the tail it will be removed
                    nptr_ahead.setNext(nptr.getNext());
                    nptr.setNext(null);
                    size--;
                    return nptr;
                }

            }

            //If the node isn't found yet and nptr isn't null
            //the method checks the next node
            nptr_ahead = nptr;
            if(nptr != null) {
                nptr = nptr.getNext();
            }
        }
        return null;
    }

    /**This method puts all of the nodes in a string and
     * sends it back to the method call to be printed
     *
     * @return returns the string of all the nodes in that list
     */
    public String printList(){

        //Make nptr to loop through the list
        PersonNode nptr = head;

        //create the string list to put all the names into if nptr isn't null
        String list = "";
        if (nptr == null){
            return "=> null";
        }
        while(nptr != null){
            list = list + "=> " + nptr.getName() + " (" + nptr.getAddress() + ") " + nptr.getPhoneNumber();
            nptr = nptr.getNext();
        }

        //returns the list string to the method caller
        return list;
    }


}
