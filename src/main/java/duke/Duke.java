package duke;

import duke.exception.IllegalEmptyDescriptionException;
import duke.exception.IllegalNumberException;
import duke.exception.IllegalPrepositionWithoutDate;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;

public class Duke {
    private static final int MAX_SIZE = 100;
    private static Task[] tasks = new Task[MAX_SIZE];
    public static Scanner inputScanner = new Scanner(System.in);

    public static String INPUT_BYE = "bye";
    public static String INPUT_LIST = "list";
    public static String INPUT_DONE = "done";
    public static String INPUT_TODO = "todo";
    public static String INPUT_DEADLINE = "deadline";
    public static String INPUT_EVENT = "event";
    public static String DELIMITER_EMPTY_STRING = "";
    public static String DELIMITER_SLASH = "/";
    public static String DELIMITER_SEMI_COLON = ":";
    public static String DELIMITER_CHARACTER = " ";


    public static void main(String[] args) {
        initialiseMike();
        readInput();
    }

    public static void initialiseMike() {
        PrintMethod.printLines();
        System.out.println("Hello! I'm Mike!\nEnter your name:");
        PrintMethod.printLines();
        String nameOfUser = inputScanner.nextLine();
        PrintMethod.printLines();
        System.out.println("Alright " + nameOfUser + " , What can I do for you?");
        PrintMethod.printLines();
    }


    public static void readInput() {
        String command = inputScanner.nextLine();
        while (!command.equals(INPUT_BYE)) {

            if (command.equals(INPUT_LIST)) {
                PrintMethod.printAllTasks(tasks);

            } else if (command.contains(INPUT_DONE)) {
                readDone(command);

            } else if (command.contains(INPUT_TODO)) {
                readTodo(command);

            } else if (command.contains(INPUT_DEADLINE)) {
                readDeadline(command);

            } else if (command.contains(INPUT_EVENT)) {
                readEvent(command);

            } else {
                PrintMethod.invalidCommand();

            }
            command = inputScanner.nextLine();
        }
        PrintMethod.exitCommand();


    }

    public static void readEvent(String command) {

        try {
            PrintMethod.printLines();
            String[] words = parseString(INPUT_EVENT, command);
            Event event = new Event(words[0], words[1]);
            addToTasks(event);

        } catch (IllegalEmptyDescriptionException i) {
            PrintMethod.printEmptyDescription(INPUT_EVENT);

        } catch (ArrayIndexOutOfBoundsException a) {
            PrintMethod.printEmptyDate(INPUT_EVENT);

        } catch (IllegalPrepositionWithoutDate p) {
            PrintMethod.printEmptyDate(INPUT_EVENT);
        }
        PrintMethod.printLines();
    }

    public static void readDeadline(String command) {
        try {
            PrintMethod.printLines();
            String[] words = parseString(INPUT_DEADLINE, command);
            Deadline deadline = new Deadline(words[0], words[1]);
            addToTasks(deadline);

        } catch (IllegalEmptyDescriptionException i) {
            PrintMethod.printEmptyDescription(INPUT_DEADLINE);

        } catch (ArrayIndexOutOfBoundsException a) {
            PrintMethod.printEmptyDate(INPUT_DEADLINE);

        } catch (IllegalPrepositionWithoutDate p) {
            PrintMethod.printEmptyDate(INPUT_DEADLINE);
        }
        PrintMethod.printLines();
    }

    public static void readTodo(String command) {
        PrintMethod.printLines();
        try {
            String task = returnTask(command, INPUT_TODO);
            Todo todo = new Todo(task);
            addToTasks(todo);
        } catch (IllegalEmptyDescriptionException d) {
            PrintMethod.printEmptyDescription(INPUT_TODO);
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
            command = command.replace(INPUT_DONE, DELIMITER_EMPTY_STRING).strip();
            int indexOfTask = findTaskNumber(command);
            tasks[indexOfTask].completeTask();

        } catch (NumberFormatException n) {
            //Alerts user upon not entering a task number
            PrintMethod.printInvalidDone();

        } catch (IllegalNumberException i) {
            //Alerts user upon entering a task that has not been initialised
            PrintMethod.printInvalidTask();

        } catch (ArrayIndexOutOfBoundsException a) {
            //Alerts user upon entering a tasknumber that is out of range
            PrintMethod.printWithinRangeTask();

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

    public static void addToTasks(Task task) {
        tasks[Task.getNumberOfTasks()] = task;
        task.addTask();
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
    }

    /**
     * Returns the preposition and the deadline of the given task.
     *
     * @param typeOfTask
     * @param command
     * @return
     * @throws IllegalEmptyDescriptionException
     * @throws IllegalPrepositionWithoutDate
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
