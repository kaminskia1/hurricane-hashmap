//******************************************************************************************************************
//
// Class to implement a basic hashmap with closed-hashing / quadratic probing capabilities
// It constructs an array of linked lists, which contain family members for all of one said last name
// List[] => List of family members by last name
//
//******************************************************************************************************************
package com.kaminskia.hurricane;

import java.util.LinkedList;

public class HashTableQuad {

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
    private final LinkedList<Person>[] table;

    /**
     * Total number of registered survivors
     */
    private int size;

    /**
     * Constructor
     */
    public HashTableQuad() {
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
    public void add(Person value) {
        // Grab the index for the provided last name
        int index = collisionCheck(hash(value.getLastName()), value);
        // Check if preexisting list exists
        if (table[index] == null) {
            // If not, create
            table[index] = new LinkedList<Person>();
        }
        // Increment size as person added
        size += 1;
        // Add person to the list
        table[index].add(value);
    }

    /**
     * Remove all of a certain last name
     *
     * @param lastName Object to remove
     */
    public void remove(String lastName) {
        // Grab the index for the corresponding last name
        int index = collisionCheck(hash(lastName), lastName);
        if (table[index] != null)
        {
            // Decrement size by the size of the list
            size -= table[index].size();
            // Nullify the list at the corresponding index
            table[index] = null;
        }

    }

    /**
     * Remove a single person
     *
     * @param person Object to remove
     */
    public void remove(Person person) {
        // Grab the index for the corresponding last name
        int index = collisionCheck(hash(person.getLastName()), person);
        // Ensure that list exists at index
        if (table[index] != null) {
            // Cycle through all elements of the list
            for (int i = 0; i < table[index].size(); i++) {
                // Grab the person stored at the current position
                Person stored = table[index].get(i);
                // Check if both first and last name match up
                if (stored.getFirstName().equals(person.getFirstName())
                        && (stored.getLastName().equals(person.getLastName()))) {
                    // If found, remove said person, decrement size, and break
                    table[index].remove(i);
                    size -= 1;
                    break;
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
        return table[collisionCheck(hash(lastName), lastName)];
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
                    // Print out the first and last name for each person
                    Ui.printLine(table[i].get(ii).getLastName() + ", " + table[i].get(ii).getFirstName());
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

    /**
     * Collision check wrapper
     *
     * @param index Previous table index
     * @param p     Person to use as correspondence
     * @return int New (or same) index suitable for the provided person
     */
    private int collisionCheck(int index, Person p) {
        return collisionCheck(index, p.getLastName());
    }

    /**
     * Run a collision check and return a 'fixed' index in the table
     *
     * @param index    Previous table index
     * @param lastName Last name to use as correspondence
     * @return int New (or same) index suitable for the provided last name
     */
    private int collisionCheck(int index, String lastName) {
        // Loop through indexes, starting at the current, until a suitable one is encountered
        for (; table[index] != null; index = (index * index + 1) % TABLE_SIZE) {
            // check if person lastname is equal to list's current group. If so, break
            if (table[index].getFirst().getLastName().equals(lastName)) {
                break;
            }
        }
        // Suitable index encountered, return such
        return index;
    }

}
