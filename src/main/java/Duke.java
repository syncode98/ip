import java.util.Scanner;

public class Duke{

    public static void printLines(){
        System.out.println("-------------------------------");
    }
    public static void initiliase(){
        Scanner inputScanner= new Scanner(System.in);
        printLines();
        System.out.println("Hello! I'm Mike!\nEnter your name:");
        printLines();
        String nameOfUser= inputScanner.nextLine();
        printLines();
        System.out.println("Alright " +nameOfUser+ " , What can I do for you?");
        printLines();
    }
    public static void commands(Task tasks) {
        Scanner inputScanner= new Scanner(System.in);
        String command;
        command=inputScanner.nextLine();
        while (!command.equals("bye")) {
            String[] words =command.split(" ");
            if (command.equals("list")) {
                printLines();
                tasks.printTasks();
                printLines();
                command=inputScanner.nextLine();

            } else if (words[0].equals("done") & words.length==2) {
                printLines();
                tasks.completeTasks(Integer.parseInt(words[1]));
                printLines();
                command=inputScanner.nextLine();

            } else {
                printLines();
                tasks.addTask(command);
                printLines();
                command=inputScanner.nextLine();
            }
        }
        printLines();
        System.out.println("Bye.Hope to see you again soon!");
        printLines();

    }
    public static void main(String[] args) {
        Task tasks=new Task();
        initiliase();
        commands(tasks);


    }
}
