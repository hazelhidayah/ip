package NUT.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks, allowing for addition, removal, and retrieval.
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Instantiates a new Task list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with existing tasks (for loading from file).
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
    }

    /**
     * Add.
     *
     * @param task the task
     */
// add new task to list
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Get task.
     *
     * @param index the index
     * @return the task
     */
// get specific task from list
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Delete.
     *
     * @param index the index
     */
// delete
    public void delete(int index) {
        list.remove(index);
    }

    /**
     * Size int.
     *
     * @return the int
     */
// return size of the list
    // this is needed cause the list is private
    public int size() {
        return list.size();
    }

    /**
     * Retrieve all array list.
     *
     * @return the array list
     */
    public ArrayList<Task> retrieveAll() {
        return list;
    }

}
