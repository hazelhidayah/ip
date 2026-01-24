package NUT.Task;

    /*
    todo borrow book
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] borrow book
     Now you have 5 tasks in the list.
    ____________________________________________________________
    */


public class ToDos extends Task {
    // protected final String name;
    // protected boolean isDone;

    // constructor
    public ToDos(String name) {
        super(name);
    }

    // included [T]
    @Override
    public String getStatusIcon() {
        return (isDone ? "[T] [x]" : "[T] [ ]");
    }
}