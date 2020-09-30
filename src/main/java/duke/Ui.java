package duke;

import duke.exception.InvalidCommand;
import duke.task.Task;

import java.util.ArrayList;

import java.util.Scanner;

public class Ui {

    public static final char sadFace = '\u2639';

    public String nameOfUser;

    public static final Scanner inputScanner = new Scanner(System.in);

    public Ui() {
        Ui.printLines();
        System.out.println("Hello! I'm Mike!\nEnter your name:");
        Ui.printLines();
        nameOfUser = Ui.inputScanner.nextLine();
        Ui.printLines();
        System.out.println("Hello " + nameOfUser + " !");
        Ui.printLines();

    }

    public void userCommands() {
        System.out.println("Alright " + nameOfUser + " , What can I do for you?");
        Ui.printLines();

    }

    /**
     * Checks if the user wants to retain the contents in the data.txt file.
     *
     * @return The decision of the user to the Storage.java class.
     */
    public static String keepContents() {
        printLines();
        System.out.println("Do you want to keep the contents of the file?");
        Ui.printLines();
        String decision = Ui.inputScanner.nextLine();
        printLines();

        while (!decision.equalsIgnoreCase("yes") && !decision.equalsIgnoreCase("no")) {
            invalidDecision();
            printLines();
            decision = Ui.inputScanner.nextLine();
            printLines();

        }

        return decision;
    }

    public static void invalidDecision() {
        System.out.println("Please enter yes or no.");
    }

    public static void printLines() {
        System.out.println("-------------------------------------------------------------");
    }


    /**
     * Prints all the tasks from the taskArrayList
     *
     * @param taskArrayList The ArrayList in which the tasks are stored.
     */
    public static void printAllTasks(ArrayList<Task> taskArrayList) {
        printLines();

        try {
            if (TaskList.taskArrayList.size() == 0) {
                System.out.println("There are currently no tasks available!");
            } else {
                System.out.println("Here are the tasks in your list:");
                for (Task task : TaskList.taskArrayList) {
                    int indexOfTask = TaskList.taskArrayList.indexOf(task) + 1;
                    System.out.println(indexOfTask + "." + task.toString());
                }
            }
            printLines();
        } catch (NullPointerException n) {
            Ui.printInvalidTask();
        }
    }

    /** Prints out a warning is user enters an invalid Command */
    public static void invalidCommand() {
        System.out.println(sadFace + " OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void printInvalidDone() {
        System.out.println(sadFace + " Command done cannot be empty!");
    }

    public static void printInvalidDelete() {
        System.out.println(sadFace + " Command delete cannot be empty!");
    }

    public static void printInvalidTask() {
        System.out.println(sadFace + " Task does not exist in list!");
    }

    public static void printWithinRangeTask() {
        System.out.println(sadFace + " Task number must be in between 1 and 100.");
    }

    public static void printEmptyDescription(String task) {
        String description = task.equals("event") ? ("n " + task) : (" " + task);
        System.out.println(sadFace + " OOPS!!! The description of a" + description + " cannot be empty.");
    }

    public static void printEmptyDate(String task) {

        System.out.println(sadFace + " Enter a valid date for " + task + ".");
    }

    public static void invalidPath() {
        System.out.println("The path does not exist!");
    }

    public static void printTasksFromFile(ArrayList<String> fileLines) {
        for (String line : fileLines) {
            System.out.println(line);
        }
    }

    public static void printMatchingTasks() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public static void printClearTasks() {
        System.out.println("All the tasks have been cleared!");
    }

    public static void printIncorrectPreposition(String keyword) {
        if (keyword.equals("event")) {
            System.out.println("Inavlid preposition! Use 'at' for events!");
        } else if (keyword.equals("deadline")) {
            System.out.println("Inavlid preposition! Use 'by' for deadlines!");
        }
    }

}

