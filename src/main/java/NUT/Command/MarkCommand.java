package NUT.Command;

import NUT.Task.*;

public class MarkCommand implements Command {
    private final TaskList list;
    private final int index;

    // constructor
    public MarkCommand(TaskList list, int index) {
        this.list = list;
        this.index = index;
    }

    /*
     for if only 'mark' is stated

     // this is
     public MarkCommand(TaskList list) {
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    Invalid task index, that doesn't exist darling :(");
        System.out.println("    ____________________________________________________________\n");
     }

     NEVERMIND
     */

    @Override
    public boolean execute() {
        if (index < 0 || index >= list.size()) { // out of bound
            System.out.println("    ____________________________________________________________\n");
            System.out.println("    Invalid task index, that doesn't exist darling :(");
            System.out.println("    ____________________________________________________________\n");
            return false;
        }
        list.get(index).mark(); // mark() is from Task
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    ood job babes! \n");
        System.out.println("    Marked task: " + list.get(index));
        System.out.println("    ____________________________________________________________\n");
        return false;
    }
}