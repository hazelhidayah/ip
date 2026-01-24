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

    // constructor
    public Deadlines(String name) {
        super(name);
    }

    // for name only
    String updatedName = name.split("/")[0];

    // for day only
    String day = name.substring(name.lastIndexOf(" ") +1);

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



