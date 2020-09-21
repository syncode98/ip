package duke;

import duke.exception.IllegalEmptyDescriptionException;
import duke.exception.IllegalPrepositionWithoutDate;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static String filePath;
    private static ArrayList<String> fileLines;
    public static String DIRECTORY ="data";
    public static String DIVIDER = " | ";

    public Storage(String pathOfFile) {
        fileLines = new ArrayList<>();
        filePath = pathOfFile;
        checkDirectoryStatus();
        checkFileStatus();
    }

    public static ArrayList<Task> load() {
        ArrayList<Task> tasksFromFile = new ArrayList<Task>();
        try {
            if (!fileLines.contains("You do not have any tasks!")) {

                Ui.printTasksFromFile(fileLines);
                String decision = Ui.keepContents();

                if (decision.equalsIgnoreCase("yes")) {

                    for (String line : fileLines) {
                        //These two lines in the data.txt file are not tasks
                        if (line.equals("Here are the tasks!") || line.equals("You do not have any tasks!")) {
                            continue;
                        } else {
                            Task task = Parser.taskFromFile(line);
                            tasksFromFile.add(task);
                        }
                    }


                } else {
                    //User decides to delete the existing file
                    clearFile();
                    createFile();
                }
            }
        } catch (NullPointerException n) {

            //If the user already has a data.txt file in the location but it is empty
            try {
                String noTasks = "You do not have any tasks!";
                writeToFile(noTasks);
                System.out.println(noTasks);
            } catch (IOException e) {
                System.out.println("There is an error with the file!");
            }

        } catch (IllegalEmptyDescriptionException e) {
            System.out.println("The description is empty!!");
        } catch (IllegalPrepositionWithoutDate illegalPrepositionWithoutDate) {
            System.out.println("Add an appropriate data");
        }
        return tasksFromFile;
    }

    public static void fileData() {
        try {
            fileLines.clear();
            File file = new File(filePath);
            Scanner readFile = new Scanner(file);

            while (readFile.hasNext()) {
                String line = readFile.nextLine();
                fileLines.add(line);
            }

        } catch (FileNotFoundException e) {
            createFile();
            System.out.println("Not able to find file! The file will be created.");
            fileData();
        }

    }


    public static void updateFile(String doneTask, String keyword) throws IOException {

        fileData();
        clearFile();
        String task = doneTask.substring(6);
        for (String line : fileLines) {
            if (line.contains(task)) {
                if (keyword.equals(Parser.KEYWORD_DELETE)) {
                    continue;
                } else {
                    line = line.replace("0", "1");
                }
            }
            writeToFile(line);
        }
        if (TaskList.getSize() == 0) {
            clearFile();
            createFile();
        }

    }


    public static void writeToFile(String textToAdd) throws IOException {

        File file = new File(filePath);
        Scanner readFile = new Scanner(file);
        FileWriter fileWriter = new FileWriter((filePath), true);

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
                textToAdd.contains("You do not have any tasks!")) {
            output = textToAdd;
        } else {
            output = textToAdd.charAt(1) + DIVIDER + status + DIVIDER + textToAdd.substring(6);
        }
        fileWriter.write(output + System.lineSeparator());
        fileWriter.close();
    }

    public static void createFile() {

        try {
            Path path = Paths.get(filePath);
            Files.writeString(path, "You do not have any tasks!" + System.lineSeparator());

        } catch (IOException i) {
            System.out.println("Not able to create the file!");
        }
    }

    public static void clearFile() {
        try {
            FileWriter file = new FileWriter(filePath);
            file.close();
        } catch (IOException i) {
            System.out.println("Not able to clear the file!");
        }
    }


    public static void checkFileStatus() {

        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                createFile();
            }

        } catch (InvalidPathException i) {
            System.out.println("The path of the file does not exist!");

        }

    }

    public void checkDirectoryStatus() {

        try {
            Path path = Paths.get(DIRECTORY);
            if (!Files.exists(path)) {
                System.out.println("The directory does not exist!");
                Ui.printLines();
                Files.createDirectories(path);
                File file = new File(filePath);
                createFile();
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