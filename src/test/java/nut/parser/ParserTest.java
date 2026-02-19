package nut.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import nut.command.AddCommand;
import nut.command.ByeCommand;
import nut.command.Command;
import nut.command.ConfirmDuplicateCommand;
import nut.command.DeleteCommand;
import nut.command.HelpCommand;
import nut.command.ListCommand;
import nut.command.MarkCommand;
import nut.command.SearchCommand;
import nut.command.UnmarkCommand;
import nut.exception.NutException;
import nut.task.ToDos;
import nut.task.TaskList;

public class ParserTest {

    @Test
    void parse_bye_returnsByeCommand() throws NutException {
        // Arrange
        TaskList list = new TaskList();

        // Act
        Command command = Parser.parse("bye", list);

        // Assert
        assertInstanceOf(ByeCommand.class, command);
    }

    @Test
    void parse_list_returnsListCommand() throws NutException {
        // Arrange
        TaskList list = new TaskList();

        // Act
        Command command = Parser.parse("list", list);

        // Assert
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    void parse_help_returnsHelpCommand() throws NutException {
        TaskList list = new TaskList();
        Command command = Parser.parse("help", list);
        assertInstanceOf(HelpCommand.class, command);
    }

    @Test
    void parse_helpWithSlash_throwsNutException() {
        TaskList list = new TaskList();
        assertThrows(NutException.class, () -> Parser.parse("/help", list));
    }

    @Test
    void parse_emptyInput_throwsNutException() {
        TaskList list = new TaskList();
        assertThrows(NutException.class, () -> Parser.parse("   ", list));
    }

    @Test
    void parse_findWithSearchTerm_returnsSearchCommand() throws NutException {
        TaskList list = new TaskList();
        Command command = Parser.parse("find report", list);
        assertInstanceOf(SearchCommand.class, command);
    }

    @Test
    void parse_findWithoutSearchTerm_throwsNutException() {
        // Arrange
        TaskList list = new TaskList();

        // Act & Assert
        assertThrows(NutException.class, () -> Parser.parse("find", list));
        assertThrows(NutException.class, () -> Parser.parse("find   ", list));
    }

    @Test
    void parse_deleteWithoutIndex_throwsNutException() {
        // Arrange
        TaskList list = new TaskList();

        // Act & Assert
        assertThrows(NutException.class, () -> Parser.parse("delete", list));
        assertThrows(NutException.class, () -> Parser.parse("delete  ", list));
        assertThrows(NutException.class, () -> Parser.parse("delete 1 2", list));
    }

    @Test
    void parse_deleteNonNumericIndex_throwsNutException() {
        TaskList list = new TaskList();
        assertThrows(NutException.class, () -> Parser.parse("delete abc", list));
    }

    @Test
    void parse_deleteWithIndex_returnsDeleteCommand() throws NutException {
        // Arrange
        TaskList list = new TaskList();

        // Act
        Command command = Parser.parse("delete 1", list);

        // Assert
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    void parse_markWithIndex_returnsMarkCommand() throws NutException {
        TaskList list = new TaskList();
        Command command = Parser.parse("mark 1", list);
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    void parse_markNonNumericIndex_throwsNutException() {
        TaskList list = new TaskList();
        assertThrows(NutException.class, () -> Parser.parse("mark xyz", list));
    }

    @Test
    void parse_unmarkWithIndex_returnsUnmarkCommand() throws NutException {
        TaskList list = new TaskList();
        Command command = Parser.parse("unmark 1", list);
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    void parse_unmarkNonNumericIndex_throwsNutException() {
        TaskList list = new TaskList();
        assertThrows(NutException.class, () -> Parser.parse("unmark ?!?", list));
    }

    @Test
    void parse_todoWithTask_returnsAddCommand() throws NutException {
        TaskList list = new TaskList();
        Command command = Parser.parse("todo read notes", list);
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    void parse_unknownCommand_throwsNutExceptionWithHelpHint() {
        TaskList list = new TaskList();
        NutException exception = assertThrows(NutException.class, () -> Parser.parse("foobar", list));
        assertTrue(exception.getMessage().contains("Try help"));
    }

    @Test
    void parse_pendingDuplicate_yes_returnsConfirmDuplicateCommand() throws NutException {
        TaskList list = new TaskList();
        list.add(new ToDos("read notes"));
        list.add(new ToDos("read notes"));
        Command command = Parser.parse("yes", list);
        assertInstanceOf(ConfirmDuplicateCommand.class, command);
    }

    @Test
    void parse_pendingDuplicate_no_returnsConfirmDuplicateCommand() throws NutException {
        TaskList list = new TaskList();
        list.add(new ToDos("read notes"));
        list.add(new ToDos("read notes"));
        Command command = Parser.parse("no", list);
        assertInstanceOf(ConfirmDuplicateCommand.class, command);
    }

    @Test
    void parse_pendingDuplicate_invalidReply_throwsNutException() throws NutException {
        TaskList list = new TaskList();
        list.add(new ToDos("read notes"));
        list.add(new ToDos("read notes"));
        assertThrows(NutException.class, () -> Parser.parse("maybe", list));
    }
}
