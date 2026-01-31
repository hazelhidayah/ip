package NUT;
import java.util.Scanner;
import NUT.Command.*;
import NUT.Task.*;
import NUT.Ui.*;

/*
 * This is the main program class for NUT
 */

public class NUT {
    private static final TaskList list = new TaskList();

    public static void main(String[] args) throws NUTException {
        Ui ui = new Ui();
        ui.showWelcome();

        // by the end of each command, the status will change.
        // if the status becomes true then exit.
        boolean status = false;

        while (!status) {
            String userInput = ui.readCommand();

            try {
                // bye
                if (userInput.equalsIgnoreCase("bye")) {
                    status = new ByeCommand().execute(ui);
                }
                // list
                else if (userInput.equalsIgnoreCase("list")) {
                    status = new ListCommand(list).execute(ui);
                }
                // delete
                else if (userInput.startsWith("delete")) {
                    String[] parts = userInput.split(" ");

                    if (parts.length != 2) { // only "delete" or "delete "
                        throw new NUTException("""
                                ____________________________________________________________
                                Please include which task to delete.
                                (if you meant to actually add 'delete' to the task, please rephrase it ^-^)
                                ____________________________________________________________
                            """);
                    }

                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    list.delete(index);
                    ui.showTaskDeleted(list.get(index), list.size());
                }

                // mark
                else if (userInput.startsWith("mark")) {
                    String[] parts = userInput.split(" ");

                    if (parts.length != 2) { // only "mark" or "mark "
                        throw new NUTException("""
                                ____________________________________________________________
                                Please include which task to mark off.
                                (if you meant to actually add 'mark' to the task, please rephrase it ^-^)
                                ____________________________________________________________
                            """);
                    }

                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    status = new MarkCommand(list, index).execute(ui);
                }
                // unmark
                else if (userInput.startsWith("unmark")) {
                    String[] parts = userInput.split(" ");

                    if (parts.length != 2) { // only "unmark" or "unmark "
                        throw new NUTException("""
                                ____________________________________________________________
                                Please include which task to unmark.
                                (if you meant to actually add 'unmark' to the task, please rephrase it ^-^)
                                ____________________________________________________________
                            """);
                    }

                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    status = new UnmarkCommand(list, index).execute(ui);
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
                    Task newTask = new ToDos(taskDescription);
                    list.add(newTask);
                    ui.showTaskAdded(newTask, list.size());
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

                    try {
                        Task newTask = new Deadlines(taskDescription);
                        list.add(newTask);
                        ui.showTaskAdded(newTask, list.size());
                    } catch (NUTException e) {
                        ui.showError(e.getMessage());
                    }
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

                    try {
                        Task newTask = new Events(taskDescription);
                        list.add(newTask);
                        ui.showTaskAdded(newTask, list.size());
                    } catch (NUTException e) {
                        ui.showError(e.getMessage());
                    }
                }

                // not valid input
                else {
                    throw new NUTException("""
                                ____________________________________________________________
                                Oops, I don't know what you just said :(
                                ____________________________________________________________
                            """);
                }

            } catch (NUTException e) { // this just loops the error msg without terminating!
                ui.showError(e.getMessage());
            }
        }
    }
}