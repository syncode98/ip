package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {


    private LocalDate date;
    private LocalTime deadlineTime;


    public Deadline(String task, LocalDate date, LocalTime time) {
        super(task, false);
        this.date = date;
        this.deadlineTime = time;

    }


    public Deadline() {
        super(null, false);
        // this.deadline = null;
        this.date = null;
        this.deadlineTime = null;
    }


    public LocalDate getDate() {
        return date;
    }

    public LocalTime getDeadlineTime() {
        return deadlineTime;
    }


    @Override
    public String toString() {
        String deadline = "";
        if (this.deadlineTime != null) {
            deadline += " "+this.deadlineTime.toString();
        }
        if (this.date != null) {
            deadline += date.format(DateTimeFormatter.ofPattern(" d MMM yyyy "));
        }

        return "[D]" + super.toString() + "(by:" + deadline + ")";
    }

}
