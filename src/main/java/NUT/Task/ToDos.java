package NUT.Task;

/**
 * The type To dos.
 */
public class ToDos extends Task {

    /**
     * Instantiates a new Todos.
     *
     * @param name the name
     * @throws NUTException the nut exception
     */
// constructor
    public ToDos(String name) throws NUTException {
        super(name);
    }

    // included [T]
    @Override
    public String getStatusIcon() {
        return (isDone ? "[T] [x]" : "[T] [ ]");
    }
}