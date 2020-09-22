package duke;

import duke.exception.IllegalEmptyDescriptionException;
import duke.exception.IllegalPrepositionWithoutDate;
import duke.exception.InvalidCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;

public class AddCommand extends Command {

    public String keyword;

    public static String DELIMITER_EMPTY_STRING = "";
    public static String DELIMITER_SLASH = "/";
    public static String DELIMITER_SEMI_COLON = ":";
    public static String DELIMITER_CHARACTER = " ";

    public AddCommand(String input){
        this.command=input;
        String[] words=input.split(DELIMITER_CHARACTER);
        this.keyword=words[0];
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
        Task currentTask;
        switch (keyword) {
        case "event":
            String[] eventDetails = parseString(keyword, input);
            currentTask = new Event(eventDetails[0], eventDetails[1]);
            break;
        case "deadline":
            String[] deadlineDetails = parseString(keyword, input);
            currentTask = new Deadline(deadlineDetails[0], deadlineDetails[1]);
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
    public static String[] parseString(String typeOfTask, String command) throws IllegalEmptyDescriptionException,
            IllegalPrepositionWithoutDate {

        String[] words = command.split(DELIMITER_SLASH);
        String task = returnDescriptionOfTask(words[0], typeOfTask);
        words[0] = task;
        String deadlineForTask = words[1].strip();


        //splits the deadline into its preposition and the date
        String[] descriptorsForDate = deadlineForTask.split(DELIMITER_CHARACTER);
        String preposition = descriptorsForDate[0].strip();
        deadlineForTask = deadlineForTask.replace(preposition, DELIMITER_EMPTY_STRING).strip();

        if (deadlineForTask.equals(DELIMITER_EMPTY_STRING)) {
            throw new IllegalPrepositionWithoutDate();
        }

        words[1] = preposition + DELIMITER_SEMI_COLON + deadlineForTask;
        return words;

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
