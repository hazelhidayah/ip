package nut.command;

import nut.task.TaskList;
import nut.ui.Ui;

/**
 * command that searches the task list for tasks matching a user-provided query.
 * <p>
 * When executed, this command scans the associated {@link nut.task.TaskList} and uses the
 * {@link nut.ui.Ui} to display the tasks whose description contains (or otherwise matches)
 * the search keyword.
 * </p>
 */
public class SearchCommand implements Command {
    private final TaskList list;
    private final String searchTerm;

    public SearchCommand(TaskList list, String searchTerm) {
        this.list = list;
        this.searchTerm = searchTerm;
    }

    @Override
    public String execute(Ui ui) {
        return ui.showSearchResults(list, searchTerm);
    }
}
