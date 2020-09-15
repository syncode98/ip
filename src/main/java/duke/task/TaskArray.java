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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskArray {
    public static final int MAX_SIZE = 100;
    public static Task[] tasks = new Task[MAX_SIZE];

    public static String DELIMITER_EMPTY_STRING = "";
    public static String DELIMITER_SLASH = "/";
    public static String DELIMITER_SEMI_COLON = ":";
    public static String DELIMITER_CHARACTER = " ";

    public static void readTask(String command, String keyword) {

        try {
            PrintMethod.printLines();
            Task currentTask;
            switch (keyword) {
            case "event":
                String[] eventDetails = parseString(keyword, command);
                currentTask = new Event(eventDetails[0], eventDetails[1]);
                break;
            case "deadline":
                String[] deadlineDetails = parseString(keyword, command);
                currentTask = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                break;
            default:
                String task = returnTask(command, keyword);
                currentTask = new Todo(task);
                break;
            }
            addToTasks(currentTask);

        } catch (IllegalEmptyDescriptionException i) {
            PrintMethod.printEmptyDescription(keyword);

        } catch (ArrayIndexOutOfBoundsException | IllegalPrepositionWithoutDate a) {
            PrintMethod.printEmptyDate(keyword);

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
            String task=tasks[indexOfTask].toString();
            searchFile(task);

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

    public static void searchFile(String doneTask) throws FileNotFoundException {
        String filePath = "data.txt";
        File file = new File(filePath);
        Scanner readFile = new Scanner(file);
        List<String> filesLines = new ArrayList<>();
        char complete = '\u2713'; //unicode values for tick
        char incomplete = '\u2A09'; //unicode values for cross

        while (readFile.hasNext()) {
            String line = readFile.nextLine();
            filesLines.add(line);
        }
        clearFile();
        String task = doneTask.substring(6);
        for (String line : filesLines) {
            if (line.contains(task)) {
                line=line.replace(incomplete,complete);
            }
            try {
                writeToFile(line);
            } catch (IOException e) {
                System.out.println("Error in file!");
                ;
            }
        }
    }


    public static void writeToFile(String textToAdd) throws IOException {
        String filePath = "data.txt";
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }

    public static void createFile() {
        try {
            writeToFile("Here are the tasks from your previous session!" + System.lineSeparator());
        } catch (IOException i) {
            System.out.println("Something went wrong: " + i.getMessage());
        }
    }

    public static void clearFile() {
        try {
            String filePath = "data.txt";
            FileWriter fw = new FileWriter(filePath);
            fw.close();
            createFile();
        } catch (IOException i) {
            System.out.println("Something went wrong: " + i.getMessage());
        }
    }


}
