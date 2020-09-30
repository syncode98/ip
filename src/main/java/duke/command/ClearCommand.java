package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ClearCommand extends Command {
    @Override
    public void execute() {
        Storage.clearFile();
        Storage.createFile();
        TaskList.removeAllTasks();
        Ui.printLines();
        Ui.printClearTasks();
        Ui.printLines();
    }
}
