package duke.command;

import duke.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
    }

    @Override
    public void execute() {
        Ui.printLines();
        String EXIT_MESSAGE = "Bye.Hope to see you again soon!";
        System.out.println(EXIT_MESSAGE);
        Ui.printLines();

    }
}
