package duke;


import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.InvalidTask;
import duke.task.Task;

public class Duke {

    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;
    private static Parser parse;
    public static Command command;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NullPointerException n) {
            tasks = new TaskList();
        }

    }

    public static void run() {
        ui.userCommands();
        String input = Ui.inputScanner.nextLine();
        while (!input.equals("bye")) {
            parse = new Parser(input);
            command = parse.parseInput();
            command.execute();
            input = Ui.inputScanner.nextLine();
        }
        Command exit = new ExitCommand();
        exit.execute();
    }

    public static void main(String[] args) {
        new Duke("data/data.txt").run();

    }


    /** Reads the input and calls the corresponding method */


}
