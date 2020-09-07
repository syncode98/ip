import java.util.Scanner;

public class Duke {
/**Declaring the array for the project*/
    public static final int MAX_SIZE = 100;
    private static Task[] tasks=new Task[MAX_SIZE];

    /**Scanner object initialised so that it can be used throughout Duke class*/
    private static Scanner inputScanner= new Scanner(System.in);

    /**defining the inputs beforehand*/
    private static String INPUT_BYE ="bye";
    private static String INPUT_LIST ="list";
    private static String INPUT_DONE ="done";
    private static String INPUT_TODO ="todo";
    private static String INPUT_DEADLINE ="deadline";
    private static String INPUT_EVENT ="event";


    public static void main(String[] args) {
        initialiseMike();
    }
    public static void initialiseMike(){
        Task.printLines();
        System.out.println("Hello! I'm Mike!\nEnter your name:");
        Task.printLines();
        String nameOfUser= inputScanner.nextLine();
        Task.printLines();
        System.out.println("Alright " + nameOfUser + " , What can I do for you?");
        Task.printLines();
        String command=inputScanner.nextLine();
        readInput(command);

    }
    public static void readInput(String command) {
        while (!command.equals(INPUT_BYE)) {

            if (command.equals(INPUT_LIST)) {
                printAllTasks(tasks);

            }else if (command.contains(INPUT_DONE)) {
                command=command.replace(INPUT_DONE,"").strip();
                int indexOfTask=Integer.parseInt(command);
                if (indexOfTask<=Task.getNumberOfTasks()) {
                    indexOfTask=indexOfTask-1;
                    tasks[indexOfTask].completeTask();
                } else{
                    Task.printLines();
                    System.out.println("The task does not exist");
                    Task.printLines();
                }


            }else if (command.contains(INPUT_TODO)) {
                String task=command.replace("todo","");
                Todo todo=new Todo(task);
                addToTasks(todo);

            } else if (command.contains(INPUT_DEADLINE)) {
                String[] words=command.split("/");
                String task=words[0].replace(INPUT_DEADLINE,"");
                String givenDeadline = words[1].replaceFirst(" ", ":");
                Deadline deadline =new Deadline(task,givenDeadline);
                addToTasks(deadline);

            } else if (command.contains(INPUT_EVENT)) {
                String[] words= command.split("/");
                String task = words[0].replace(INPUT_EVENT, "");
                String givenDeadline = words[1].replaceFirst(" ", ":");
                Event event = new Event(task, givenDeadline);
                addToTasks(event);

            } else {
                System.out.println("Invalid command");
            }
            command=inputScanner.nextLine();
        }
        Task.printLines();
        System.out.println("Bye.Hope to see you again soon!");
        Task.printLines();

    }
    public static void addToTasks(Task task) {
        tasks[Task.getNumberOfTasks()]=task;
        task.addTask();
        System.out.println("Now you have " +Task.getNumberOfTasks() +" tasks in the list.");
        Task.printLines();
    }

    public static void printAllTasks(Task[] tasks) {
        Task.printLines();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i<Task.getNumberOfTasks(); i++) {
            int indexOfTask=i+1;
            System.out.println(indexOfTask+"."+tasks[i].toString());
        }
        Task.printLines();
    }
}
