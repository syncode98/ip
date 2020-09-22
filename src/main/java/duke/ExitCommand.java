package duke;

public class ExitCommand extends Command {

    public ExitCommand() {
        Ui.printLines();
        String EXIT_MESSAGE = "Bye.Hope to see you again soon!";
        System.out.println(EXIT_MESSAGE);
        Ui.printLines();


    }


}
