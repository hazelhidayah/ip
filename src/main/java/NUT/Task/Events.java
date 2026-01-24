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

    // constructor
    public Events(String name) {
        super(name);
    }

    // for name only
    String updatedName = name.split("/")[0];

    // for start only
    String startTime = name.split("/")[1]; // "from Mon 2pm"
    String updatedStartTime = startTime.substring(startTime.indexOf(" ")+1); // "Mon 2pm"

    // for end only
    String endTime = name.split("/")[2]; // "to 2pm"
    String updatedEndTime = endTime.substring(endTime.indexOf(" ")+1); // "2pm"

    @Override
    public String getName() {
        return updatedName;
    }

    public String getStart() {
        return updatedStartTime;
    }

    public String getEnd() {
        return updatedEndTime;
    }

    // included [D]
    @Override
    public String getStatusIcon() {
        return (isDone ? "[E] [x]" : "[E] [ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + updatedName
                + " (by: " +  updatedStartTime
                + " to: " + updatedEndTime + ")";
    }
}



