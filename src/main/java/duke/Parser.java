package duke;

import duke.command.*;
import duke.command.FindCommand;
import duke.command.ListCommand;

public class Parser {
    public static Command command;

    public static String KEYWORD_LIST = "list";
    public static String KEYWORD_DONE = "done";
    public static String KEYWORD_DELETE = "delete";
    public static String KEYWORD_FIND = "find";
    public static String DELIMITER_EMPTY_STRING = "";

    public Parser(String input) {

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
            FindCommand findcommand = new FindCommand(input);

        } else {
            command = new AddCommand(input);

        }

    }

}
