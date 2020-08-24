public class Task {
    private String[] tasks;
    private static int taskIndex=0;
    private int[] completion;

    public Task(String[] tasks, int taskNumber, int[] completion) {
        this.tasks = tasks;
        taskIndex=taskNumber;
        this.completion = completion;
    }
    public Task(){
        this.tasks=new String[100];
        this.completion=new int[100];
        for (int i=0;i <100; i++) {
            this.completion[i]=-1;
        }
        taskIndex=0;
    }

    public int[] getCompletion() {
        return completion;
    }

    public void setCompletion(int[] completion) {
        this.completion = completion;
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
        this.completion[taskIndex]=0;
        taskIndex++;
        System.out.println("added: "+task);
    }

    public void printTasks(){
        int index=0;
        char complete='\u2713'; //code for tick
        char incomplete='\u2A09'; //code for cross
        System.out.println("Here are the tasks in your list:");
        for (int i=0;i<taskIndex;i++) {
            index++;
            char symbol=(this.completion[i]==1) ? complete:incomplete;
            System.out.println((index)+ ".["+ symbol +"]"+this.tasks[i]);
        }
    }
/*
 *The completion int array stores dictates if a task has been
 *completed or not. -1 is assigned to tasks that have not been created yet.
 *0 is assigned to tasks that have been created but are not complete
 *1 is for tasks that have been completed
 *
 */
    public void completeTasks(int taskNumber){
        taskNumber--;
        char complete='\u2713';
        if (this.completion[taskNumber] == 0 ) {
            this.completion[taskNumber]=1;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("["+ complete +"]"+this.tasks[taskNumber]);
        }
        else if (this.completion[taskNumber] == 1 ) {
            System.out.println("Task already completed");
        }
        else {
            System.out.println("Task does not exist ");
        }
    }
}
