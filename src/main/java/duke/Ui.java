package duke;

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
        //Parser.readInput();

    }

    public static String keepContents() {
        Ui.printLines();
        System.out.println("Do you want to keep the contents of the file?");
        Ui.printLines();
        String decision = Ui.inputScanner.nextLine();
        Ui.printLines();

        return decision;
    }


    public static void printLines() {
        System.out.println("-------------------------------------------------------------");
    }


    public static void printAllTasks(ArrayList<Task> taskArrayList) {
        printLines();

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
    }

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

    public static void printMatchingTasks(){
        System.out.println("Here are the matching tasks in your list:");
    }


}

