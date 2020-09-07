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
    private static String EMPTY="";


    public static void main(String[] args) {

        PrintMethod.initialiseMike();
        readInput();
    }

    public static void readInput() {

        String command = inputScanner.nextLine();
        while (!command.equals(INPUT_BYE)) {

            if (command.equals(INPUT_LIST)) {
                PrintMethod.printAllTasks(tasks);

            } else if (command.contains(INPUT_DONE)) {
                readDone(command);

            } else if (command.contains(INPUT_TODO)) {
                readTodo(command);

            } else if (command.contains(INPUT_DEADLINE)) {
                String[] words = command.split("/");
                String task = words[0].replace(INPUT_DEADLINE, EMPTY);
                String givenDeadline = words[1].replaceFirst(" ", ":");
                Deadline deadline = new Deadline(task, givenDeadline);
                addToTasks(deadline);

            } else if (command.contains(INPUT_EVENT)) {
                String[] words = command.split("/");
                String task = words[0].replace(INPUT_EVENT, EMPTY);
                String givenDeadline = words[1].replaceFirst(" ", ":");
                Event event = new Event(task, givenDeadline);
                addToTasks(event);

            }
            command = inputScanner.nextLine();
        }
        PrintMethod.printLines();


    }

    public static void readTodo(String command) {
        PrintMethod.printLines();
        try {
            String task = returnTask(command);
            Todo todo = new Todo(task);
            addToTasks(todo);
        } catch (IllegalDescriptionException d) {
            char sadFace = '\u2639';
            System.out.println(sadFace + " OOPS!!! The description of a todo cannot be empty.");
        }
        PrintMethod.printLines();

    }

    public static String returnTask(String command) throws IllegalDescriptionException {
        String task = command.replace(INPUT_TODO, "").strip();
        if (task.equals("")) {
            throw new IllegalDescriptionException();
        }
        return task;
    }

    public static void readDone(String command) {
        PrintMethod.printLines();
        try {
            command = command.replace(INPUT_DONE, "").strip();
            int indexOfTask = findTaskNumber(command);
            tasks[indexOfTask].completeTask();

        } catch (NumberFormatException n) {
            //Alerts user upon not entering a task number
            PrintMethod.printInvalidDone();

        } catch (IllegalNumberException i) {
            //Alerts user upon entering a task that has not been initialised
            PrintMethod.printInvalidTask();

        } catch (ArrayIndexOutOfBoundsException a) {
            //Alerts user upon entering a tasknumber that is out of range
            PrintMethod.printWithinRangeTask();

        }
        PrintMethod.printLines();
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
        PrintMethod.printLines();
    }


}
