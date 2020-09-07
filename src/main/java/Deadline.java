public class Deadline extends Task {
    private String deadline;

    public Deadline(String task, boolean isDone, String deadline) {
        super(task, isDone);
        this.deadline = deadline;
    }

    public Deadline(String task, String deadline) {
        super(task,false);
        this.deadline = deadline;
    }
    public Deadline() {
        super(null,false);
        this.deadline=null;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()+"("+this.deadline + ")" ;
    }

}
