package duke.task;

import duke.Parser;
import duke.Ui;


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

public class TaskList {
    public static final int MAX_SIZE = 100;
    public static ArrayList<Task> taskArrayList = new ArrayList<>();

    public static String DELIMITER_EMPTY_STRING = "";
    public static String DELIMITER_SLASH = "/";
    public static String DELIMITER_SEMI_COLON = ":";
    public static String DELIMITER_CHARACTER = " ";
    public static String DIVIDER = " | ";

    public static String DIRECTORY = "data";
    public static String FILEPATH = "data/data.txt";




    public static void addToTasks(Task task, boolean fileWrite) throws IOException {
        taskArrayList.add(task);
        task.addTask();
        if (fileWrite) {
            writeToFile(task.toString());
            System.out.println("Now you have " + taskArrayList.size() + " tasks in the list.");
        }
    }


    public static void printTasks() {
        Ui.printAllTasks(taskArrayList);
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
                if (keyword.equals(Parser.KEYWORD_DELETE)) {
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
                TaskList.createFile();
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
                Ui.printLines();
                Files.createDirectory(path);
                createFile();
                File file = new File(DIRECTORY);
                String absolutePath = file.getAbsolutePath();
                System.out.println("A new directory has been created at " + absolutePath);
                Ui.printLines();
            }

        } catch (IOException e) {
            System.out.println("Could not create Directory!");
        } catch (InvalidPathException i) {
            System.out.println("The path of the directory does not exist!");
        }
    }


}
