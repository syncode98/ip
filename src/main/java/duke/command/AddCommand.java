package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.PatternSyntaxException;


public class AddCommand extends Command {

    public String keyword;

    public static String DELIMITER_EMPTY_STRING = "";
    public static String DELIMITER_SLASH = "/";
    public static String DELIMITER_SEMI_COLON = ":";
    public static String DELIMITER_CHARACTER = " ";

    public AddCommand(String input) {
        this.command = input;
        String[] words = input.split(DELIMITER_CHARACTER);
        this.keyword = words[0];

    }

    /** Reads the command and adds the corresponding tasks to the taskArrayList. */
    public void execute() {

        try {
            Ui.printLines();
            Task currentTask = returnTask(command, keyword);
            TaskList.addToTasks(currentTask, true);

        } catch (IllegalEmptyDescriptionException i) {
            Ui.printEmptyDescription(keyword);

        } catch (ArrayIndexOutOfBoundsException | IllegalPrepositionWithoutDate a) {
            Ui.printEmptyDate(keyword);
        } catch (IOException e) {
            System.out.println("Something went wrong" + e.getMessage());
        } catch (InvalidCommand invalidCommand) {
            Ui.invalidCommand();
        } catch (DateTimeParseException d) {
            Ui.printEmptyDate(keyword);
        } catch (InvalidPreposition i) {
            Ui.printIncorrectPreposition(this.keyword);
        } catch (IllegalSlashException i) {
            Ui.printSlash();
        }
        Ui.printLines();
    }


    /**
     * Returns a task object that has been instantiated according to its type of task
     *
     * @param input   Given by the user
     * @param keyword The type of the task
     * @return A task object
     * @throws IllegalEmptyDescriptionException If the user does not enter a valid description
     * @throws IllegalPrepositionWithoutDate    If the user does not follow the
     *                                          required format for deadline and events
     */
    public static Task returnTask(String input, String keyword) throws
            IllegalEmptyDescriptionException, IllegalPrepositionWithoutDate, InvalidCommand, NullPointerException,
            InvalidPreposition, IllegalSlashException {
        Task currentTask = null;
        switch (keyword) {
        case "event":
            //fallthrough
        case "deadline":
            currentTask = returnEventDeadline(keyword, input);
            break;

        case "todo":
            String task = returnDescriptionOfTask(input, keyword);
            currentTask = new Todo(task);
            break;
        default:
            throw new InvalidCommand();
        }
        return currentTask;

    }


    /**
     * Returns the preposition and the deadline of the given task.
     *
     * @param typeOfTask The type of the task in the command.
     * @param command    The command given by the user.
     * @return The task and the deadline for the task as a String array
     * @throws IllegalEmptyDescriptionException If the task is not given by the user
     * @throws IllegalPrepositionWithoutDate    If the user does not enter the date after the preposition
     */
    public static Task returnEventDeadline(String typeOfTask, String command) throws IllegalEmptyDescriptionException,
            IllegalPrepositionWithoutDate, InvalidPreposition, IllegalSlashException {
        Task task = null;

        if (!command.contains(DELIMITER_SLASH)) {
            throw new IllegalSlashException();
        }
        String[] words = command.split(DELIMITER_SLASH);
        String taskDescription = returnDescriptionOfTask(words[0], typeOfTask);
        words[0] = taskDescription;
        String deadlineForTask = words[1].strip();


        //splits the deadline into its preposition and the date
        String[] descriptorsForDate = deadlineForTask.substring(2).strip().split(DELIMITER_CHARACTER);
        String preposition = deadlineForTask.substring(0, 2);

        if ((typeOfTask.equals("event") && !preposition.equals("at")) |
                (typeOfTask.equals("deadline") && !preposition.equals("by"))) {
            throw new InvalidPreposition();
        }


        LocalDate date = null;
        LocalTime startTime = null;
        LocalTime endTime = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MM-yyyy");
        if (descriptorsForDate.length > 0) {
            try {
                date = LocalDate.parse(descriptorsForDate[0], format);
            } catch (DateTimeParseException d) {
                date = LocalDate.parse(descriptorsForDate[0]);
            }
        }
        if (descriptorsForDate.length > 1) {
            startTime = LocalTime.parse(descriptorsForDate[1]);
            if (descriptorsForDate.length > 2) {
                endTime = LocalTime.parse(descriptorsForDate[3]);
            }
        }

        if (typeOfTask.equals("deadline")) {
            task = new Deadline(taskDescription, date, startTime);
        } else {
            task = new Event(taskDescription, date, startTime, endTime);
        }


        if (deadlineForTask.equals(DELIMITER_EMPTY_STRING) | deadlineForTask.equals(DELIMITER_CHARACTER)) {
            throw new IllegalPrepositionWithoutDate();
        }

        return task;
    }


    /**
     * Returns the description of the task.
     *
     * @param command    The command given by the user.
     * @param typeOfTask The type of the task.
     * @return The description of the task
     * @throws IllegalEmptyDescriptionException If the task is not given by the user.
     */
    public static String returnDescriptionOfTask(String command, String typeOfTask) throws
            IllegalEmptyDescriptionException {
        String task = command.replace(typeOfTask, DELIMITER_EMPTY_STRING).strip();
        if (task.equals("")) {
            throw new IllegalEmptyDescriptionException();
        }
        return task;
    }

}
