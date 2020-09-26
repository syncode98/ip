package duke.command;

import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand(){
        Ui.printAllTasks(TaskList.taskArrayList);
    }
}
