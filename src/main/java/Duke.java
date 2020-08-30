import java.util.Scanner;

public class Duke{
    public static final int MAX_SIZE = 100;

    public static void main(String[] args) {
        Task[] tasks = new Task[MAX_SIZE];
        for (int i=0; i<MAX_SIZE; i++) {
            tasks[i]=new Task();
        }
        initialiseMike();
        readInput(tasks);
    }

    public static void printLines(){
        System.out.println("-------------------------------");
    }

    public static void initialiseMike(){
        Scanner inputScanner= new Scanner(System.in);
        printLines();
        System.out.println("Hello! I'm Mike!\nEnter your name:");
        printLines();
        String nameOfUser= inputScanner.nextLine();
        printLines();
        System.out.println("Alright " +nameOfUser+ " , What can I do for you?");
        printLines();
    }

    public static void readInput(Task[] tasks) {
        Scanner inputScanner= new Scanner(System.in);
        String command;
        command=inputScanner.nextLine();
        while (!command.equals("bye")) {
            String[] words =command.split(" ");
            if (command.equals("list")) {
                printLines();
                for (int i = 0; i< Task.getNumberOfTasks(); i++) {
                    int indexOfTask=i+1;
                    tasks[i].printTasks(indexOfTask);
                }
                printLines();
            } else if (words[0].equals("done") & words.length==2) {
                int indexOfTask=Integer.parseInt(words[1])-1;
                tasks[indexOfTask].completeTask();
          } else {
                tasks[Task.getNumberOfTasks()].addTask(command);
            }
            command=inputScanner.nextLine();
        }
        printLines();
        System.out.println("Bye.Hope to see you again soon!");
        printLines();

    }
}
