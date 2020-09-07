import java.util.Scanner;

public class PrintMethod {
    private static final char sadFace = '\u2639';
    public static void printLines() {
        System.out.println("-------------------------------");
    }
    public static void exitCommand() {
        printLines();
        System.out.println("Bye.Hope to see you again soon!");
        printLines();
    }

    public static void initialiseMike() {
        Scanner inputScanner = new Scanner(System.in);
        printLines();
        System.out.println("Hello! I'm Mike!\nEnter your name:");
        printLines();
        String nameOfUser = inputScanner.nextLine();
        printLines();
        System.out.println("Alright " + nameOfUser + " , What can I do for you?");
        printLines();
    }

    public static void printAllTasks(Task[] tasks) {
        printLines();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            int indexOfTask = i + 1;
            System.out.println(indexOfTask + "." + tasks[i].toString());
        }
        printLines();
    }

    public static void printInvalidDone() {
        System.out.println(sadFace + " Command done cannot be empty!");
    }

    public static void printInvalidTask() {
        System.out.println(sadFace + " Task does not exist in list!");
    }

    public static void printWithinRangeTask() {
        System.out.println(sadFace + " Task number must be in between 1 and 100");
    }


}

