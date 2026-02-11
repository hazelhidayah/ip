package nut.Command;

import nut.Task.TaskList;
import nut.Ui.Ui;

public class UnmarkCommand implements Command {
    private final TaskList list;
    private final int index;

    public UnmarkCommand(TaskList list, int index) {
        this.list = list;
        this.index = index;
    }

    @Override
    public String execute(Ui ui) {
        if (index < 0 || index >= list.size()) { // out of bound
            return ui.showSearchError();
        }
        list.get(index).unmark(); // unmark() is from Task
        return ui.markTask(list, index);
    }
}
