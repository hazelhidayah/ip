package NUT.Command;

import NUT.Task.*;
import NUT.Ui.Ui;

/*
 * Returns the list of tasks when the user says 'list'
 */
public class ListCommand implements Command {
    private final TaskList list; // list

    /**
     * Instantiates a new List command.
     *
     * @param list  the list
     */
    public ListCommand(TaskList list) {
        this.list = list;
    }

    @Override
    public boolean execute(Ui ui) {
        ui.showTaskList(list);
        return false; // DON'T exit
    }

}