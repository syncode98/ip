package duke;

import duke.command.*;
import duke.command.FindCommand;
import duke.command.ListCommand;

public class Parser {
    private String input;
    public static String KEYWORD_LIST = "list";
    public static String KEYWORD_DONE = "done";
    public static String KEYWORD_DELETE = "delete";
    public static String KEYWORD_FIND = "find";
    public static String DELIMITER_EMPTY_STRING = "";

    /**
     * Reads the command and instantiates the corresponding task.
     *
     * @param input The command given by the user
     */
    public Parser(String input) {
        this.input = input;

    }


    public Command parseInput() {
        Command command;
        if (input.contains(KEYWORD_DONE)) {
            input = input.replace(KEYWORD_DONE, DELIMITER_EMPTY_STRING).strip();
            command = new DoneCommand(input);

        } else if (input.contains(KEYWORD_DELETE)) {
            input = input.replace(KEYWORD_DELETE, DELIMITER_EMPTY_STRING).strip();
            command = new DeleteCommand(input);

        } else if (input.contains(KEYWORD_LIST)) {
            command = new ListCommand();

        } else if (input.contains(KEYWORD_FIND)) {
            input = input.replace(KEYWORD_FIND, DELIMITER_EMPTY_STRING).strip();
            command = new FindCommand(input);

        } else {
            command = new AddCommand(input);

        }
        return command;

    }
}
