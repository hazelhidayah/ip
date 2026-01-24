package NUT.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    // constructor
    public TaskList() {
        this.list = new ArrayList<>();
    }

    // add new task to list
    public void add(Task task) {
        list.add(task);
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    added: " + task + "\n");
        System.out.println("    ____________________________________________________________\n");
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
