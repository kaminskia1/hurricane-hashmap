//******************************************************************************************************************
//
// Wrapper class for bridging the driver component of this program to the hashmap, whether
// it be the open or closed one. To change the hashmap type, modify the tree and the constructor
// to the providing class.
//
//******************************************************************************************************************
package com.kaminskia.hurricane;

import java.io.File;
import java.util.Scanner;

public class Hurricane {

    /**
     * Tree object
     */
    private final HashTableOpen tree;

    /**
     * Constructor
     */
    public Hurricane() {
        tree = new HashTableOpen();
    }

    /**
     * Read input file
     */
    public void readFile() throws Exception {
        File file = new File("names.txt");
        Scanner reader, tokenizer;
        String inputLine, lastName, firstName;
        reader = new Scanner(file);
        while (reader.hasNextLine()) {
            inputLine = reader.nextLine();
            tokenizer = new Scanner(inputLine);
            lastName = tokenizer.next();
            firstName = tokenizer.next();
            Person p = new Person();
            p.setLastName(lastName);
            p.setFirstName(firstName);
            this.tree.add(p);
        }

    }

    /**
     * Search by last name
     *
     * @param lastName Last name
     * @return int
     */
    public LinkedList<Person> search(String lastName) {
        return tree.find(lastName);
    }

    /**
     * Insert wrapper
     *
     * @param p Person object
     */
    public void insert(Person p) {
        tree.add(p);
    }

    /**
     * Remove all wrapper
     *
     * @param lastName  Last name
     */
    public void removeAll(String lastName) {
        tree.remove(lastName);
    }

    /**
     * Remove single wrapper
     *
     * @param firstName First name
     * @param lastName  Last name
     */
    public void removeSingle(String firstName, String lastName) {
        tree.remove(new Person(firstName, lastName));
    }

    /**
     * Count wrapper
     *
     * @return int
     */
    public int count() {
        return tree.getSize();
    }

    /**
     * Print wrapper
     */
    public void print() {
        Ui.printLine("Printed:");
        tree.print();
    }

}