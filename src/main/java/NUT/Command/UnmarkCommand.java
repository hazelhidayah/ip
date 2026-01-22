package NUT.Command;

import NUT.Task.*;

public class UnmarkCommand implements Command {
    private final TaskList list;
    private final int index;

    public UnmarkCommand(TaskList list, int index) {
        this.list = list;
        this.index = index;
    }

    @Override
    public boolean execute() {
        if (index < 0 || index >= list.size()) { // out of bound
            System.out.println("    ____________________________________________________________\n");
            System.out.println("    Invalid task index, that doesn't exist darling :(");
            System.out.println("    ____________________________________________________________\n");
            return false;
        }
        list.get(index).unmark(); // unmark() is from Task
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    Unmarked task: " + list.get(index));
        System.out.println("    ____________________________________________________________\n");
        return false;
    }
}