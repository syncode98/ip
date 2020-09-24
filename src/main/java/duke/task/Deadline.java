package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    //private String deadline;
    private String deadline;
    private LocalDate date;

    public Deadline(String task, boolean isDone, String deadline) {
        super(task, isDone);
        //this.deadline = deadline;
        convertTime(deadline);

    }

    public Deadline(String task, String deadline) {
        super(task, false);
        //this.deadline = deadline;
        convertTime(deadline);
    }

    public Deadline() {
        super(null, false);
        this.deadline = null;
        this.date =null;
    }

    public void convertTime(String deadline){
        try {
            LocalDate date=LocalDate.parse(deadline.replace("by:","").strip());
            this.date =date;
            String format=date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.deadline="by: "+ format;

        } catch (java.time.format.DateTimeParseException d) {
            this.deadline = deadline;
        }
    }

    public LocalDate getDate(){
        return date;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + this.deadline + ")";
    }

}
