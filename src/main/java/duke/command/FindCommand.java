package duke.command;


import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {

    public FindCommand(String input) {
        this.command = input;
        Ui.printLines();
        System.out.println("Here are the matching tasks in your list:");
        int index = 1;
        for (Task task : TaskList.taskArrayList) {
            String task_description = task.toString().substring(6);
            if (task_description.contains(command)) {
                System.out.println(index + ". " + task);
                index++;
            }

        }
        Ui.printLines();

    }
}


