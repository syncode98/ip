package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.IllegalNumberException;
import duke.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {

    public DeleteCommand(String input) {
        this.command = input;
        try {
            Ui.printLines();
            int indexOfTask = findTaskNumber(command);
            Task current_task = TaskList.taskArrayList.get(indexOfTask);

            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + current_task.toString());
            TaskList.removeTask(current_task);
            System.out.println("Now you have " + TaskList.taskArrayList.size() + " tasks in the list.");
            Storage.updateFile(current_task.toString(), "delete");

        } catch (NumberFormatException n) {
            //Alerts user upon not entering a task number
            Ui.printInvalidDelete();

        } catch (IllegalNumberException i) {
            //Alerts user upon entering a task that has not been initialised
            Ui.printInvalidTask();

        } catch (IndexOutOfBoundsException o) {
            //Alerts user upon entering a number that is out of range
            Ui.printWithinRangeTask();

        } catch (IOException e) {
            System.out.println("Not able to update file!");
        }
        Ui.printLines();

    }


}
