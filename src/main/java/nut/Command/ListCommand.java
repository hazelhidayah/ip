package nut.Command;

import nut.Task.TaskList;
import nut.Ui.Ui;

/*
 * Returns the list of tasks when the user says 'list'
 */

public class ListCommand implements Command {
    private final TaskList list; // list

    public ListCommand(TaskList list) {
        this.list = list;
    }

    @Override
    public boolean execute(Ui ui) {
        ui.showTaskList(list);
        return false; // DON'T exit
    }

}