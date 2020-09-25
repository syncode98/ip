package duke;

import duke.exception.IllegalEmptyDescriptionException;
import duke.exception.IllegalPrepositionWithoutDate;
import duke.exception.InvalidCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;


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
        readTask();
    }

    public void readTask() {

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
            IllegalEmptyDescriptionException, IllegalPrepositionWithoutDate, InvalidCommand {
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
            IllegalPrepositionWithoutDate {
        Task task = null;
        try {

            String[] words = command.split(DELIMITER_SLASH);
            String taskDescription = returnDescriptionOfTask(words[0], typeOfTask);
            words[0] = taskDescription;
            String deadlineForTask = words[1].strip();


            //splits the deadline into its preposition and the date
            String[] descriptorsForDate = deadlineForTask.substring(2).strip().split(DELIMITER_CHARACTER);
            String preposition = deadlineForTask.substring(0, 2);


            LocalDate date = null;
            LocalTime startTime = null;
            LocalTime endTime = null;
            if (descriptorsForDate.length >0) {
                date = LocalDate.parse(descriptorsForDate[0]);
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


            if (deadlineForTask.equals(DELIMITER_EMPTY_STRING)) {
                throw new IllegalPrepositionWithoutDate();
            }


        } catch (IllegalEmptyDescriptionException | IllegalPrepositionWithoutDate e) {
            Ui.printInvalidTask();
        } catch (DateTimeParseException d) {
            Ui.printEmptyDate(typeOfTask);
        }
        return task;
    }


    public static String returnDescriptionOfTask(String command, String typeOfTask) throws
            IllegalEmptyDescriptionException {
        String task = command.replace(typeOfTask, DELIMITER_EMPTY_STRING).strip();
        if (task.equals("")) {
            throw new IllegalEmptyDescriptionException();
        }
        return task;
    }

}
