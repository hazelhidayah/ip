package NUT.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    // constructor
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with existing tasks (for loading from file).
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
    }

    // add new task to list
    public void add(Task task) {
        list.add(task);
    }

    // get specific task from list
    public Task get(int index) {
        return list.get(index);
    }

    // delete
    public void delete(int index) {
        list.remove(index);
    }

    // return size of the list
    // this is needed cause the list is private
    public int size() {
        return list.size();
    }

    public ArrayList<Task> retrieveAll() {
        return list;
    }

}
