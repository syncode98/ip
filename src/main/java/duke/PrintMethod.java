package duke;

import duke.task.Task;

public class PrintMethod {
    public static final char sadFace = '\u2639';


    public static void printLines() {
        System.out.println("-------------------------------------------------------------");
    }

    public static void exitCommand() {
        printLines();
        System.out.println("Bye.Hope to see you again soon!");
        printLines();
    }


    public static void printAllTasks(Task[] tasks) {
        printLines();

        if (Task.getNumberOfTasks() == 0) {
            System.out.println("There are currently no tasks available!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Task.getNumberOfTasks(); i++) {
                int indexOfTask = i + 1;
                System.out.println(indexOfTask + "." + tasks[i].toString());
            }

        }

        printLines();
    }

    public static void invalidCommand() {
        printLines();
        System.out.println(sadFace + " OOPS!!! I'm sorry, but I don't know what that means :-(");
        printLines();
    }

    public static void printInvalidDone() {
        System.out.println(sadFace + " Command done cannot be empty!");
    }

    public static void printInvalidTask() {
        System.out.println(sadFace + " Duke.Task does not exist in list!");
    }

    public static void printWithinRangeTask() {
        System.out.println(sadFace + " Duke.Task number must be in between 1 and 100");
    }

    public static void printEmptyDescription(String task) {
        String description = task.equals(Duke.KEYWORD_EVENT) ? ("n " + task) : (" " + task);
        System.out.println(sadFace + " OOPS!!! The description of a" + description + " cannot be empty.");
    }

    public static void printEmptyDate(String task) {

        System.out.println(sadFace + " Enter a valid date for " + task + ".");
    }


}

