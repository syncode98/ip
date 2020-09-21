package duke;

import duke.exception.IllegalEmptyDescriptionException;
import duke.exception.IllegalNumberException;
import duke.exception.IllegalPrepositionWithoutDate;
import duke.task.*;

import java.io.IOException;
import java.util.Scanner;

import static duke.task.TaskList.MAX_SIZE;
import static duke.task.TaskList.addToTasks;


public class Parser {

    public static String KEYWORD_BYE = "bye";
    public static String KEYWORD_LIST = "list";
    public static String KEYWORD_DONE = "done";
    public static String KEYWORD_TODO = "todo";
    public static String KEYWORD_DEADLINE = "deadline";
    public static String KEYWORD_EVENT = "event";
    public static String KEYWORD_DELETE = "delete";

    public static String DELIMITER_EMPTY_STRING = "";
    public static String DELIMITER_SLASH = "/";
    public static String DELIMITER_SEMI_COLON = ":";
    public static String DELIMITER_CHARACTER = " ";
    public static String DIVIDER = " | ";


    public static void readInput() {
        try {
            Scanner inputScanner = new Scanner(System.in);
            String input = inputScanner.nextLine();
            while (!input.equals(KEYWORD_BYE)) {

                if (input.equals(KEYWORD_LIST)) {
                    TaskList.printTasks();

                } else {
                    boolean writeFile = true;
                    if (input.contains(KEYWORD_DONE)) {
                        readDoneAndDelete(input, KEYWORD_DONE, writeFile);

                    } else if (input.contains(KEYWORD_TODO)) {
                        readTask(input, KEYWORD_TODO, writeFile);

                    } else if (input.contains(KEYWORD_DEADLINE)) {
                        readTask(input, KEYWORD_DEADLINE, writeFile);

                    } else if (input.contains(KEYWORD_EVENT)) {

                        readTask(input, KEYWORD_EVENT, writeFile);

                    } else if (input.contains(KEYWORD_DELETE)) {
                        readDoneAndDelete(input, KEYWORD_DELETE, writeFile);

                    } else {
                        Ui.invalidCommand();

                    }
                }
                input = inputScanner.nextLine();
            }
            Ui.exitCommand();
        } catch (IOException e) {
            Ui.invalidCommand();
        }

    }

    public static Task returnTaskFromInput(String input, String keyword) throws
            IllegalEmptyDescriptionException, IllegalPrepositionWithoutDate {
        Task currentTask;
        switch (keyword) {
        case "event":
            String[] eventDetails = parseString(keyword, input);
            currentTask = new Event(eventDetails[0], eventDetails[1]);
            break;
        case "deadline":
            String[] deadlineDetails = parseString(keyword, input);
            currentTask = new Deadline(deadlineDetails[0], deadlineDetails[1]);
            break;
        default:
            String task = returnTaskDescription(input, keyword);
            currentTask = new Todo(task);
            break;
        }
        return currentTask;

    }

    /**
     * Returns a task object that has been instantiated according to its type of task
     *
     * @param input   Given by the user
     * @param keyword The type of the task
     * @return A task object
     * @throws IllegalEmptyDescriptionException If the user does not enter a valid description
     * @throws IllegalPrepositionWithoutDate    If the user does not follow the
     *                                          required format for deadline and events
     */


    public static void readTask(String input, String keyword, boolean writeToFile) {

        try {
            Ui.printLines();
            Task currentTask = returnTaskFromInput(input, keyword);
            addToTasks(currentTask, writeToFile);

        } catch (IllegalEmptyDescriptionException i) {
            Ui.printEmptyDescription(keyword);

        } catch (ArrayIndexOutOfBoundsException | IllegalPrepositionWithoutDate a) {
            Ui.printEmptyDate(keyword);
        } catch (IOException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
        Ui.printLines();
    }


    public static String returnTaskDescription(String command, String typeOfTask) throws
            IllegalEmptyDescriptionException {
        String task = command.replace(typeOfTask, DELIMITER_EMPTY_STRING).strip();
        if (task.equals("")) {
            throw new IllegalEmptyDescriptionException();
        }
        return task;
    }

    public static void readDoneAndDelete(String command, String keyword, boolean writeFile) {

        Ui.printLines();
        try {

            command = command.replace(keyword, DELIMITER_EMPTY_STRING).strip();
            int indexOfTask = findTaskNumber(command);

            Task current_task = TaskList.taskArrayList.get(indexOfTask);
            if (keyword.equals(KEYWORD_DONE)) {
                current_task.completeTask();

            } else {

                System.out.println("Noted. I've removed this task:");
                System.out.println(" " + current_task.toString());
                TaskList.taskArrayList.remove(current_task);
                System.out.println("Now you have " + TaskList.taskArrayList.size() + " tasks in the list.");
            }
            if (writeFile) {
                TaskList.updateFile(current_task.toString(), keyword);
            }


        } catch (NumberFormatException n) {
            //Alerts user upon not entering a task number
            Ui.printInvalidDone();

        } catch (IllegalNumberException i) {
            //Alerts user upon entering a task that has not been initialised
            Ui.printInvalidTask();

        } catch (IndexOutOfBoundsException o) {
            //Alerts user upon entering a number that is out of range
            Ui.printWithinRangeTask();

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        Ui.printLines();

    }

    public static int findTaskNumber(String command) throws IllegalNumberException {

        int indexOfTask;
        indexOfTask = Integer.parseInt(command);
        if (indexOfTask > TaskList.taskArrayList.size() && indexOfTask <= MAX_SIZE) {
            throw new IllegalNumberException();
        }
        return indexOfTask - 1;

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
        String task = returnTaskDescription(words[0], typeOfTask);
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
    public static void nameOfTask(String input) throws IllegalEmptyDescriptionException,
            IllegalPrepositionWithoutDate {

        char keywordSymbol = input.charAt(0);
        String status = input.substring(4, 5);
        String taskDescription = input.substring(8);
        String keyword = null;
        switch (keywordSymbol) {
        case 'T':
            keyword = KEYWORD_TODO;
            break;
        case 'D':
            keyword = KEYWORD_DEADLINE;
            taskDescription = taskDescription.replace("(", DELIMITER_SLASH);
            taskDescription = taskDescription.replace(")", DELIMITER_EMPTY_STRING);
            taskDescription = taskDescription.replace(DELIMITER_SEMI_COLON, DELIMITER_CHARACTER);

            break;
        default:
            keyword = KEYWORD_EVENT;
            taskDescription = taskDescription.replace("(", DELIMITER_SLASH);
            taskDescription = taskDescription.replace(")", DELIMITER_EMPTY_STRING);
            taskDescription = taskDescription.replace(DELIMITER_SEMI_COLON, DELIMITER_CHARACTER);
            break;
        }
        Task task = returnTaskFromInput(taskDescription, keyword);

        if (status.equals("1")) {
            task.setDone(true);
        }
        try {
            addToTasks(task, false);
        } catch (IOException e) {
            System.out.println("Not able to add to file!");

        }
        Ui.printLines();

    }
}
