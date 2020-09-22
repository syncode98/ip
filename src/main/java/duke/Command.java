package duke;

import duke.exception.IllegalNumberException;

public abstract class Command {
    public String command;

    public static int findTaskNumber(String command) throws IllegalNumberException {

        int indexOfTask;
        indexOfTask = Integer.parseInt(command);
        if (indexOfTask > TaskList.taskArrayList.size() && indexOfTask <= TaskList.MAX_SIZE) {
            throw new IllegalNumberException();
        }
        return indexOfTask - 1;

    }

}
