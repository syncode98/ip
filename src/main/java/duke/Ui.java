package duke;


import duke.task.Task;

import java.util.ArrayList;

import java.util.Scanner;

public class Ui {


    private final String nameOfUser;

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
        System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /** Prints out a warning if the command done is empty. */
    public static void printInvalidDone() {
        System.out.println(" Command done cannot be empty!");
    }

    /** Prints out a warning if the command delete is empty. */
    public static void printInvalidDelete() {
        System.out.println(" Command delete cannot be empty!");
    }

    /**
     * Prints out a warning if the user executes the done,delete,find command for a task
     * that does not exist.
     */
    public static void printInvalidTask() {
        System.out.println(" Task does not exist in list!");
    }

    /** Prints out a warning if the user uses an invalid index for the done or delete command. */
    public static void printWithinRangeTask() {
        System.out.println(" Task number must be in between 1 and 100.");
    }

    /** Prints out a warning if the user enters a task without any description */
    public static void printEmptyDescription(String task) {
        String description = task.equals("event") ? ("n " + task) : (" " + task);
        System.out.println(" OOPS!!! The description of a" + description + " cannot be empty.");
    }

    /** Prints out a warning if the user enters a task with invalid date */
    public static void printEmptyDate(String task) {

        System.out.println(" Enter a valid date for " + task + ".");
    }

    /** Prints out all of the tasks from the data.txt file. */
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

    /** Prints out a warning if user enters the wrong preposition for deadline or event. */
    public static void printIncorrectPreposition(String keyword) {
        if (keyword.equals("event")) {
            System.out.println("Invalid preposition! Use 'at' for events!");
        } else if (keyword.equals("deadline")) {
            System.out.println("Invalid preposition! Use 'by' for deadlines!");
        }
    }

    public static void printSlash() {
        System.out.println("Please enter the slash for the task!");
    }

}

