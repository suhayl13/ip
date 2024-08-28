import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends DatedTask{
    protected LocalDateTime by;

    public Deadline(String taskName, LocalDateTime by) {
        super(taskName);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }
    public String getFormattedDateAndTime() {
        return this.by.format((DatedTask.outputFormatter));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.getFormattedDateAndTime() + ")";
    }
}
