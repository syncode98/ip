import java.util.Scanner;

public class Duke{

    public static void printLines(){
        System.out.println("-------------------------------");
    }
    public static void initiliase(){
        Scanner inputScanner= new Scanner(System.in);
        printLines();
        System.out.println("Hello! I'm Mike!\nMay I have your name please?");
        printLines();
        String name= inputScanner.nextLine();
        printLines();
        System.out.println("Alright " +name+ " , What can I do for you?");
        printLines();
    }
    public static void main(String[] args) {
        Scanner inputScanner= new Scanner(System.in);
        String input;
        Task tasks=new Task();
        initiliase();
        input=inputScanner.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printLines();
                tasks.printTask();
                printLines();
                input=inputScanner.nextLine();
            } else {
                printLines();
                tasks.addTask(input);
                printLines();
                input=inputScanner.nextLine();
            }
        }

        printLines();
        System.out.println("Bye.Hope to see you again soon!");
        printLines();

    }
}
