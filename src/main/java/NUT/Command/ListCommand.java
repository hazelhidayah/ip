package NUT.Command;

import NUT.Task.*;

/*
 * Returns the list of tasks when the user says 'list'
 */

public class ListCommand implements Command {
    private final TaskList list; // list

    public ListCommand(TaskList list) {
        this.list = list;
    }

    @Override
    public boolean execute() {
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    Here are your tasks:\n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + list.get(i));
        }
        System.out.println("    ____________________________________________________________\n");
        return false; // DON'T exit
    }

}