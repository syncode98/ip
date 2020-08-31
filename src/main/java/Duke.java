import java.util.Scanner;

public class Duke {

    public static final int MAX_SIZE = 100;
    private static Task[] tasks=new Task[MAX_SIZE];
    public static Scanner inputScanner= new Scanner(System.in);

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
        while (!command.equals("bye")) {

            if (command.equals("list")) {
                printAllTasks(tasks);

            }else if (command.contains("done")) {
                command=command.replace("done","").strip();
                int indexOfTask=Integer.parseInt(command)-1;
                tasks[indexOfTask].completeTask();

            }else if (command.contains("todo")) {
                String task=command.replace("todo","");
                Todo todo=new Todo(task);
                addToTasks(todo);

            } else if (command.contains("deadline")) {
                String[] words=command.split("/");
                String task=words[0].replace("deadline","");
                String givenDeadline = words[1].replaceFirst(" ", ":");
                Deadline deadline =new Deadline(task,givenDeadline);
                addToTasks(deadline);

            } else if (command.contains("event")) {
                String[] words= command.split("/");
                String task = words[0].replace("event", "");
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
