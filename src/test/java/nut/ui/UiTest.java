package nut.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nut.exception.NutException;
import nut.task.TaskList;
import nut.task.ToDos;
import org.junit.jupiter.api.Test;

public class UiTest {

    @Test
    void showSearchResults_withMatches_returnsMatchingTasks() throws NutException {
        Ui ui = new Ui();
        TaskList list = new TaskList();
        list.add(new ToDos("read report"));
        list.add(new ToDos("buy milk"));

        String result = ui.showSearchResults(list, "read");

        assertTrue(result.contains("Here are the matching tasks in your nut stash:"));
        assertTrue(result.contains("1.[T] [ ] read report"));
    }

    @Test
    void showSearchResults_withoutMatches_returnsNoResultsMessage() throws NutException {
        Ui ui = new Ui();
        TaskList list = new TaskList();
        list.add(new ToDos("read report"));

        String result = ui.showSearchResults(list, "zzz");

        assertEquals("No results found for \"zzz\".", result);
    }
}
