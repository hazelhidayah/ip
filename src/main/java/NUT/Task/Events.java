package NUT.Task;

/**
 * Represents a task with a specific start and end date.
 * An Events task contains a description and a date/time by which it must be completed.
 * It supports input formats for both date and time (dd/MM/yyyy HHmm), and date only (dd/MM/yyyy).
 */
public class Events extends Task {
    private final String updatedName;
    private final String updatedStartTime;
    private final String updatedEndTime;

    /**
     * Instantiates a new Events.
     *
     * @param name the name
     * @throws NUTException the nut exception
     */
    public Events(String name) throws NUTException {
        super(name);

        String[] parts = name.split("/");

        if (parts.length != 3
                || !parts[1].trim().startsWith("from")
                || !parts[2].trim().startsWith("to")) { // invalid
            throw new NUTException("""
                        ____________________________________________________________
                        OOPS!!! Events must be in the format:
                        event <name> /from <start> /to <end>
                        ____________________________________________________________
                    """);
        }

        this.updatedName = parts[0].trim();
        this.updatedStartTime = parts[1].substring(parts[1].indexOf(" ") + 1); // "Mon 2pm"
        this.updatedEndTime = parts[2].substring(parts[2].indexOf(" ") + 1); // "4pm"
    }

    @Override
    public String getName() {
        return updatedName;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public String getStartTime() {
        return updatedStartTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public String getEndTime() {
        return updatedEndTime;
    }

    // included [D]
    @Override
    public String getStatusIcon () {
        return (isDone ? "[E] [x]" : "[E] [ ]");
    }

    @Override
    public String toString () {
        return getStatusIcon() + " " + updatedName
                + " (by: " + updatedStartTime
                + " to: " + updatedEndTime + ")";
    }
}



