package NUT;
import java.util.Scanner;
import NUT.Command.*;
import NUT.Task.*;

/*
 * This is the main program class for NUT
 */

public class NUT {
    private static final TaskList list = new TaskList();

    public static void main(String[] args) throws NUTException {
        System.out.println("""
                ____________________________________________________________
                 Hello! I'm NUT
                 What can I do for you?
                ____________________________________________________________
                """);

        Scanner sc = new Scanner(System.in);

        boolean status = false;

        while (!status) {
            String userInput = sc.nextLine();

            try {
                // bye
                if (userInput.equalsIgnoreCase("bye")) {
                    status = new ByeCommand().execute();
                }
                // list
                else if (userInput.equalsIgnoreCase("list")) {
                    status = new ListCommand(list).execute();
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

                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    list.delete(index);
                    System.out.println("    Task removed!");
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

                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    status = new MarkCommand(list, index).execute();
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

                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    status = new UnmarkCommand(list, index).execute();
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
                    list.add(new ToDos(taskDescription));
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
                        list.add(new Deadlines(taskDescription));
                    } catch (NUTException e) {
                        System.out.println(e.getMessage());
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
                        list.add(new Events(taskDescription));
                    } catch (NUTException e) {
                        System.out.println(e.getMessage());
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

            } catch (NUTException e) {
                System.out.println(e.getMessage());
            }
        }

        sc.close();
    }
}