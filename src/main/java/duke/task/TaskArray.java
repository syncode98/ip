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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TaskArray {
    public static final int MAX_SIZE = 100;
    public static ArrayList<Task> taskArrayList = new ArrayList<>();

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

    public static void readDoneAndDelete(String command, String keyword) {
        PrintMethod.printLines();
        try {
            command = command.replace(keyword, DELIMITER_EMPTY_STRING).strip();
            int indexOfTask = findTaskNumber(command);
            Task current_task = taskArrayList.get(indexOfTask);
            if (keyword.equals(Duke.KEYWORD_DONE)) {
                current_task.completeTask();
            } else {

                System.out.println("Noted. I've removed this task:");
                System.out.println(" " + current_task.toString());
                taskArrayList.remove(current_task);
                System.out.println("Now you have " + taskArrayList.size() + " tasks in the list.");
            }


        } catch (NumberFormatException n) {
            //Alerts user upon not entering a task number
            PrintMethod.printInvalidDone();

        } catch (IllegalNumberException i) {
            //Alerts user upon entering a task that has not been initialised
            PrintMethod.printInvalidTask();

        } catch (IndexOutOfBoundsException o) {
            //Alerts user upon entering a number that is out of range
            PrintMethod.printWithinRangeTask();

        }
        PrintMethod.printLines();

    }

    public static int findTaskNumber(String command) throws IllegalNumberException {

        int indexOfTask;
        indexOfTask = Integer.parseInt(command);
        if (indexOfTask > taskArrayList.size() && indexOfTask <= MAX_SIZE) {
            throw new IllegalNumberException();
        }
        return indexOfTask - 1;

    }

    public static void addToTasks(Task task) {
        taskArrayList.add(task);
        task.addTask();
        System.out.println("Now you have " + taskArrayList.size() + " tasks in the list.");
    }

    public static void printTasks() {
        PrintMethod.printAllTasks(taskArrayList);
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


}
