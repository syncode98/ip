public class Task {
    private String task;
    private boolean isDone;
    private static int numberOfTasks = 0;

    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    public Task() {
        this(null, false);
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean getDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public static void setNumberOfTasks(int numberOfTasks) {
        Task.numberOfTasks = numberOfTasks;
    }

    public static void printLines() {
        System.out.println("-------------------------------");
    }

    public void addTask() {
        printLines();
        System.out.println("Got it.I've added this task:\n " + this.toString());
        numberOfTasks++;
    }

    public void completeTask() {
        printLines();
        if (this.isDone) {
            System.out.println("The task has already been completed");
        } else {
            this.isDone = true;
            char complete = '\u2713';
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.toString());
        }

        printLines();
    }

    @Override
    public String toString() {
        char complete = '\u2713'; //unicode values for tick
        char incomplete = '\u2A09'; //unicode values for cross
        char symbol = (this.isDone) ? complete : incomplete;
        return "[" + symbol + "]" + this.task;
    }
}

