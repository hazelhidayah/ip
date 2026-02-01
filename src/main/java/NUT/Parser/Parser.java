package NUT.Parser;

import NUT.Command.*;
import NUT.Task.*;

/**
 * Parses user input and creates corresponding Command objects.
 */
public class Parser {

    /**
     * Parses the user's input string and returns the appropriate Command.
     *
     * @param userInput The full user input string.
     * @param list      The TaskList to operate on.
     * @return The Command object corresponding to the user input.
     * @throws NUTException If the input format is invalid.
     */
    public static Command parse(String userInput, TaskList list) throws NUTException {
        // bye
        if (userInput.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        }

        // hello or hi
        if (userInput.equalsIgnoreCase("hi")
            || userInput.equalsIgnoreCase("hello")) {
            return new HelloCommand();
        }

        // list
        else if (userInput.equalsIgnoreCase("list")) {
            return new ListCommand(list);
        }

        // delete
        else if (userInput.startsWith("delete")) {
            String[] parts = userInput.split(" ");

            if (parts.length != 2) {
                throw new NUTException("""
                        ____________________________________________________________
                        Please include which task to delete.
                        (if you meant to actually add 'delete' to the task, please rephrase it ^-^)
                        ____________________________________________________________
                        """);
            }

            int index = Integer.parseInt(parts[1]) - 1;
            return new DeleteCommand(list, index);
        }

        // mark
        else if (userInput.startsWith("mark")) {
            String[] parts = userInput.split(" ");

            if (parts.length != 2) {
                throw new NUTException("""
                        ____________________________________________________________
                        Please include which task to mark off.
                        (if you meant to actually add 'mark' to the task, please rephrase it ^-^)
                        ____________________________________________________________
                        """);
            }

            int index = Integer.parseInt(parts[1]) - 1;
            return new MarkCommand(list, index);
        }

        // unmark
        else if (userInput.startsWith("unmark")) {
            String[] parts = userInput.split(" ");

            if (parts.length != 2) {
                throw new NUTException("""
                        ____________________________________________________________
                        Please include which task to unmark.
                        (if you meant to actually add 'unmark' to the task, please rephrase it ^-^)
                        ____________________________________________________________
                        """);
            }

            int index = Integer.parseInt(parts[1]) - 1;
            return new UnmarkCommand(list, index);
        }

        // todo
        else if (userInput.startsWith("todo")) {
            String taskDescription = userInput.substring(5).trim();

            if (taskDescription.isEmpty()) {
                throw new NUTException("""
                        ____________________________________________________________
                        OOPS! The description of a todo cannot be empty.
                        ____________________________________________________________
                        """);
            }

            return new AddCommand(list, new ToDos(taskDescription));
        }

        // deadline
        else if (userInput.startsWith("deadline")) {
            String taskDescription = userInput.substring(9).trim();

            if (taskDescription.isEmpty()) {
                throw new NUTException("""
                        ____________________________________________________________
                        Oops! The description of a deadline cannot be empty.
                        ____________________________________________________________
                        """);
            }

            return new AddCommand(list, new Deadlines(taskDescription));
        }

        // event
        else if (userInput.startsWith("event")) {
            String taskDescription = userInput.substring(6).trim();

            if (taskDescription.isEmpty()) {
                throw new NUTException("""
                        ____________________________________________________________
                        Oops! The description of an event cannot be empty.
                        ____________________________________________________________
                        """);
            }

            return new AddCommand(list, new Events(taskDescription));
        }

        // not valid input
        else {
            throw new NUTException("""
                    ____________________________________________________________
                    Oops, I don't know what you just said :(
                    ____________________________________________________________
                    """);
        }
    }
}