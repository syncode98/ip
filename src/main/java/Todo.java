public class Todo extends Task {
    public Todo(String task, boolean isDone) {
        super(task, isDone);
    }

    public Todo(String task) {
        super(task, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

