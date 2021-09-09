//******************************************************************************************************************
//
// Main entrypoint for this project. This project contains the base "driver" for all
// operations in this class. All calls should route through main.
//
//******************************************************************************************************************
package com.kaminskia.hurricane;

import java.util.Scanner;

public class Main {

    /**
     * GUI Choice Cases
     */
    public static final int CASE_SEARCH = 1;
    public static final int CASE_INSERT = 2;
    public static final int CASE_REM_ALL = 3;
    public static final int CASE_REM_SINGLE = 4;
    public static final int CASE_COUNT = 5;
    public static final int CASE_PRINT = 6;
    public static final int CASE_LOAD = 7;
    public static final int CASE_EXIT = 8;


    /**
     * Default Entrypoint
     *
     * @param args Default arguments
     */
    public static void main(String[] args) {

        // Initialize default variables and create universal scanner / tree implementation
        int choice = 0;
        boolean loop = true;
        Hurricane h = new Hurricane();
        Scanner s = new Scanner(System.in);

        // Repeat until exit condition met
        while (loop) {

            // Prompt user for choice and execute based on said, catch any errors
            // and repeat until proper input recieved
            boolean loop2 = true;
            while (loop2) {
                try {
                    choice = Ui.promptChoice();
                    loop2 = false;
                } catch (Exception ignored) {
                }
            }

            // call switch statement on user-provided choice
            switch (choice) {

                // Search
                case CASE_SEARCH:
                    // Prompt user for last name and search based on said
                    LinkedList<Person> result = h.search(Ui.promptLastName(s));

                    // Display output
                    if (result != null) {
                        Ui.printLine("Total Found: " + result.size());
                        for (int i = 0; i < result.size(); i++) {
                            Ui.printLine(i + ". " + result.get(i).getLastName() + ", " + result.get(i).getFirstName());
                        }
                    } else {
                        Ui.printLine("Total Found: 0");
                    }
                    break;

                // Insert
                case CASE_INSERT:
                    // Insert new user to table based on user input
                    h.insert(new Person(Ui.promptFirstName(s), Ui.promptLastName(s)));
                    break;

                // Remove all of a name
                case CASE_REM_ALL:
                    // Remove user from table based on user input
                    h.removeAll(Ui.promptLastName(s));
                    break;

                // Remove a single person
                case CASE_REM_SINGLE:
                    // Remove user from table based on user input
                    h.removeSingle(Ui.promptFirstName(s), Ui.promptLastName(s));
                    break;

                // Count
                case CASE_COUNT:
                    // Call count and output the result
                    Ui.printLine("Total count: " + h.count());
                    break;

                // Print
                case CASE_PRINT:
                    // Print the list out in inorder notation
                    h.print();
                    break;

                // Load file
                case CASE_LOAD:
                    try {
                        h.readFile();
                    } catch (Exception e) {
                        Ui.print("There was an error loading this file! (does it exist?)");
                    }
                    break;

                // Exit
                case CASE_EXIT:
                    // Exit the universal loop
                    loop = false;
                    break;
            }
            Ui.pauseTillInput();
        }
    }
}
