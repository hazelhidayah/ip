package NUT.Command;

import NUT.Task.*;
import NUT.Ui.Ui;

/*
 * Deletes task
 */
public class DeleteCommand implements Command {
    private final TaskList list;
    private final int index;

    public DeleteCommand(TaskList list, int index) {
        this.list = list;
        this.index = index;
    }

    @Override
    public boolean execute(Ui ui) {
        ui.showTaskDeleted(list.get(index), list.size() - 1);
        list.delete(index);
        return false;
    }
}