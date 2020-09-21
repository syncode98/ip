package duke;

import static duke.Parser.readInput;

public class Duke {

    private static Ui ui;

    public Duke (String filepath) {
        ui=new Ui();
        readInput();
    }

    public static void main(String[] args){
        new Duke("data/data.txt");

    }


    /** Reads the input and calls the corresponding method */

}
