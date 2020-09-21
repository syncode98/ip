package duke;

import duke.exception.IllegalEmptyDescriptionException;
import duke.exception.IllegalPrepositionWithoutDate;
import duke.task.TaskList;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static void printContents() {

        try {
            String filePath = "data/data.txt";
            TaskList.checkDirectoryStatus();
            TaskList.checkFileStatus(filePath);
            ArrayList<String> fileLines = new ArrayList<>();
            TaskList.fileData(fileLines);

            if (!fileLines.contains("You do not have any tasks!")) {
                for (String line : fileLines) {
                    System.out.println(line);
                }

                Ui.printLines();
                System.out.println("Do you want to keep the contents of the file?");
                Ui.printLines();

                Scanner inputScanner=new Scanner(System.in);
                String decision = inputScanner.nextLine();
                Ui.printLines();
                if (decision.equalsIgnoreCase("yes")) {

                    for (String line : fileLines) {
                        //These two lines in the data.txt file are not tasks
                        if (line.equals("Here are the tasks!") || line.equals("You do not have any tasks!")) {
                            continue;
                        } else {
                            Parser.nameOfTask(line);
                        }
                    }

                } else {
                    TaskList.clearFile();
                    TaskList.createFile();
                }
            }
        } catch (InvalidPathException i) {
            System.out.println("The path does not exist!");
        } catch (NullPointerException n) {

            //If the user already has a data.txt file in the location but it is empty
            try {
                String noTasks = "You do not have any tasks!";
                TaskList.writeToFile(noTasks);
                System.out.println(noTasks);
            } catch (IOException e) {
                System.out.println("There is an error with the file!");
            }
        } catch (IllegalEmptyDescriptionException e) {
            System.out.println("The description is empty!!");
        } catch (IllegalPrepositionWithoutDate illegalPrepositionWithoutDate) {
            System.out.println("Add an appropriate data");
        }
    }
}
