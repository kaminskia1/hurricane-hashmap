//******************************************************************************************************************
//
// Class to implement a basic hashmap with open-hashing capabilities
// It constructs an array of linked lists, which contain linked lists of which hold names
// List[] => Open hashing list => List of family members by last name
//
//******************************************************************************************************************
package com.kaminskia.hurricane;

import java.util.LinkedList;

public class HashTableOpen {

    /**
     * Table size in bits
     */
    private final int TABLE_SIZE = 1 << 16;

    /**
     * Hash constant
     */
    private final double HASH_CONST = 0.0680;

    /**
     * Hash Table
     */
    private final LinkedList<LinkedList<Person>>[] table;

    /**
     * Total number of registered survivors
     */
    private int size;

    /**
     * Constructor
     */
    public HashTableOpen() {
        // Initialize the table
        table = new LinkedList[TABLE_SIZE];
        // Initialize the size
        size = 0;
    }


    /**
     * Add a value wrapper
     *
     * @param value Object to add
     */
    public void add(Person value)
    {
        // Grab the index for the provided last name
        int index = hash(value.getLastName());
        // Check if preexisting list exists
        if (table[index] == null) {
            // Initialize table[index] to fresh list
            table[index] = new LinkedList<>();
            // Add a list to that list
            table[index].add(new LinkedList<Person>());
            // Add a person to the list's list
            table[index].getFirst().add(value);
        }
        else
        {
            // Flag to check if added or not
            boolean added = false;
            // Cycle through all instances and check if it's the last name's group
            for (int i=0;i<table[index].size();i++)
            {
                if (table[index].get(i).getFirst().getLastName().equals(value.getLastName()) && !added)
                {
                    // If so, add person and flag as added
                    table[index].get(i).add(value);
                    added = true;
                }
            }
            // If not added
            if (!added)
            {
                // Create a fresh list and add it to the list's list, and append the person to the new list
                LinkedList<Person> list = new LinkedList<Person>();
                list.add(value);
                // Add to table
                table[index].add(list);
            }
        }
        // Increment size as person added
        size += 1;
    }

    /**
     * Remove all of a certain last name
     *
     * @param lastName Object to remove
     */
    public void remove(String lastName) {
        // Grab the index for the corresponding last name
        int index = hash(lastName);

        // Iterate through all subindicies
        for (int i=0;i<table[index].size();i++)
        {
            // If equivalent found, remove and decrement by lastname size
            if (table[index].get(i).getFirst().getLastName().equals(lastName))
            {
                // Decrement size
                size -= table[index].get(i).size();
                // Remove element
                table[index].remove(i);
            }
        }
    }

    /**
     * Remove a single person
     *
     * @param person Object to remove
     */
    public void remove(Person person) {
        // Grab the index for the corresponding last name
        int index = hash(person.getLastName());
        // Ensure that list exists at index
        for (int i=0;i<table[index].size();i++)
        {
            // If last name of tree is same last name
            if (table[index].get(i).getFirst().getLastName().equals(person.getLastName()))
            {
                // Iterate through all people in sublist
                for (int ii=0;ii<table[index].get(i).size();ii++)
                {
                    // If person found
                    if (table[index].get(i).get(ii).getLastName().equals(person.getLastName()))
                    {
                        // Remove person
                        table[index].get(i).remove(ii);
                        size -= 1;
                        // Optimization, prevents further unneeded execution
                        break;
                    }
                }
            }
        }
    }


    /**
     * Find / count wrapper
     *
     * @param lastName Value to search
     * @return int
     */
    public LinkedList<Person> find(String lastName) {
        // Grab the corresponding list for said last name
        int index = hash(lastName);
        // If no list exists return null
        if (table[index] == null) return null;
        // Iterate through all sublists at the grabbed index
        for (int i=0;i<table[index].size();i++)
        {
            // If sublist is equal to the provided last name
            if (table[index].get(i).getFirst().getLastName().equals(lastName))
            {
                // Return said
                return table[index].get(i);
            }
        }
        return null;
    }

    /**
     * Print the hashmap
     */
    public void print() {
        // Iterate through all indices in the array
        for (int i = 0; i < TABLE_SIZE; i++) {
            // Check if a list exists for i
            if (table[i] != null) {
                // Iterate through all elements in the list
                for (int ii = 0; ii < table[i].size(); ii++) {

                    // Iterate through every person
                    for (int iii=0; iii<table[i].get(ii).size();iii++)
                    {
                        Ui.printLine(table[i].get(ii).get(iii).getLastName() + ", " + table[i].get(ii).get(iii).getFirstName());
                    }
                }
            }
        }
    }

    /**
     * Get the total size
     *
     * @return int
     */
    public int getSize() {
        // Return the size
        return size;
    }

    /**
     * Convert a string into a hash
     *
     * @param k String to hash
     * @return int
     */
    private int hash(String k) {
        return (int)Math.abs((k.hashCode() * HASH_CONST) % TABLE_SIZE);
    }

}
