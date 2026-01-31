package NUT.Task;

public class ToDos extends Task {

    // constructor for new task
    public ToDos(String name) {
        super(name);
    }

    // constructor for loading from file
    public ToDos(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[T] [x]" : "[T] [ ]");
    }

}