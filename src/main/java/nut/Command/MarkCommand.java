package nut.Command;

import nut.Task.*;
import nut.Ui.Ui;

public class MarkCommand implements Command {
    private final TaskList list;
    private final int index;

    // constructor
    public MarkCommand(TaskList list, int index) {
        this.list = list;
        this.index = index;
    }

    @Override
    public boolean execute(Ui ui) {
        if (index < 0 || index >= list.size()) { // out of bound
            ui.showSearchError();
            return false;
        }
        list.get(index).mark(); // mark() is from Task
        ui.markTask(list, index);
        return false;
    }
}