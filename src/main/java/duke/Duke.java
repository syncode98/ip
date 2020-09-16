package duke;

import duke.exception.IllegalEmptyDescriptionException;
import duke.exception.IllegalPrepositionWithoutDate;
import duke.task.TaskArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static Scanner inputScanner = new Scanner(System.in);

    public static String KEYWORD_BYE = "bye";
    public static String KEYWORD_LIST = "list";
    public static String KEYWORD_DONE = "done";
    public static String KEYWORD_TODO = "todo";
    public static String KEYWORD_DEADLINE = "deadline";
    public static String KEYWORD_EVENT = "event";
    public static String KEYWORD_DELETE = "delete";


    public static void main(String[] args) throws IOException {
        initialiseMike();
        readInput();
    }

    public static void initialiseMike() {
        PrintMethod.printLines();
        System.out.println("Hello! I'm Mike!\nEnter your name:");
        PrintMethod.printLines();
        String nameOfUser = inputScanner.nextLine();
        PrintMethod.printLines();
        System.out.println("Hello " + nameOfUser + " !");
        PrintMethod.printLines();
        printContents();
        PrintMethod.printLines();
        System.out.println("Alright " + nameOfUser + " , What can I do for you?");
        PrintMethod.printLines();
    }


    /** Reads the input and calls the corresponding method */

    public static void readInput() throws IOException {

        String input = inputScanner.nextLine();
        while (!input.equals(KEYWORD_BYE)) {

            if (input.equals(KEYWORD_LIST)) {
                TaskArray.printTasks();

            } else {
                boolean writeFile = true;
                if (input.contains(KEYWORD_DONE)) {
                    TaskArray.readDoneAndDelete(input, KEYWORD_DONE, writeFile);

                } else if (input.contains(KEYWORD_TODO)) {
                    TaskArray.readTask(input, KEYWORD_TODO, writeFile);

                } else if (input.contains(KEYWORD_DEADLINE)) {
                    TaskArray.readTask(input, KEYWORD_DEADLINE, writeFile);

                } else if (input.contains(KEYWORD_EVENT)) {

                    TaskArray.readTask(input, KEYWORD_EVENT, writeFile);

                } else if (input.contains(KEYWORD_DELETE)) {
                    TaskArray.readDoneAndDelete(input, KEYWORD_DELETE, writeFile);

                } else {
                    PrintMethod.invalidCommand();

                }
            }
            input = inputScanner.nextLine();
        }
        PrintMethod.exitCommand();

    }

    public static void printContents() {

        try {
            String filePath = "data/data.txt";
            TaskArray.checkDirectoryStatus();
            TaskArray.checkFileStatus(filePath);
            ArrayList<String> fileLines = new ArrayList<>();
            TaskArray.fileData(fileLines);

//            String lastLine = null;
//            File file = new File(filePath);
//            Scanner readFile = new Scanner(file);

//            while (readFile.hasNext()) {
//                lastLine = readFile.nextLine();
//                fileLines.add(lastLine);
//                System.out.println(lastLine);
//            }
            for (String line: fileLines) {
                System.out.println(line);
            }


            if (!fileLines.contains("You do not have any tasks!")) {

                PrintMethod.printLines();
                System.out.println("Do you want to keep the contents of the file?");
                PrintMethod.printLines();
                String decision = inputScanner.nextLine();
                PrintMethod.printLines();
                if (decision.equalsIgnoreCase("yes")) {
                    for (String line : fileLines) {
                        if (line.equals("Here are the tasks!") || line.equals("You do not have any tasks!")) {
                            continue;
                        } else {
                            TaskArray.fileToTask(line);
                        }
                    }

                } else {
                    TaskArray.clearFile();
                    TaskArray.createFile();
                }
            }
        } catch (InvalidPathException i) {
            System.out.println("The path does not exist!");
        } catch (NullPointerException n) {
            //If the user already has an empty file in the location
            try {
                String noTasks = "You do not have any tasks!";
                TaskArray.writeToFile(noTasks);
                System.out.println(noTasks);
            } catch (IOException e) {
                System.out.println("There is an error with the file!");
            }
        } catch (IllegalEmptyDescriptionException e) {
            System.out.println("The description is empty!!");
        } catch (IllegalPrepositionWithoutDate illegalPrepositionWithoutDate) {
            illegalPrepositionWithoutDate.printStackTrace();
        }
    }


}
