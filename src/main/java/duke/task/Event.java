package duke.task;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;


    public Event(String task, boolean isDone, String startAndEndTime) {
        super(task, isDone);


    }

    public Event(String task, String startAndEndTime) {
        super(task, false);

    }

    public Event(String task, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(task, false);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }


    /**
     * Converts the date, startTime and endTime to string format, only if the
     * object has been instantiated.
     *
     * @return String format of event.
     */
    @Override
    public String toString() {
        String date_format;
        String startAndEndTime = "";

        if (this.startTime != null) {
            startAndEndTime += this.startTime.toString();
        }
        if (this.endTime != null) {
            startAndEndTime += "-" + this.endTime.toString();
        }
        if (this.date != null) {
            date_format = date.format(DateTimeFormatter.ofPattern("d MMM yyyy "));
            startAndEndTime += " " + date_format;
        }

        return "[E]" + super.toString() + "("+"at: "  + startAndEndTime + ")";
    }
}
