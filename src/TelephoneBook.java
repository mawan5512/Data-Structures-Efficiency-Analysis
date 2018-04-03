package src;
import java.util.*;
import java.io.*;
/**
 * Created by mohammedawan on 4/8/17.
 */
//This class had the main method and acts as a main menu for the
//for the user to go through
public class TelephoneBook {

    public static void main(String[] args) throws IOException{

        //Here the hash table and BST are created
        HashTable book = new HashTable();
        BinaryTree treeList = new BinaryTree();

        //Initialized a scanner for User Input
        Scanner scan = new Scanner(System.in);

        //The end boolean is what keeps the main menu looping
        //until the User exits
        boolean end = false;

        //These check for which type of data structure is chosen
        boolean hash = false;
        boolean tree = false;

        //These are for the input and output file
        //To check whether or not one was entered
        boolean inputHere = false;
        boolean inputRead = false;
        boolean outputHere = false;

        //Theses are the string used to store the file path
        String inputFile = "";
        String outputFile = "";

        //Here the readers and writers are initializes
        FileReader fw = null;
        BufferedReader br = null;
        FileWriter writer = null;
        BufferedWriter bwriter = null;

        //This is a loop for the input file
        while(!inputHere) {
            System.out.println("Type in the filepath for the input file (Type in NA if you don't have an input file):");
            inputFile = scan.next();

            //If the User doesn't want to put in a file
            if(inputFile.equalsIgnoreCase("NA")){

                //An input was given, but nothing that can be read
                //so inputHere is true, but inputRead is still false
                inputHere = true;
            } else {
                try {

                    //Sets the input file to the readers
                    fw = new FileReader(inputFile);
                    br = new BufferedReader(fw);
                    System.out.println("File Found");

                    //Since there is a file, inputHere and inputRead are both true
                    inputHere = true;
                    inputRead = true;
                } catch (FileNotFoundException e) {
                    System.out.println("No existing file path has been entered");
                }
            }
        }


        //This loop goes until the User chooses a data structure
        while(!hash && !tree){
            System.out.println("Choose a data structure");
            System.out.println("1.Hash Table");
            System.out.println("2.Binary Search Tree");
            int input = scan.nextInt();
            if(input == 1){
                hash = true;
            } else if (input == 2) {
                tree = true;
            } else {
                System.out.println("Choose 1 or 2 stupid.");
            }
        }

        //This is the menu loop for if the User chose a hash table
        while (!end && hash){

            //This creates the linked lists for the hash table
            book.startHash();

            //if a file was given
            if(inputRead) {

                //Loops until end of file
                String read = br.readLine();
                while (read != null) {
                    String[] fileColumns = read.split(",");
                    String nameFile = fileColumns[0];
                    String numberFile = fileColumns[1];
                    String addressFile = fileColumns[2];

                    //inserts person info into hash table
                    book.insert(nameFile, numberFile, addressFile);
                    read = br.readLine();
                }

                //Sets input read to false so that every time the
                //menu loops it doesn't read the same file again
                inputRead = false;
            }

            //Print statements for the main menu
            System.out.println("");
            System.out.println("MAIN MENU");
            System.out.println("1.Insert telephone info");
            System.out.println("2.Retrieve telephone info");
            System.out.println("3.Delete telephone info");
            System.out.println("4.Display whole phone book");
            System.out.println("5.Update info");
            System.out.println("6.If you want to do testing");
            System.out.println("7.End the program");

            //Sets the input to zero and then checks if it was a number
            int input = 0;
            try {
                input = scan.nextInt();
            }catch(InputMismatchException e){

            }

            //Switch statements for each option that the User chose
            switch (input) {
                case 1:
                    System.out.println("Type in the person's name (No spaces)");
                    String name = scan.next();
                    System.out.println("Type in the person's phone number (No spaces)");
                    String number = scan.next();
                    System.out.println("Type in the person's address (No spaces)");
                    String address = scan.next();

                    //The inputs are all inserted in to the hash table
                    book.insert(name, number, address);

                    break;
                case 2:
                    System.out.println("Type in a person's name (No spaces)");

                    //Gets the name to retrieve from User
                    String nameRetrieve = scan.next();
                    PersonNode person = book.retrieve(nameRetrieve);

                    //If the retrieve methods returns a null value then it says no person found
                    //otherwise it prints their info
                    if(person != null) {
                        System.out.println(person.getName() + "'s info is: ");
                        System.out.println("Number: " + person.getPhoneNumber());
                        System.out.println("Address: " + person.getAddress());
                    } else {
                        System.out.println("Name not found.");
                    }

                    break;
                case 3:
                    System.out.println("Type in a person's name to delete (No spaces)");

                    //Gets name to delete from User
                    String nameDelete = scan.next();
                    PersonNode deletedNode = book.delete(nameDelete);

                    //If the delete methods returns a null value then it says no person found
                    //otherwise it prints their info
                    if(deletedNode != null) {
                        System.out.println("Deleting: " + deletedNode.getName() + " (" + deletedNode.getAddress() + ") " + deletedNode.getPhoneNumber());
                    } else {
                        System.out.println("Person not found");
                    }
                    break;
                case 4:
                    System.out.println("Displaying");

                    //Displays the entire phone book
                    book.print();
                    break;
                case 5:
                    System.out.println("Type in the name you want to update (No spaces)");
                    name = scan.next();
                    System.out.println("Type in the new address (or NA if you don't want to change the address) (No spaces)");
                    String newaddress = scan.next();
                    System.out.println("Type in the new number (or NA if you don't want to change the number) (No spaces)");
                    String newnumber = scan.next();
                    PersonNode editNode = book.retrieve(name);

                    //If the update methods returns a null value then it says no person found
                    //otherwise it prints their old info and then their new info
                    if(editNode == null){
                        System.out.println("Person not found");
                    } else {
                        System.out.println("Changing " + editNode.getName() + "'s info");
                        System.out.println(editNode.getAddress());
                        System.out.println(editNode.getPhoneNumber());
                        book.update(name, newaddress, newnumber);
                        editNode = book.retrieve(name);
                        System.out.println(editNode.getName() + "'s new info is: ");
                        System.out.println(editNode.getAddress());
                        System.out.println(editNode.getPhoneNumber());
                    }
                    break;

                //This is specifically meant to be for testing the time performance
                //but I left it in here in case anyone wants to use it
                case 6:

                    //Here it takes an input file and adds the people's
                    //information
                    System.out.println("Enter a filename to input data");
                    inputFile = scan.next();
                    try {
                        fw = new FileReader(inputFile);
                        br = new BufferedReader(fw);
                    } catch(FileNotFoundException e){
                        System.out.println("File Not Found");
                    }
                    long totalTime = 0;
                    String read = br.readLine();
                    while(read != null){
                        String[] fileColumns = read.split(",");
                        String nameFile = fileColumns[0];
                        String numberFile = fileColumns[1];
                        String addressFile = fileColumns[2];
                        long startTime = System.nanoTime();
                        book.insert(nameFile, numberFile, addressFile);
                        long endTime = System.nanoTime();
                        read = br.readLine();
                        totalTime = totalTime + (endTime - startTime);

                    }

                    //It then print's out the total time that it took to do so
                    System.out.println("It took this long to input the data: ");
                    System.out.println(totalTime);

                    //Here it takes an input file and retrieves the people's
                    //information
                    System.out.println("Enter a filename to retrieve data");
                    inputFile = scan.next();
                    try {
                        fw = new FileReader(inputFile);
                        br = new BufferedReader(fw);
                    } catch(FileNotFoundException e){
                        System.out.println("File Not Found");
                    }
                    totalTime = 0;
                    read = br.readLine();
                    while(read != null){
                        long startTime = System.nanoTime();
                        book.retrieve(read);
                        long endTime = System.nanoTime();
                        read = br.readLine();
                        totalTime = totalTime + (endTime - startTime);
                    }

                    //It then print's out the total time that it took to do so
                    System.out.println("It took this long to retrieve the data: ");
                    System.out.println(totalTime);

                    //Here it takes an input file and updates the people's
                    //information
                    System.out.println("Enter a filename to update data");
                    inputFile = scan.next();
                    try {
                        fw = new FileReader(inputFile);
                        br = new BufferedReader(fw);
                    } catch(FileNotFoundException e){
                        System.out.println("File Not Found");
                    }
                    totalTime = 0;
                    read = br.readLine();
                    while(read != null){
                        String[] fileColumns = read.split(",");
                        String nameFile = fileColumns[0];
                        String numberFile = fileColumns[1];
                        String addressFile = fileColumns[2];
                        long startTime = System.nanoTime();
                        book.update(nameFile, numberFile, addressFile);
                        long endTime = System.nanoTime();
                        read = br.readLine();
                        totalTime = totalTime + (endTime - startTime);

                    }

                    //It then print's out the total time that it took to do so
                    System.out.println("It took this long to update the data: ");
                    System.out.println(totalTime);

                    //Here it takes an input file and deletes the people's
                    //information
                    System.out.println("Enter a filename to delete data");
                    inputFile = scan.next();
                    try {
                        fw = new FileReader(inputFile);
                        br = new BufferedReader(fw);
                    } catch(FileNotFoundException e){
                        System.out.println("File Not Found");
                    }
                    totalTime = 0;
                    read = br.readLine();
                    while(read != null){
                        long startTime = System.nanoTime();
                        book.delete(read);
                        long endTime = System.nanoTime();
                        read = br.readLine();
                        totalTime = totalTime + (endTime - startTime);
                    }

                    //It then print's out the total time that it took to do so
                    System.out.println("It took this long to delete the data: ");
                    System.out.println(totalTime);
                    break;
                case 7:

                    //This set's end to true and breaks out of the main menu loop
                    System.out.println("Good-Bye");
                    end = true;
                    break;
                default:

                    //If the User put in the wrong input the their prompted to put
                    //the right input
                    System.out.println("Oye Stupid Type in a number 1-7");
                    break;
            }
        }

        //This is the menu loop for the BST
        while(!end && tree){

            //if a file was given
            if(inputRead) {

                //Loops until end of file
                String read = br.readLine();
                while (read != null) {
                    String[] fileColumns = read.split(",");
                    String nameFile = fileColumns[0];
                    String numberFile = fileColumns[1];
                    String addressFile = fileColumns[2];

                    //Takes person info from the input file and adds it to the tree
                    treeList.add(nameFile, numberFile, addressFile);
                    read = br.readLine();
                }

                //Sets input read to false so that every time the
                //menu loops it doesn't read the same file again
                inputRead = false;
            }

            //Main menu loop for the BST
            System.out.println("");
            System.out.println("MAIN MENU");
            System.out.println("1.Insert telephone info");
            System.out.println("2.Retrieve telephone info");
            System.out.println("3.Delete telephone info");
            System.out.println("4.Display whole phone book");
            System.out.println("5.Update information");
            System.out.println("6.If you want to do testing");
            System.out.println("7.End the program");

            //Sets the input to zero and then checks if it was a number
            int input = 0;
            try {
                input = scan.nextInt();
            }catch(InputMismatchException e){

            }

            //Switch statements for each option that the User chose
            switch (input) {
                case 1:
                    System.out.println("Type in the person's name (No spaces)");
                    String name = scan.next();
                    System.out.println("Type in the person's phone number (No spaces)");
                    String number = scan.next();
                    System.out.println("Type in the person's address (No spaces)");
                    String address = scan.next();

                    //Takes the User's input and adds it to the tree
                    treeList.add(name, number, address);

                    break;
                case 2:
                    System.out.println("Type in a person's name (No spaces)");

                    //Gets the name to retrieve from User
                    String nameRetrieve = scan.next();
                    TreeNode person = treeList.retrieve(nameRetrieve);

                    //If the delete methods returns a null value then it says no person found
                    //otherwise it prints their info
                    if (person != null) {
                        System.out.println(person.getName() + "'s info is: ");
                        System.out.println("Number: " + person.getPhoneNumber());
                        System.out.println("Address: " + person.getAddress());
                    } else {
                        System.out.println("Person not found");
                    }

                    break;
                case 3:
                    System.out.println("Type in a person's name to delete (No spaces)");

                    //Gets the name to delete from User
                    String numberDelete = scan.next();
                    TreeNode deletedNode = treeList.delete(numberDelete);

                    //If the retrieve methods returns a null value then it says no person found
                    //otherwise it prints their info
                    if(deletedNode != null) {
                        System.out.println("Deleting: " + deletedNode.getName() + " (" + deletedNode.getAddress() + ") " + deletedNode.getPhoneNumber());
                    }
                    break;
                case 4:

                    //Displays the whole phonebook
                    System.out.println("Displaying");
                    treeList.InTraverse(treeList.root);
                    break;
                case 5:
                    System.out.println("Type in the name you want to update (No spaces)");
                    name = scan.next();
                    System.out.println("Type in the new address (or NA if you don't want to change the address) (No spaces)");
                    String newaddress = scan.next();
                    System.out.println("Type in the new number (or NA if you don't want to change the number) (No spaces)");

                    //If the retrieve methods returns a null value then it says no person found
                    //otherwise it prints their info
                    String newnumber = scan.next();
                    TreeNode editNode = treeList.retrieve(name);
                    if(editNode == null){
                        System.out.println("Person not found");
                    } else {

                        //If the update methods returns a null value then it says no person found
                        //otherwise it prints their old info and then their new info
                        System.out.println("Changing " + editNode.getName() + "'s info");
                        System.out.println(editNode.getAddress());
                        System.out.println(editNode.getPhoneNumber());
                        treeList.update(name, newaddress, newnumber);
                        editNode = treeList.retrieve(name);
                        System.out.println(editNode.getName() + "'s new info is: ");
                        System.out.println(editNode.getAddress());
                        System.out.println(editNode.getPhoneNumber());
                    }
                    break;
                case 6:

                    //Here it takes an input file and adds the people's
                    //information
                    System.out.println("Enter a filename to input data");
                    inputFile = scan.next();
                    try {
                        fw = new FileReader(inputFile);
                        br = new BufferedReader(fw);
                    } catch(FileNotFoundException e){
                        System.out.println("File Not Found");
                    }
                    long totalTime = 0;
                    String read = br.readLine();
                    while(read != null){
                        String[] fileColumns = read.split(",");
                        String nameFile = fileColumns[0];
                        String numberFile = fileColumns[1];
                        String addressFile = fileColumns[2];
                        long startTime = System.nanoTime();
                        treeList.add(nameFile, numberFile, addressFile);
                        long endTime = System.nanoTime();
                        read = br.readLine();

                        totalTime = totalTime + (endTime - startTime);
                    }

                    //It then print's out the total time that it took to do so
                    System.out.println("It took this long to input the data: ");
                    System.out.println(totalTime);

                    //Here it takes an input file and retrieves the people's
                    //information
                    System.out.println("Enter a filename to retrieve data");
                    inputFile = scan.next();
                    try {
                        fw = new FileReader(inputFile);
                        br = new BufferedReader(fw);
                    } catch(FileNotFoundException e){
                        System.out.println("File Not Found");
                    }
                    totalTime = 0;
                    read = br.readLine();
                    while(read != null){
                        long startTime = System.nanoTime();
                        treeList.retrieve(read);
                        long endTime = System.nanoTime();
                        read = br.readLine();
                        totalTime = totalTime + (endTime - startTime);
                    }

                    //It then print's out the total time that it took to do so
                    System.out.println("It took this long to retrieve the data: ");
                    System.out.println(totalTime);

                    //Here it takes an input file and updates the people's
                    //information
                    System.out.println("Enter a filename to update data");
                    inputFile = scan.next();
                    try {
                        fw = new FileReader(inputFile);
                        br = new BufferedReader(fw);
                    } catch(FileNotFoundException e){
                        System.out.println("File Not Found");
                    }
                    totalTime = 0;
                    read = br.readLine();
                    while(read != null){
                        String[] fileColumns = read.split(",");
                        String nameFile = fileColumns[0];
                        String numberFile = fileColumns[1];
                        String addressFile = fileColumns[2];
                        long startTime = System.nanoTime();
                        treeList.update(nameFile, addressFile, numberFile);
                        long endTime = System.nanoTime();
                        read = br.readLine();

                        totalTime = totalTime + (endTime - startTime);
                    }

                    //It then print's out the total time that it took to do so
                    System.out.println("It took this long to update the data: ");
                    System.out.println(totalTime);

                    //Here it takes an input file and adds the people's
                    //information
                    System.out.println("Enter a filename to delete data");
                    inputFile = scan.next();
                    try {
                        fw = new FileReader(inputFile);
                        br = new BufferedReader(fw);
                    } catch(FileNotFoundException e){
                        System.out.println("File Not Found");
                    }
                    totalTime = 0;
                    read = br.readLine();
                    while(read != null){
                        long startTime = System.nanoTime();
                        treeList.delete(read);
                        long endTime = System.nanoTime();
                        read = br.readLine();
                        totalTime = totalTime + (endTime - startTime);
                    }

                    //It then print's out the total time that it took to do so
                    System.out.println("It took this long to delete the data: ");
                    System.out.println(totalTime);
                    break;
                case 7:

                    //This set's end to true and breaks out of the main menu loop
                    System.out.println("Good-Bye");
                    end = true;
                    break;
                default:

                    //If the User put in the wrong input the their prompted to put
                    //the right input
                    System.out.println("Oye Stupid Type in a number 1-7");
                    break;
            }

        }

        //This is the loop to get the output file to write to
        while(!outputHere) {
            System.out.println("Type in the filepath for the output file (Type in NA if you don't have an output file):");
            outputFile = scan.next();

            //The User can type na if they don't want to output
            if(outputFile.equalsIgnoreCase("NA")){
                outputHere = true;
            } else {
                try {
                    writer = new FileWriter(outputFile);
                    bwriter = new BufferedWriter(writer);
                    System.out.println("File Found");
                    outputHere = true;
                } catch (FileNotFoundException e) {
                    System.out.println("No existing file path has been entered");
                }
            }
        }

        //If there is an output file then it will write to it
        if(bwriter != null){

            //If the User used the BST data structure
            if (tree == true){
                treeList.writeOutput(outputFile);
            }

            //If the User used the hash table data structure
            else if (hash == true){
                book.outputWrite(outputFile);
            }
        }

    }

}
