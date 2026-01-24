package NUT.Task;

    /*
    deadline return book /by Sunday
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] return book (by: Sunday)
     Now you have 6 tasks in the list.
    ____________________________________________________________
    */

    // return book /by Sunday

public class Deadlines extends Task {
    // protected final String name;
    // protected boolean isDone;

    private final String updatedName;
    private final String day;

    // constructor
    public Deadlines(String name) throws NUTException {
        super(name);

        String[] parts = name.split("/");

        // invalid
        if (parts.length != 2
                || !parts[1].trim().startsWith("by")) {
            throw new NUTException("""
                        ____________________________________________________________
                        OOPS!!! Deadlines must be in the format:
                        deadline <name> /by <day>
                        ____________________________________________________________
                    """);
        }

        this.updatedName = parts[0].trim();
        this.day = parts[1].substring(parts[1].indexOf(" ") + 1); // "day"
    }

    @Override
    public String getName() {
        return updatedName;
    }

    public String getDay() {
        return day;
    }

    // included [D]
    @Override
    public String getStatusIcon() {
        return (isDone ? "[D] [x]" : "[D] [ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + updatedName + " (by: " + day + " )";
    }
}



