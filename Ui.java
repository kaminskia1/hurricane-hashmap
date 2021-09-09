//******************************************************************************************************************
//
// UI class, every class requesting read/write access to the terminal has their calls routed
// through functions in this class. This serves as a layer of abstract for easy restyling /
// updating of future UI design
//
//******************************************************************************************************************
package com.kaminskia.hurricane;

import java.util.Scanner;

public class Ui {

    /**
     * Display the menu
     */
    public static void menu() {
        System.out.println("\nHurricane Maria Disaster Relief Assistance\n");
        System.out.println("[" + Main.CASE_SEARCH + "] Search");
        System.out.println("[" + Main.CASE_INSERT + "] Insert");
        System.out.println("[" + Main.CASE_REM_ALL + "] Remove Family");
        System.out.println("[" + Main.CASE_REM_SINGLE + "] Remove Single");
        System.out.println("[" + Main.CASE_COUNT + "] Count");
        System.out.println("[" + Main.CASE_PRINT + "] Print");
        System.out.println("[" + Main.CASE_LOAD + "] Load File");
        System.out.println("[" + Main.CASE_EXIT + "] Exit");
        System.out.print("> ");
    }

    /**
     * Prompt the user for a choice, and return the provided
     *
     * @return int
     */
    public static int promptChoice() {
        menu();
        int c = -1;
        boolean choice = true;

        while (choice) {
            // get choice
            Scanner x = new Scanner(System.in);
            c = x.nextInt();

            // validate choice
            if (Main.CASE_LOWEST_NUM < c && c < Main.CASE_HIGHEST_NUM) {
                choice = false;
            } else {
                // display if invalid number, and repeat
                System.out.println("Invalid Choice!");
                System.out.print("> ");
            }
        }

        return c;
    }

    /**
     * Prompt the user for their first name
     *
     * @param s first name
     * @return String
     */
    public static String promptFirstName(Scanner s) {
        System.out.println("Enter a first name");
        System.out.print("> ");
        return s.nextLine();
    }

    /**
     * Prompt the user for their last name
     *
     * @param s last name
     * @return String
     */
    public static String promptLastName(Scanner s) {
        System.out.println("Enter a last name");
        System.out.print("> ");
        return s.nextLine();
    }

    /**
     * Print a line to the console
     *
     * @param s String to print
     */
    public static void printLine(String s) {
        System.out.println(s);
    }

    /**
     * Print text to the console
     *
     * @param s String to print
     */
    public static void print(String s) {
        System.out.print(s);
    }

    /**
     * Pause execution until user inputs any keystroke
     */
    public static void pauseTillInput() {
        System.out.println("Press any key to continue...");
        try {
            System.in.read();
        } catch (Exception ignored) {

        }
    }
}
