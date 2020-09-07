import java.util.Scanner;

public class Duke {

    private static final int MAX_SIZE = 100;
    private static Task[] tasks = new Task[MAX_SIZE];
    private static Scanner inputScanner = new Scanner(System.in);

    private static String INPUT_BYE = "bye";
    private static String INPUT_LIST = "list";
    private static String INPUT_DONE = "done";
    private static String INPUT_TODO = "todo";
    private static String INPUT_DEADLINE = "deadline";
    private static String INPUT_EVENT = "event";


    public static void main(String[] args) {
        initialiseMike();
    }

    public static void initialiseMike() {
        Task.printLines();
        System.out.println("Hello! I'm Mike!\nEnter your name:");
        Task.printLines();
        String nameOfUser = inputScanner.nextLine();
        Task.printLines();
        System.out.println("Alright " + nameOfUser + " , What can I do for you?");
        Task.printLines();
        String command = inputScanner.nextLine();
        readInput(command);

    }

    public static void readInput(String command) {
        while (!command.equals(INPUT_BYE)) {

            if (command.equals(INPUT_LIST)) {
                printAllTasks(tasks);

            } else if (command.contains(INPUT_DONE)) {
                readDone(command);

            } else if (command.contains(INPUT_TODO)) {
                readTodo(command);

            } else if (command.contains(INPUT_DEADLINE)) {
                String[] words = command.split("/");
                String task = words[0].replace(INPUT_DEADLINE, "");
                String givenDeadline = words[1].replaceFirst(" ", ":");
                Deadline deadline = new Deadline(task, givenDeadline);
                addToTasks(deadline);

            } else if (command.contains(INPUT_EVENT)) {
                String[] words = command.split("/");
                String task = words[0].replace(INPUT_EVENT, "");
                String givenDeadline = words[1].replaceFirst(" ", ":");
                Event event = new Event(task, givenDeadline);
                addToTasks(event);

            }
            command = inputScanner.nextLine();
        }
        Task.printLines();
        System.out.println("Bye.Hope to see you again soon!");
        Task.printLines();

    }

    public static void readTodo(String command) {
        Task.printLines();
        try {
            String task = returnTask(command);
            Todo todo = new Todo(task);
            addToTasks(todo);
        } catch (IllegalDescriptionException d) {
            char sadFace = '\u2639';
            System.out.println(sadFace + " OOPS!!! The description of a todo cannot be empty.");
        }
        Task.printLines();

    }

    public static String returnTask(String command) throws IllegalDescriptionException {
        String task = command.replace(INPUT_TODO, "").strip();
        if (task.equals("")) {
            throw new IllegalDescriptionException();
        }
        return task;
    }

    public static void readDone(String command) {
        Task.printLines();
        try {
            command = command.replace(INPUT_DONE, "").strip();
            int indexOfTask = findTaskNumber(command);
            tasks[indexOfTask].completeTask();

        } catch (NumberFormatException n) { //If user inputs command done without any number
            printInvalidDone();

        } catch (IllegalNumberException i) { //
            printInvalidTask();

        } catch (ArrayIndexOutOfBoundsException a) {
            printWithinRangeTask();

        }
        Task.printLines();
    }

    public static int findTaskNumber(String command) throws IllegalNumberException {

        int indexOfTask;
        indexOfTask = Integer.parseInt(command);
        if (indexOfTask > Task.getNumberOfTasks() && indexOfTask <= MAX_SIZE) {
            throw new IllegalNumberException();
        }
        return indexOfTask - 1;

    }

    public static void addToTasks(Task task) {
        tasks[Task.getNumberOfTasks()] = task;
        task.addTask();
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
        Task.printLines();
    }

    public static void printAllTasks(Task[] tasks) {
        Task.printLines();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            int indexOfTask = i + 1;
            System.out.println(indexOfTask + "." + tasks[i].toString());
        }
        Task.printLines();
    }

    public static void printInvalidDone() {
        char sadFace = '\u2639';
        System.out.println(sadFace + " Command done cannot be empty!");
    }

    public static void printInvalidTask() {
        char sadFace = '\u2639';
        System.out.println(sadFace + " Task does not exist in list!");
    }

    public static void printWithinRangeTask() {
        char sadFace = '\u2639';
        System.out.println(sadFace + " Task number must be in between 1 and 100");
    }


}
