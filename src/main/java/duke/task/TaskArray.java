package duke.task;

import duke.Duke;
import duke.PrintMethod;
import duke.exception.IllegalEmptyDescriptionException;
import duke.exception.IllegalNumberException;
import duke.exception.IllegalPrepositionWithoutDate;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskArray {
    public static final int MAX_SIZE = 100;
    public static Task[] tasks = new Task[MAX_SIZE];

    public static String DELIMITER_EMPTY_STRING = "";
    public static String DELIMITER_SLASH = "/";
    public static String DELIMITER_SEMI_COLON = ":";
    public static String DELIMITER_CHARACTER = " ";

    public static void readEvent(String command) {

        try {
            PrintMethod.printLines();
            String[] words = parseString(Duke.KEYWORD_EVENT, command);
            Event event = new Event(words[0], words[1]);
            addToTasks(event);

        } catch (IllegalEmptyDescriptionException i) {
            PrintMethod.printEmptyDescription(Duke.KEYWORD_EVENT);

        } catch (ArrayIndexOutOfBoundsException | IllegalPrepositionWithoutDate a) {
            PrintMethod.printEmptyDate(Duke.KEYWORD_EVENT);

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        PrintMethod.printLines();
    }

    public static void readDeadline(String command) {
        try {
            PrintMethod.printLines();
            String[] words = parseString(Duke.KEYWORD_DEADLINE, command);
            Deadline deadline = new Deadline(words[0], words[1]);
            addToTasks(deadline);

        } catch (IllegalEmptyDescriptionException i) {
            PrintMethod.printEmptyDescription(Duke.KEYWORD_DEADLINE);

        } catch (ArrayIndexOutOfBoundsException | IllegalPrepositionWithoutDate a) {
            PrintMethod.printEmptyDate(Duke.KEYWORD_DEADLINE);

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        PrintMethod.printLines();
    }

    public static void readTodo(String command) {
        PrintMethod.printLines();
        try {
            String task = returnTask(command, Duke.KEYWORD_TODO);
            Todo todo = new Todo(task);
            addToTasks(todo);
        } catch (IllegalEmptyDescriptionException d) {
            PrintMethod.printEmptyDescription(Duke.KEYWORD_TODO);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        PrintMethod.printLines();

    }

    public static String returnTask(String command, String typeOfTask) throws IllegalEmptyDescriptionException {
        String task = command.replace(typeOfTask, DELIMITER_EMPTY_STRING).strip();
        if (task.equals("")) {
            throw new IllegalEmptyDescriptionException();
        }
        return task;
    }

    public static void readDone(String command) {
        PrintMethod.printLines();
        try {
            command = command.replace(Duke.KEYWORD_DONE, DELIMITER_EMPTY_STRING).strip();
            int indexOfTask = findTaskNumber(command);
            tasks[indexOfTask].completeTask();
            writeToFile(tasks[indexOfTask].toString());

        } catch (NumberFormatException n) {
            //Alerts user upon not entering a task number
            PrintMethod.printInvalidDone();

        } catch (IllegalNumberException i) {
            //Alerts user upon entering a task that has not been initialised
            PrintMethod.printInvalidTask();

        } catch (ArrayIndexOutOfBoundsException a) {
            //Alerts user upon entering a number that is out of range
            PrintMethod.printWithinRangeTask();

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        PrintMethod.printLines();

    }

    public static int findTaskNumber(String command) throws IllegalNumberException {

        int indexOfTask;
        indexOfTask = Integer.parseInt(command);
        if (indexOfTask > Task.getNumberOfTasks() && indexOfTask <= MAX_SIZE) {
            throw new IllegalNumberException();
        }
        return indexOfTask - 1;

    }

    public static void addToTasks(Task task) throws IOException {
        tasks[Task.getNumberOfTasks()] = task;
        task.addTask();
        writeToFile(task.toString());

        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
    }


    public static void printTasks() {
        PrintMethod.printAllTasks(tasks);
    }

    /**
     * Returns the preposition and the deadline of the given task.
     *
     * @param typeOfTask The type of the task in the command.
     * @param command    The command given by the user.
     * @return The task and the deadline for the task as a String array
     * @throws IllegalEmptyDescriptionException If the task is not given by the user
     * @throws IllegalPrepositionWithoutDate    If the user does not enter the date after the preposition
     */
    public static String[] parseString(String typeOfTask, String command) throws IllegalEmptyDescriptionException,
            IllegalPrepositionWithoutDate {

        String[] words = command.split(DELIMITER_SLASH);
        String task = returnTask(words[0], typeOfTask);
        words[0] = task;
        String deadlineForTask = words[1].strip();


        //splits the deadline into its preposition and the date
        String[] descriptorsForDate = deadlineForTask.split(DELIMITER_CHARACTER);
        String preposition = descriptorsForDate[0].strip();
        deadlineForTask = deadlineForTask.replace(preposition, DELIMITER_EMPTY_STRING).strip();

        if (deadlineForTask.equals(DELIMITER_EMPTY_STRING)) {
            throw new IllegalPrepositionWithoutDate();
        }

        words[1] = preposition + DELIMITER_SEMI_COLON + deadlineForTask;
        return words;

    }


    public static void writeToFile(String textToAdd) throws IOException {
        String filePath = "data.txt";
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }

    public static void createFile()  {
        try {
            writeToFile("Here are the tasks from your previous session!");
        }catch (IOException i) {
            System.out.println("Something went wrong: " + i.getMessage());
        }
    }


}
