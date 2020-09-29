package duke.command;

import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand(){

    }

    @Override
    public void execute() {
        Ui.printAllTasks(TaskList.taskArrayList);
    }
}
