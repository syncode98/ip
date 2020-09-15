package duke.task;

import duke.Duke;
import duke.PrintMethod;
import duke.exception.IllegalEmptyDescriptionException;
import duke.exception.IllegalNumberException;
import duke.exception.IllegalPrepositionWithoutDate;


import java.util.ArrayList;
import java.util.Arrays;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class TaskArray {
    public static final int MAX_SIZE = 100;
    public static ArrayList<Task> taskArrayList = new ArrayList<>();

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
            System.out.println("Something went wrong" +e.getMessage());
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
                searchFile(current_task.toString());
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

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
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


    public static void addToTasks(Task task) throws IOException {
        taskArrayList.add(task);
        task.addTask();
        System.out.println("Now you have " + taskArrayList.size() + " tasks in the list.");
        writeToFile(task.toString());

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


    public static void searchFile(String doneTask) throws FileNotFoundException {

        String filePath = "data/data.txt";
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
                line = line.replace(incomplete, complete);
            }
            try {
                writeToFile(line);
            } catch (IOException e) {
                System.out.println("Error in file!");

            }
        }
    }


    public static void writeToFile(String textToAdd) throws IOException {

        String filePath = "data/data.txt";
        Path path = Paths.get(filePath);
        File file = new File(filePath);
        Scanner readFile = new Scanner(file);
        FileWriter fileWriter = new FileWriter(filePath, true);
        if (readFile.hasNextLine() && readFile.nextLine().equals("You do not have any tasks!")) {
            clearFile();
            fileWriter.write("Here are the tasks!" + System.lineSeparator());
        }

        fileWriter.write(textToAdd + System.lineSeparator());
        fileWriter.close();
    }

    public static void createFile() {

        try {
            String filePath = "data/data.txt";
            Path path = Paths.get(filePath);
            Files.writeString(path, "You do not have any tasks!" + System.lineSeparator());

        } catch (IOException i) {
            System.out.println("Not able to create the file!");
        }
    }

    public static void clearFile() {
        try {
            String filePath = "data/data.txt";
            Path path = Paths.get(filePath);
            FileWriter file = new FileWriter(filePath);
            file.close();
        } catch (IOException i) {
            System.out.println("Something went wrong: " + i.getMessage());
        }
    }

    public static void checkFileStatus(String filePath) {

        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                TaskArray.createFile();
            }

        } catch (InvalidPathException i) {
            System.out.println("The path of the file does not exist!");

        }

    }

    public static void checkDirectoryStatus() {
        try {
            String directory = "data";
            Path path = Paths.get(directory);
            if (!Files.exists(path)) {
                System.out.println("The directory does not exist!\n");
                Files.createDirectory(path);
                System.out.println("A new directory has been created,named  " + path.toString());
            }

        } catch (IOException e) {
            System.out.println("Could not create Directory!");
        } catch (InvalidPathException i) {
            System.out.println("The path of the directory does not exist!");
        }
    }


}
