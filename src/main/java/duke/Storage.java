package duke;

import duke.command.AddCommand;
import duke.exception.*;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    private static String filePath;
    private static ArrayList<String> fileLines;
    public static String DIRECTORY = "data";
    public static String DIVIDER = " | ";

    public static String DELIMITER_EMPTY_STRING = "";
    public static String DELIMITER_SLASH = "/";
    public static String DELIMITER_SEMI_COLON = ":";


    public Storage(String pathOfFile) {
        fileLines = new ArrayList<>();
        filePath = pathOfFile;
        checkDirectoryStatus();
        checkFileStatus();
        fileData();
    }

    /**
     * Loads the data in the data.txt file upon the start of the program..
     *
     * @return ArrayList of the lines in the data.txt file
     */
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
                            Task task = Storage.taskFromFile(line);
                            tasksFromFile.add(task);
                        }
                    }


                } else if (decision.equalsIgnoreCase("no")) {
                    //User decides to delete the existing file
                    clearFile();
                    createFile();
                } else {
                    Ui.invalidCommand();

                    Ui.printLines();
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

        }
        return tasksFromFile;
    }

    /** Reads the data in the data.txt file and stores it in an ArrayList */
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

    /**
     * Updates the data.txt file if a task has been deleted or
     * has been completed
     */
    public static void updateFile(String doneTask, String keyword) throws IOException {

        fileData();
        clearFile();
        String task = doneTask.substring(6);
        for (String line : fileLines) {
            String toCompare = line.substring(7).strip();
            if (toCompare.equals(task)) {
                if (keyword.equals(Parser.KEYWORD_DELETE)) {
                    continue;
                } else {
                    line = line.replaceFirst("0", "1");
                }
            }
            writeToFile(line);
        }
        if (TaskList.getSize() == 0) {
            clearFile();
            createFile();
        }

    }

    /** Writes the data to the file. */
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

    /** Creates a data.txt file */
    public static void createFile() {

        try {
            Path path = Paths.get(filePath);
            Files.writeString(path, "You do not have any tasks!" + System.lineSeparator());

        } catch (IOException i) {
            System.out.println("Not able to create the file!");
        }
    }

    /** Clears the data.txt file of all the data */
    public static void clearFile() {
        try {
            FileWriter file = new FileWriter(filePath);
            file.close();
        } catch (IOException i) {
            System.out.println("Not able to clear the file!");
        }
    }

    /**
     * Checks if the file exists in the directory and creates
     * the file if it is not present
     */
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

    /**
     * Checks if the directory is present.If not,
     * this method created the directory for the user.
     */
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

    /**
     * Reads the line from the file and converts
     * it into a task.
     *
     * @param input The line in the data.txt file.
     * @return An instantiated task.
     */
    public static Task taskFromFile(String input) {

        Task task = null;
        String keyword = null;
        try {
            char keywordSymbol = input.charAt(0);
            String status = input.substring(4, 5);
            String taskDescription = input.substring(8);
            switch (keywordSymbol) {
            case 'T':
                keyword = "todo";
                break;
            case 'D':
                keyword = "deadline";
                taskDescription = taskDescription.replace("(", DELIMITER_SLASH);
                taskDescription = taskDescription.replace(")", DELIMITER_EMPTY_STRING);
                taskDescription = taskDescription.replaceFirst(DELIMITER_SEMI_COLON, DELIMITER_EMPTY_STRING);
                int index = taskDescription.indexOf("/by");
                String dateAndTime = taskDescription.substring(index + 3).strip();
                String deadline = convertToTime(dateAndTime);
                taskDescription = taskDescription.replace(dateAndTime, deadline);
                break;
            default:
                keyword = "event";
                taskDescription = taskDescription.replace("(", DELIMITER_SLASH);
                taskDescription = taskDescription.replace(")", DELIMITER_EMPTY_STRING);
                taskDescription = taskDescription.replaceFirst(DELIMITER_SEMI_COLON, DELIMITER_EMPTY_STRING);
                int index1 = taskDescription.indexOf("/at");
                String eventDetails = taskDescription.substring(index1 + 3).strip();
                String eventTime = convertToTime(eventDetails);
                taskDescription = taskDescription.replace(eventDetails, eventTime);
                break;
            }
            task = AddCommand.returnTask(taskDescription, keyword);

            if (status.equals("1")) {
                task.setDone(true);
            }

        } catch (IllegalEmptyDescriptionException e) {
            Ui.printEmptyDescription(task.toString());
        } catch (IllegalPrepositionWithoutDate i) {
            Ui.printEmptyDate(task.toString());
        } catch (InvalidCommand i) {
            Ui.printInvalidTask();
        } catch (InvalidPreposition i) {
            assert keyword != null;
            Ui.printIncorrectPreposition(keyword);
        } catch (NullPointerException n) {
            Ui.invalidCommand();
        } catch (IllegalSlashException e) {
            Ui.printSlash();
        }
        return task;

    }

    /**
     * Reads the deadline and converts it into the correct format
     * which is accepted by the returnEventDeadline() method in
     * AddCommand class.
     *
     * @param deadline Deadline of the task
     * @return The corrected String format of the deadline.
     */
    public static String convertToTime(String deadline) {
        DateTimeFormatter formatter;
        String startTime = "";
        String endTime = "";
        String newDeadline = "";
        String[] words = deadline.split(" ");
        if (deadline.contains("-")) {
            String[] details = deadline.split("-");
            startTime = details[0];
            deadline = deadline.replace(details[0] + "-", "");
            details = deadline.split(" ");
            endTime = details[0];
            deadline = deadline.replace(endTime + " ", "");
            formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
            LocalDate date = LocalDate.parse(deadline, formatter);
            newDeadline += date.toString() + " " + startTime + " - " + endTime;
        } else if (deadline.contains(":")) {
            formatter = DateTimeFormatter.ofPattern("HH:mm d MMM yyyy");
            LocalDateTime date = LocalDateTime.parse(deadline, formatter);

            newDeadline += date.toString().replace("T", " ");
        } else {
            formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
            deadline = deadline.strip();
            newDeadline += LocalDate.parse(deadline, formatter).toString();
        }
        return newDeadline;

    }

}
