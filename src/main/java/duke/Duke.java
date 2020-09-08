package duke;

import java.util.Scanner;

public class Duke {

    public static Scanner inputScanner = new Scanner(System.in);

    public static String KEYWORD_BYE = "bye";
    public static String KEYWORD_LIST = "list";
    public static String KEYWORD_DONE = "done";
    public static String KEYWORD_TODO = "todo";
    public static String KEYWORD_DEADLINE = "deadline";
    public static String KEYWORD_EVENT = "event";


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
        String input = inputScanner.nextLine();
        while (!input.equals(KEYWORD_BYE)) {

            if (input.equals(KEYWORD_LIST)) {
                TaskArray.printTasks();

            } else if (input.contains(KEYWORD_DONE)) {
                TaskArray.readDone(input);

            } else if (input.contains(KEYWORD_TODO)) {
                TaskArray.readTodo(input);

            } else if (input.contains(KEYWORD_DEADLINE)) {
                TaskArray.readDeadline(input);

            } else if (input.contains(KEYWORD_EVENT)) {
                TaskArray.readEvent(input);

            } else {
                PrintMethod.invalidCommand();

            }
            input = inputScanner.nextLine();
        }
        PrintMethod.exitCommand();

    }

}
