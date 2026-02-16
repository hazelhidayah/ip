package nut.Parser;

import nut.Command.SearchCommand;
import nut.Command.ByeCommand;
import nut.Command.HelloCommand;
import nut.Command.ListCommand;
import nut.Command.AddCommand;
import nut.Command.DeleteCommand;
import nut.Command.MarkCommand;
import nut.Command.UnmarkCommand;
import nut.Command.Command;
import nut.Task.TaskList;
import nut.Task.Deadlines;
import nut.Task.Events;
import nut.Task.NutException;
import nut.Task.ToDos;

/**
 * Converts raw user input into executable {@link nut.Command.Command} instances.
 * <p>
 * The {@code Parser} is responsible for interpreting a user's command line (e.g. {@code list},
 * {@code todo <desc>}, {@code delete <index>}) and constructing the corresponding command object
 * configured with the provided {@link nut.Task.TaskList}.
 * </p>
 * <p>
 * If the input does not match a supported command or is missing required arguments, parsing fails
 * by throwing {@link nut.Task.NutException}.
 * </p>
 */
public class Parser {

    /**
     * Parses the user's input string and returns the appropriate Command.
     *
     * @param userInput The full user input string.
     * @param list The TaskList to operate on.
     * @return The Command object corresponding to the user input.
     * @throws NutException If the input format is invalid.
     */
    public static Command parse(String userInput, TaskList list) throws NutException {

        // Handles an empty user input.
        if (userInput.trim().isEmpty()) {
            throw new NutException("""
                            OOPS, I can't help you as your input is empty :(
                        """);
        }

        // Handles when the user says "bye".
        else if (userInput.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        }

        // Handles when the user says "hello", "hi" or "hey".
        else if (userInput.equalsIgnoreCase("hi")
            || userInput.equalsIgnoreCase("hello")
                || userInput.equalsIgnoreCase("hey")) {
            return new HelloCommand();
        }

        // Handles when the user says "list".
        else if (userInput.equalsIgnoreCase("list")) {
            return new ListCommand(list);
        }

        // Handles when the user says "find".
        else if (userInput.startsWith("find")) {
            String searchTerm = userInput.substring(4).trim();

            if (searchTerm.isEmpty()) {
                throw new NutException("""
                            OOPS! Please enter a search term to search for!
                        """);
            }
            return new SearchCommand(list, searchTerm);
        }

        // Handles when the user says "delete".
        else if (userInput.startsWith("delete")) {
            String[] parts = userInput.split(" ");

            if (parts.length != 2) {
                throw new NutException("""
                            Please include which task to delete.
                            (if you meant to actually add 'delete' to the task, please rephrase it ^-^)
                        """);
            }
            int index = Integer.parseInt(parts[1]) - 1;
            return new DeleteCommand(list, index);
        }

        // Handles when the user says "mark".
        else if (userInput.startsWith("mark")) {
            String[] parts = userInput.split(" ");

            if (parts.length != 2) {
                throw new NutException("""
                            Please include which task to mark off.
                            (if you meant to actually add 'mark' to the task, please rephrase it ^-^)
                        """);
            }
            int index = Integer.parseInt(parts[1]) - 1;
            return new MarkCommand(list, index);
        }

        // Handles when the user says "unmark".
        else if (userInput.startsWith("unmark")) {
            String[] parts = userInput.split(" ");

            if (parts.length != 2) {
                throw new NutException("""
                            Please include which task to unmark.
                            (if you meant to actually add 'unmark' to the task, please rephrase it ^-^)
                        """);
            }
            int index = Integer.parseInt(parts[1]) - 1;
            return new UnmarkCommand(list, index);
        }

        // Handles when the user says "todo".
        else if (userInput.startsWith("todo")) {
            String taskDescription = userInput.substring(4).trim();

            if (taskDescription.isEmpty()) {
                throw new NutException("""
                            OOPS! The description of a todo cannot be empty.
                        """);
            }
            return new AddCommand(list, new ToDos(taskDescription));
        }

        // Handles when the user says "deadline".
        else if (userInput.startsWith("deadline")) {
            String taskDescription = userInput.substring(8).trim();

            if (taskDescription.isEmpty()) {
                throw new NutException("""
                            OOPS! The description of a deadline cannot be empty.
                        """);
            }
            return new AddCommand(list, new Deadlines(taskDescription));
        }

        // Handles when the user says "event".
        else if (userInput.startsWith("event")) {
            String taskDescription = userInput.substring(5).trim();

            if (taskDescription.isEmpty()) {
                throw new NutException("""
                            OOPS! The description of an event cannot be empty.
                        """);
            }
            return new AddCommand(list, new Events(taskDescription));
        }

        // Handles when the user's input is invalid.
        else {
            throw new NutException("""
                        OOPS! I don't know what you just said :(
                    """);
        }
    }
}