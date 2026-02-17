package nut.parser;

import nut.command.SearchCommand;
import nut.command.ByeCommand;
import nut.command.HelloCommand;
import nut.command.ListCommand;
import nut.command.AddCommand;
import nut.command.DeleteCommand;
import nut.command.MarkCommand;
import nut.command.UnmarkCommand;
import nut.command.ConfirmDuplicateCommand;
import nut.command.Command;
import nut.task.TaskList;
import nut.task.Deadlines;
import nut.task.Events;
import nut.task.NutException;
import nut.task.ToDos;

/**
 * Converts raw user input into executable {@link nut.command.Command} instances.
 * <p>
 * The {@code parser} is responsible for interpreting a user's command line (e.g. {@code list},
 * {@code todo <desc>}, {@code delete <index>}) and constructing the corresponding command object
 * configured with the provided {@link nut.task.TaskList}.
 * </p>
 * <p>
 * If the input does not match a supported command or is missing required arguments, parsing fails
 * by throwing {@link nut.task.NutException}.
 * </p>
 */
public class Parser {

    /**
     * Parses the user's input string and returns the appropriate command.
     *
     * @param userInput The full user input string.
     * @param list The TaskList to operate on.
     * @return The command object corresponding to the user input.
     * @throws NutException If the input format is invalid.
     */
    public static Command parse(String userInput, TaskList list) throws NutException {

        if (userInput.trim().isEmpty()) { // Handles an empty user input.
            throw new NutException("OOPS, I can't help you as your input is empty :(");

        } else if (list.hasPendingDuplicate()) { // Handles pending duplicate confirmation.

            if (userInput.trim().equalsIgnoreCase("yes")
                    || userInput.trim().equalsIgnoreCase("y")) {
                return new ConfirmDuplicateCommand(list, true);
            } else if (userInput.trim().equalsIgnoreCase("no")
                    || userInput.trim().equalsIgnoreCase("n")) {
                return new ConfirmDuplicateCommand(list, false);
            } // stop the user from entering other inputs, throw an error message for confirmation.
            throw new NutException("Please reply \"yes\" or \"no\" to confirm adding the task.");

        } else if (userInput.equalsIgnoreCase("bye")) { // Handles when the user says "bye".
            return new ByeCommand();

        } else if (userInput.equalsIgnoreCase("hi")
                || userInput.equalsIgnoreCase("hello")
                || userInput.equalsIgnoreCase("hey")) { // Handles when the user says "hello"/"hi"/"hey".
            return new HelloCommand();

        } else if (userInput.equalsIgnoreCase("list")) { // Handles when the user says "list".
            return new ListCommand(list);

        } else if (userInput.startsWith("find")) { // Handles when the user says "find".
            String searchTerm = userInput.substring(4).trim();

            if (searchTerm.isEmpty()) {
                throw new NutException("OOPS! Please enter a search term to search for!");
            }
            return new SearchCommand(list, searchTerm);

        } else if (userInput.startsWith("delete")) { // Handles when the user says "delete".
            String[] parts = userInput.split(" ");

            if (parts.length != 2) {
                throw new NutException("""
                            Please include which task to delete.
                            (if you meant to actually add 'delete' to the task, please rephrase it ^-^)
                        """);
            }
            int index = Integer.parseInt(parts[1]) - 1;
            return new DeleteCommand(list, index);

        } else if (userInput.startsWith("mark")) { // Handles when the user says "mark".
            String[] parts = userInput.split(" ");

            if (parts.length != 2) {
                throw new NutException("""
                            Please include which task to mark off.
                            (if you meant to actually add 'mark' to the task, please rephrase it ^-^)
                        """);
            }
            int index = Integer.parseInt(parts[1]) - 1;
            return new MarkCommand(list, index);

        } else if (userInput.startsWith("unmark")) { // Handles when the user says "unmark".
            String[] parts = userInput.split(" ");

            if (parts.length != 2) {
                throw new NutException("""
                            Please include which task to unmark.
                            (if you meant to actually add 'unmark' to the task, please rephrase it ^-^)
                        """);
            }
            int index = Integer.parseInt(parts[1]) - 1;
            return new UnmarkCommand(list, index);

        } else if (userInput.startsWith("todo")) { // Handles when the user says "todo".
            String taskDescription = userInput.substring(4).trim();

            if (taskDescription.isEmpty()) {
                throw new NutException("OOPS! The description of a todo cannot be empty.");
            }
            return new AddCommand(list, new ToDos(taskDescription));

        } else if (userInput.startsWith("deadline")) { // Handles when the user says "deadline".
            String taskDescription = userInput.substring(8).trim();

            if (taskDescription.isEmpty()) {
                throw new NutException("OOPS! The description of a deadline cannot be empty.");
            }
            return new AddCommand(list, new Deadlines(taskDescription));

        } else if (userInput.startsWith("event")) { // Handles when the user says "event".
            String taskDescription = userInput.substring(5).trim();

            if (taskDescription.isEmpty()) {
                throw new NutException("OOPS! The description of an event cannot be empty.");
            }
            return new AddCommand(list, new Events(taskDescription));

        } else { // Handles when the user's input is invalid.
            throw new NutException("OOPS! I don't know what you just said :(");
        }
    }
}
