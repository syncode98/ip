package duke.task;

import duke.Duke;
import duke.PrintMethod;
import duke.exception.IllegalEmptyDescriptionException;
import duke.exception.IllegalNumberException;
import duke.exception.IllegalPrepositionWithoutDate;


import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

public class TaskArray {
    public static final int MAX_SIZE = 100;
    public static ArrayList<Task> taskArrayList = new ArrayList<>();

    public static String DELIMITER_EMPTY_STRING = "";
    public static String DELIMITER_SLASH = "/";
    public static String DELIMITER_SEMI_COLON = ":";
    public static String DELIMITER_CHARACTER = " ";
    public static String DIVIDER = " | ";

    public static String DIRECTORY = "data";
    public static String FILEPATH = "data/data.txt";

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
            PrintMethod.printLines();
            Task currentTask = returnTaskFromInput(input, keyword);
            addToTasks(currentTask, writeToFile);

        } catch (IllegalEmptyDescriptionException i) {
            PrintMethod.printEmptyDescription(keyword);

        } catch (ArrayIndexOutOfBoundsException | IllegalPrepositionWithoutDate a) {
            PrintMethod.printEmptyDate(keyword);
        } catch (IOException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
        PrintMethod.printLines();
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
            if (writeFile) {
                updateFile(current_task.toString(), keyword);
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


    public static void addToTasks(Task task, boolean fileWrite) throws IOException {
        taskArrayList.add(task);
        task.addTask();
        if (fileWrite) {
            writeToFile(task.toString());
            System.out.println("Now you have " + taskArrayList.size() + " tasks in the list.");
        }
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

    public static void fileData(ArrayList<String> filesLines) {
        try {

            File file = new File(FILEPATH);
            Scanner readFile = new Scanner(file);

            while (readFile.hasNext()) {
                String line = readFile.nextLine();
                filesLines.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Not able to find file!");
        }

    }


    public static void updateFile(String doneTask, String keyword) throws IOException {
        ArrayList<String> filesLines = new ArrayList<>();
        fileData(filesLines);
        clearFile();
        String task = doneTask.substring(6);
        for (String line : filesLines) {
            if (line.contains(task)) {
                if (keyword.equals(Duke.KEYWORD_DELETE)) {
                    continue;
                } else {
                    line = line.replace("0", "1");
                }
            }
            writeToFile(line);
        }
        if (taskArrayList.size() == 0) {
            clearFile();
            createFile();
        }

    }

    public static void fileToTask(String input) throws IllegalEmptyDescriptionException,
            IllegalPrepositionWithoutDate {

        char keywordSymbol = input.charAt(0);
        String status = input.substring(4, 5);
        String taskDescription = input.substring(8);
        String keyword = null;
        switch (keywordSymbol) {
        case 'T':
            keyword = Duke.KEYWORD_TODO;
            break;
        case 'D':
            keyword = Duke.KEYWORD_DEADLINE;
            taskDescription = taskDescription.replace("(", DELIMITER_SLASH);
            taskDescription = taskDescription.replace(")", DELIMITER_EMPTY_STRING);
            taskDescription = taskDescription.replace(DELIMITER_SEMI_COLON, DELIMITER_CHARACTER);

            break;
        default:
            keyword = Duke.KEYWORD_EVENT;
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
        PrintMethod.printLines();

    }

    public static void writeToFile(String textToAdd) throws IOException {

        File file = new File(FILEPATH);
        Scanner readFile = new Scanner(file);
        FileWriter fileWriter = new FileWriter((FILEPATH), true);

        if (readFile.hasNextLine() && readFile.nextLine().equals("You d" +
                "o not have any tasks!")) {
            clearFile();
            fileWriter.write("Here are the tasks!" + System.lineSeparator());
        }
        char complete = '\u2713';
        int status = 0;

        if (textToAdd.charAt(4) == complete) {
            status = 1;
        }
        String output = null;


        if (textToAdd.contains(DIVIDER) || textToAdd.contains("Here are the tasks!") ||
                textToAdd.contains("You do" + "not have any tasks!")) {
            output = textToAdd;
        } else {
            output = textToAdd.charAt(1) + DIVIDER + status + DIVIDER + textToAdd.substring(6);
        }
        fileWriter.write(output + System.lineSeparator());
        fileWriter.close();
    }

    public static void createFile() {

        try {
            Path path = Paths.get(FILEPATH);
            Files.writeString(path, "You do" + " not have any tasks!" + System.lineSeparator());

        } catch (IOException i) {
            System.out.println("Not able to create the file!");
        }
    }

    public static void clearFile() {
        try {
            FileWriter file = new FileWriter(FILEPATH);
            file.close();
        } catch (IOException i) {
            System.out.println("Something went wrong: " + i.getMessage());
        }
    }


    public static void checkFileStatus(String filePath) {

        try {
            Path path = Paths.get(FILEPATH);
            if (!Files.exists(path)) {
                TaskArray.createFile();
            }

        } catch (InvalidPathException i) {
            System.out.println("The path of the file does not exist!");

        }

    }

    public static void checkDirectoryStatus() {

        try {
            Path path = Paths.get(DIRECTORY);
            if (!Files.exists(path)) {
                System.out.println("The directory does not exist!");
                PrintMethod.printLines();
                Files.createDirectory(path);
                createFile();
                File file = new File(DIRECTORY);
                String absolutePath = file.getAbsolutePath();
                System.out.println("A new directory has been created at " + absolutePath);
                PrintMethod.printLines();
            }

        } catch (IOException e) {
            System.out.println("Could not create Directory!");
        } catch (InvalidPathException i) {
            System.out.println("The path of the directory does not exist!");
        }
    }


}
