package duke;

public class Duke {

    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;

    public Duke (String filepath) {
        ui=new Ui();
        storage=new Storage(filepath);
        try {
            tasks=new TaskList(storage.load());
        } catch(NullPointerException n){
            tasks=new TaskList();
        }

    }

    public static void run(){
        ui.userCommands();
    }

    public static void main(String[] args){
        new Duke("data/data.txt").run();

    }


    /** Reads the input and calls the corresponding method */

}
