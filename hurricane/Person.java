//******************************************************************************************************************
//
// Object class to contain all data for a person object with basic getters/setters. Contains
// space for storing both a first and last name, alongside comparison to other instances of
// this class
//
//******************************************************************************************************************
package com.kaminskia.hurricane;

public class Person {

    /**
     * Person's first name
     */
    private String firstName;

    /**
     * Person's last name
     */
    private String lastName;

    /**
     * Constructor
     */
    public Person() {
        this.firstName = null;
        this.lastName = null;
    }

    /**
     * Constructor
     *
     * @param firstName String
     * @param lastName  String
     */
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Convert the object to a readable string
     *
     * @return String
     */
    public String toString() {
        return lastName + ", " + firstName + ": [" + getUniqueId() + "]";
    }

    /**
     * Get the object's hash code
     *
     * @return int
     */
    public int getUniqueId() {
        return hashCode();
    }

    /**
     * Grab the user's identifiable value, used in comparison
     *
     * @return String
     */
    public String getValue() {
        return lastName;
    }

    /**
     * Grab the user's first name
     *
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the user's first name
     *
     * @param name String
     */
    public void setFirstName(String name) {
        firstName = name;
    }

    /**
     * Grab the user's last name
     *
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the user's last name
     *
     * @param name String
     */
    public void setLastName(String name) {
        lastName = name;
    }

    /**
     * Compare the object and determine if it is weighted less than the provided
     *
     * @param node Node to compare
     * @return boolean
     */
    public boolean lessThan(Person node) {
        return this.getLastName().compareTo(node.getValue()) < 0;
    }

    /**
     * Compare the object and determine if it is equally weighted to the provided
     *
     * @param node Node to compare
     * @return boolean
     */
    public boolean equalTo(Person node) {
        return this.getValue().equals(node.getValue());
    }

}
