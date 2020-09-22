package duke;

import duke.exception.IllegalNumberException;
import duke.task.Task;

import java.io.IOException;

public class DoneCommand extends Command {

    public DoneCommand(String input) {
        this.command=input;
        Ui.printLines();
        try {

            int indexOfTask = findTaskNumber(command);
            Task current_task = TaskList.taskArrayList.get(indexOfTask);
            current_task.completeTask();
            Storage.updateFile(current_task.toString(),"done");

        } catch (NumberFormatException n) {
            //Alerts user upon not entering a task number
            Ui.printInvalidDone();

        } catch (IllegalNumberException i) {
            //Alerts user upon entering a task that has not been initialised
            Ui.printInvalidTask();

        } catch (IndexOutOfBoundsException o) {
            //Alerts user upon entering a number that is out of range
            Ui.printWithinRangeTask();

        } catch (IOException e) {
            System.out.println("Error in file!");
        }
        Ui.printLines();

    }

}
