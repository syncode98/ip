package duke;

import duke.task.TaskArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Scanner;

public class Duke {

    public static Scanner inputScanner = new Scanner(System.in);

    public static String KEYWORD_BYE = "bye";
    public static String KEYWORD_LIST = "list";
    public static String KEYWORD_DONE = "done";
    public static String KEYWORD_TODO = "todo";
    public static String KEYWORD_DEADLINE = "deadline";
    public static String KEYWORD_EVENT = "event";


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

            } else if (input.contains(KEYWORD_DONE)) {
                TaskArray.readDone(input);

            } else if (input.contains(KEYWORD_TODO)) {
                TaskArray.readTask(input, KEYWORD_TODO);

            } else if (input.contains(KEYWORD_DEADLINE)) {
                TaskArray.readTask(input, KEYWORD_DEADLINE);

            } else if (input.contains(KEYWORD_EVENT)) {
                TaskArray.readTask(input, KEYWORD_EVENT);
            } else {
                PrintMethod.invalidCommand();

            }
            input = inputScanner.nextLine();
        }
        PrintMethod.exitCommand();

    }

    public static void printContents() {
        String directory = "src";
        //Path directoryPath=Paths.get(directory);
        File directory1 = new File(directory);
        String absolutePath = directory1.getAbsolutePath().replace("src", "");
        String filePath = "data.txt";
        File f = new File(filePath);
        try {
            Scanner readFile = new Scanner(f);
            while (readFile.hasNext()) {
                System.out.println(readFile.nextLine());
            }
            PrintMethod.printLines();
            System.out.println("Do you want to keep the contents of the file?");
            PrintMethod.printLines();
            String decision=inputScanner.nextLine();
            if (decision.equalsIgnoreCase("yes")) {
                PrintMethod.printLines();
                System.out.println("Alright, the tasks will not be deleted!");
            } else {
                TaskArray.clearFile();
            }
            //PrintMethod.printLines();
        } catch (FileNotFoundException e) {
            System.out.println("You do not have a data.txt file to store yours tasks!");
            System.out.println("A new data.txt file will be created in this path " + absolutePath);
            TaskArray.createFile();
        } catch (InvalidPathException i) {
            System.out.println("The path does not exist!");
        }

    }

}
