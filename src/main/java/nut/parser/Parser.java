package nut.parser;

import nut.command.SearchCommand;
import nut.command.ByeCommand;
import nut.command.HelloCommand;
import nut.command.ListCommand;
import nut.command.HelpCommand;
import nut.command.AddCommand;
import nut.command.DeleteCommand;
import nut.command.MarkCommand;
import nut.command.UnmarkCommand;
import nut.command.ConfirmDuplicateCommand;
import nut.command.Command;
import nut.task.TaskList;
import nut.task.Deadlines;
import nut.task.Events;
import nut.exception.NutException;
import nut.task.ToDos;
import java.util.Locale;

/**
 * Converts raw user input into executable {@link nut.command.Command} instances.
 * <p>
 * The {@code parser} is responsible for interpreting a user's command line (e.g. {@code list},
 * {@code todo <desc>}, {@code delete <index>}) and constructing the corresponding command object
 * configured with the provided {@link nut.task.TaskList}.
 * </p>
 * <p>
 * If the input does not match a supported command or is missing required arguments, parsing fails
 * by throwing {@link NutException}.
 * </p>
 */
public class Parser {

    /**
     * Parses the user's input string and returns the appropriate command.
     *
     * @param userInput The full user input string.
     * @param list      The TaskList to operate on.
     * @return The command object corresponding to the user input.
     * @throws NutException If the input format is invalid.
     */
    public static Command parse(String userInput, TaskList list) throws NutException {
        validateNonEmptyInput(userInput);

        if (list.hasPendingDuplicate()) {
            return parseDuplicateConfirmation(userInput, list);
        } else if (userInput.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        } else if (isGreeting(userInput)) {
            return new HelloCommand();
        } else if (userInput.equalsIgnoreCase("list")) {
            return new ListCommand(list);
        } else if (userInput.trim().equalsIgnoreCase("help")) {
            return new HelpCommand();
        } else if (userInput.toLowerCase(Locale.ROOT).startsWith("find")) {
            return parseFind(userInput, list);
        } else if (userInput.startsWith("delete")) {
            return parseDelete(userInput, list);
        } else if (userInput.startsWith("mark")) {
            return parseMark(userInput, list);
        } else if (userInput.startsWith("unmark")) {
            return parseUnmark(userInput, list);
        } else if (userInput.startsWith("todo")) {
            return parseTodo(userInput, list);
        } else if (userInput.startsWith("deadline")) {
            return parseDeadline(userInput, list);
        } else if (userInput.startsWith("event")) {
            return parseEvent(userInput, list);
        } else {
            throw invalidCommandException();
        }
    }

    private static void validateNonEmptyInput(String userInput) throws NutException {
        if (userInput.trim().isEmpty()) {
            throw new NutException("""
           Nut can't crack an empty command.
           Type something and I'll shell it out for you.
           """);
        }
    }

    private static Command parseDuplicateConfirmation(String userInput, TaskList list) throws NutException {
        if (userInput.trim().equalsIgnoreCase("yes")
                || userInput.trim().equalsIgnoreCase("y")) {
            return new ConfirmDuplicateCommand(list, true);
        }
        if (userInput.trim().equalsIgnoreCase("no")
                || userInput.trim().equalsIgnoreCase("n")) {
            return new ConfirmDuplicateCommand(list, false);
        }
        throw new NutException("Please reply \"yes\" or \"no\" so I can crack this issue.");
    }

    private static boolean isGreeting(String userInput) {
        return userInput.equalsIgnoreCase("hi")
                || userInput.equalsIgnoreCase("hello")
                || userInput.equalsIgnoreCase("hey");
    }

    private static Command parseFind(String userInput, TaskList list) throws NutException {
        String searchTerm = userInput.substring(4).trim();
        if (searchTerm.isEmpty()) {
            throw new NutException("Toss me a keyword so I can sniff out the right nut.");
        }
        return new SearchCommand(list, searchTerm);
    }

    private static Command parseDelete(String userInput, TaskList list) throws NutException {
        String[] parts = userInput.split(" ");
        if (parts.length != 2) {
            throw new NutException("""
                            Please include which task to delete, little acorn.
                            (if you meant to actually add 'delete' to the task, please rephrase it ^-^)
                        """);
        }
        int index = parseTaskIndex(parts[1], "delete");
        return new DeleteCommand(list, index);
    }

    private static Command parseMark(String userInput, TaskList list) throws NutException {
        String[] parts = userInput.split(" ");
        if (parts.length != 2) {
            throw new NutException("""
                            Please include which task to mark off, little acorn.
                            (if you meant to actually add 'mark' to the task, please rephrase it ^-^)
                        """);
        }
        int index = parseTaskIndex(parts[1], "mark");
        return new MarkCommand(list, index);
    }

    private static Command parseUnmark(String userInput, TaskList list) throws NutException {
        String[] parts = userInput.split(" ");
        if (parts.length != 2) {
            throw new NutException("""
                            Please include which task to unmark, little acorn.
                            (if you meant to actually add 'unmark' to the task, please rephrase it ^-^)
                        """);
        }
        int index = parseTaskIndex(parts[1], "unmark");
        return new UnmarkCommand(list, index);
    }

    private static Command parseTodo(String userInput, TaskList list) throws NutException {
        String taskDescription = userInput.substring(4).trim();
        if (taskDescription.isEmpty()) {
            throw new NutException("A todo needs a description. This shell is empty.");
        }
        return new AddCommand(list, new ToDos(taskDescription));
    }

    private static Command parseDeadline(String userInput, TaskList list) throws NutException {
        String taskDescription = userInput.substring(8).trim();
        if (taskDescription.isEmpty()) {
            throw new NutException("A deadline needs a description. This shell is empty.");
        }
        return new AddCommand(list, new Deadlines(taskDescription));
    }

    private static Command parseEvent(String userInput, TaskList list) throws NutException {
        String taskDescription = userInput.substring(5).trim();
        if (taskDescription.isEmpty()) {
            throw new NutException("An event needs a description. This shell is empty.");
        }
        return new AddCommand(list, new Events(taskDescription));
    }

    private static NutException invalidCommandException() {
        return new NutException("""
                    I couldn't crack that command.
                    Try help for the command list.
                    """);
    }

    private static int parseTaskIndex(String indexText, String commandWord) throws NutException {
        try {
            return Integer.parseInt(indexText) - 1;
        } catch (NumberFormatException e) {
            throw new NutException("Please provide a valid task number for " + commandWord + ".");
        }
    }
}
