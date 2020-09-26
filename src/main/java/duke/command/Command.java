package duke.command;

import duke.TaskList;
import duke.exception.IllegalNumberException;

public abstract class Command {
    public String command;

    /**
     * Checks if the user is finding for a task that is
     * present in the taskArrayList.
     *
     * @param command The number entered by the user
     * @return The index of the task.
     * @throws IllegalNumberException If the number represents a task that
     *                                is not present in the taskArrayList
     */
    public static int findTaskNumber(String command) throws IllegalNumberException {

        int indexOfTask;
        indexOfTask = Integer.parseInt(command);
        if (indexOfTask > TaskList.taskArrayList.size() && indexOfTask <= TaskList.MAX_SIZE) {
            throw new IllegalNumberException();
        }
        return indexOfTask - 1;

    }



}
