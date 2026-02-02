package nut.Parser;

import nut.Command.AddCommand;
import nut.Command.ByeCommand;
import nut.Command.Command;
import nut.Command.DeleteCommand;
import nut.Command.HelloCommand;
import nut.Command.ListCommand;
import nut.Command.MarkCommand;
import nut.Command.UnmarkCommand;
import nut.Task.TaskList;
import nut.Task.Deadlines;
import nut.Task.Events;
import nut.Task.NutException;
import nut.Task.ToDos;

/**
 * Parses user input and creates corresponding Command objects.
 */
public class Parser {

    /**
     * Parses the user's input string and returns the appropriate Command.
     * @param userInput The full user input string.
     * @param list The TaskList to operate on.
     * @return The Command object corresponding to the user input.
     * @throws NutException If the input format is invalid.
     */
    public static Command parse(String userInput, TaskList list) throws NutException {
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
                throw new NutException("""
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
                throw new NutException("""
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
                throw new NutException("""
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
                throw new NutException("""
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
                throw new NutException("""
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
                throw new NutException("""
                        ____________________________________________________________
                        Oops! The description of an event cannot be empty.
                        ____________________________________________________________
                        """);
            }

            return new AddCommand(list, new Events(taskDescription));
        }

        // not valid input
        else {
            throw new NutException("""
                    ____________________________________________________________
                    Oops, I don't know what you just said :(
                    ____________________________________________________________
                    """);
        }
    }
}