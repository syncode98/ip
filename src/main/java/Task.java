public class Task {
    private  String[] tasks;
    private static int taskIndex=0;

    public Task(String[] tasks, int taskNumber) {
        this.tasks = tasks;
        taskIndex=taskNumber;
    }
    public Task(){
        this.tasks=new String[100];
        taskIndex=0;
    }

    public String[] getTasks() {
        return tasks;
    }

    public void setTasks(String[] tasks) {
        this.tasks = tasks;
    }

    public static int getTaskIndex() {
        return taskIndex;
    }

    public static void setTaskIndex(int taskIndex) {
        Task.taskIndex = taskIndex;
    }

    public void addTask(String task){
        this.tasks[taskIndex]=task;
        taskIndex++;
        System.out.println("added: "+task);
    }

    public void printTask(){
        int index=0;
        for (int i=0;i<taskIndex;i++) {
            index++;
            System.out.println((index )+ ". "+this.tasks[i]);
        }
    }
}
