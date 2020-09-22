package duke;

public class ListCommand extends Command {
    public ListCommand(){
        Ui.printAllTasks(TaskList.taskArrayList);
    }
}
