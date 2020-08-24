import java.util.Scanner;

public class Duke{

    public static void printLines(){
        System.out.println("-------------------------------");
    }
    public static void main(String[] args) {
        Scanner inputScanner= new Scanner(System.in);
        String input;
        printLines();
        System.out.println("Hello! I'm Mike!\nMay I have your name please?");
        printLines();
        String name= inputScanner.nextLine();
        printLines();
        System.out.println("Alright " +name+ " , What can I do for you?");
        printLines();
        input=inputScanner.nextLine();
        while(!input.equals("bye")){
            printLines();
            System.out.println(input);
            printLines();
            input=inputScanner.nextLine();
        }

        printLines();
        System.out.println("Bye.Hope to see you again soon!");
        printLines();

    }
}
