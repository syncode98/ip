package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidTask;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindCommand extends duke.command.Command {

    public FindCommand(String input) {
        this.command = input;

    }

    public void execute() {

        try {
            checkTask();
            Ui.printLines();
            Ui.printMatchingTasks();
            int index = 1;

            for (Task task : TaskList.taskArrayList) {
                String task_description = task.toString();

                if (task_description.contains(command)) {
                    System.out.println(index + ". " + task);
                    index++;
                }

            }
            Ui.printLines();
        } catch (InvalidTask invalidTask) {
            Ui.printLines();
            Ui.printInvalidTask();
            Ui.printLines();
        }
    }


    public void checkTask() throws InvalidTask {

        int incidenceOfTask = (int) TaskList.taskArrayList.stream()
                .filter(task -> task.toString().contains(command))
                .count();
        if (incidenceOfTask == 0) {
            throw new InvalidTask();
        }

    }

}