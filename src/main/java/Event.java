public class Event extends Task {
    private String startAndEndTime;

    public Event(String task, boolean isDone, String startAndEndTime) {
        super(task, isDone);
        this.startAndEndTime = startAndEndTime;
    }

    public Event(String task, String startAndEndTime) {
        super(task, false);
        this.startAndEndTime = startAndEndTime;
    }

    public String getStartAndEndTime() {
        return startAndEndTime;
    }

    public void setStartAndEndTime(String startAndEndTime) {
        this.startAndEndTime = startAndEndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + this.startAndEndTime + ")";
    }
}
