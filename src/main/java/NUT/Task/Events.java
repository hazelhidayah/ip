package NUT.Task;

    /*
    event project meeting /from Mon 2pm /to 4pm
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] project meeting (from: Mon 2pm to: 4pm)
     Now you have 7 tasks in the list.
    ____________________________________________________________
    */

    // project meeting /from Mon 2pm /to 4pm

public class Events extends Task {
    // protected final String name;
    // protected boolean isDone;
    private final String updatedName;
    private final String updatedStartTime;
    private final String updatedEndTime;

    // constructor
    public Events(String name) throws NUTException {
        super(name);

        String[] parts = name.split("/");

        // invalid
        if (parts.length != 3
                || !parts[1].trim().startsWith("from")
                || !parts[2].trim().startsWith("to")) {
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

    public String getStartTime() {
        return updatedStartTime;
    }

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



