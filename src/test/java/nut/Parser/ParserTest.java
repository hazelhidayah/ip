package nut.Parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import nut.Command.ByeCommand;
import nut.Command.Command;
import nut.Command.DeleteCommand;
import nut.Command.ListCommand;
import nut.Command.SearchCommand;
import nut.Task.NutException;
import nut.Task.TaskList;

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
    void parse_deleteWithIndex_returnsDeleteCommand() throws NutException {
        // Arrange
        TaskList list = new TaskList();

        // Act
        Command command = Parser.parse("delete 1", list);

        // Assert
        assertInstanceOf(DeleteCommand.class, command);
    }
}