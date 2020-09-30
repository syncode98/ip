package duke;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

import static duke.Storage.writeToFile;

public class TaskList {
    public static final int MAX_SIZE = 100;
    public static ArrayList<Task> taskArrayList = new ArrayList<>();

    public TaskList() {
        taskArrayList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> dataTexts) {
        taskArrayList = dataTexts;

    }

    public static int getSize() {
        return taskArrayList.size();
    }

    /**
     * Adds the task to the taskArrayList and updates the data.txt file concurrently,
     * if needed.
     *
     * @param task      The task that has been instantiated.
     * @param fileWrite To decide if the data.txt file needs to be updated.
     * @throws IOException If the file is not able to be updated.
     */
    public static void addToTasks(Task task, boolean fileWrite) throws IOException {
        try {
            taskArrayList.add(task);
            task.addTask();
            if (fileWrite) {
                writeToFile(task.toString());
                System.out.println("Now you have " + getSize() + " tasks in the list.");
            }
        } catch (NullPointerException n) {
            Ui.printInvalidTask();
        }
    }


    public static void printTasks() {
        Ui.printAllTasks(taskArrayList);
    }

    public static void removeTask(Task task) {
        taskArrayList.remove(task);
    }

    public static void removeAllTasks(){
        taskArrayList.clear();
    }
}




