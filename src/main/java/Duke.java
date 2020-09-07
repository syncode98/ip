import java.util.Scanner;

public class Duke {

    private static final int MAX_SIZE = 100;
    private static Task[] tasks = new Task[MAX_SIZE];
    private static Scanner inputScanner = new Scanner(System.in);


    public static String INPUT_BYE = "bye";
    public static String INPUT_LIST = "list";
    public static String INPUT_DONE = "done";
    public static String INPUT_TODO = "todo";
    public static String INPUT_DEADLINE = "deadline";
    public static String INPUT_EVENT = "event";
    public static String EMPTY = "";
    public static String SLASH = "/";
    public static String SEMI_COLON = ":";
    public static String SPACE = " ";


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
                readDeadline(command);

            } else if (command.contains(INPUT_EVENT)) {
                readEvent(command);

            }
            command = inputScanner.nextLine();
        }
        PrintMethod.exitCommand();


    }

    public static void readEvent(String command) {

        try {
            PrintMethod.printLines();
            String[] words = parseString(INPUT_EVENT, command);
            Event event = new Event(words[0], words[1]);
            addToTasks(event);

        } catch (IllegalEmptyDescriptionException i) {
            PrintMethod.printEmptyDescription(INPUT_EVENT);

        } catch (ArrayIndexOutOfBoundsException a) {
            PrintMethod.printEmptyDate(INPUT_EVENT);

        } catch (IllegalPrepositionWithoutDate p) {
            PrintMethod.printEmptyDate(INPUT_EVENT);
        }
        PrintMethod.printLines();
    }

    public static void readDeadline(String command) {
        try {
            PrintMethod.printLines();
            String[] words = parseString(INPUT_DEADLINE, command);
            Deadline deadline = new Deadline(words[0], words[1]);
            addToTasks(deadline);

        } catch (IllegalEmptyDescriptionException i) {
            PrintMethod.printEmptyDescription(INPUT_DEADLINE);
        } catch (ArrayIndexOutOfBoundsException a) {
            PrintMethod.printEmptyDate(INPUT_DEADLINE);
        } catch (IllegalPrepositionWithoutDate p) {
            PrintMethod.printEmptyDate(INPUT_DEADLINE);
        }
        PrintMethod.printLines();
    }

    public static void readTodo(String command) {
        PrintMethod.printLines();
        try {
            String task = returnTask(command, INPUT_TODO);
            Todo todo = new Todo(task);
            addToTasks(todo);
        } catch (IllegalEmptyDescriptionException d) {
            PrintMethod.printEmptyDescription(INPUT_TODO);
        }
        PrintMethod.printLines();

    }

    public static String returnTask(String command, String typeOfTask) throws IllegalEmptyDescriptionException {
        String task = command.replace(typeOfTask, EMPTY).strip();
        if (task.equals("")) {
            throw new IllegalEmptyDescriptionException();
        }
        return task;
    }

    public static void readDone(String command) {
        PrintMethod.printLines();
        try {
            command = command.replace(INPUT_DONE, EMPTY).strip();
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
    }

    public static String[] parseString(String typeOfTask, String command) throws IllegalEmptyDescriptionException,
            IllegalPrepositionWithoutDate {

        String[] words = command.split(SLASH);
        String task = returnTask(words[0], typeOfTask);
        words[0] = task;
        String deadlineForTask = words[1].strip();


        //splits the deadline into its preposition and the date
        String[] descriptorsForDate = deadlineForTask.split(SPACE);
        String preposition = descriptorsForDate[0].strip();
        deadlineForTask = deadlineForTask.replace(preposition, EMPTY).strip();

        if (deadlineForTask.equals(EMPTY)) {
            throw new IllegalPrepositionWithoutDate();
        }

        words[1] = preposition + SEMI_COLON + deadlineForTask;
        return words;

    }


}
