package duke;

import duke.command.*;


public class Parser {
    public static Command command;

    public static String KEYWORD_LIST = "list";
    public static String KEYWORD_DONE = "done";
    public static String KEYWORD_DELETE = "delete";

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
        } else {
            command = new AddCommand(input);

        }

    }

}
