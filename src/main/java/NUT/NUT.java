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

        Scanner sc = new Scanner(System.in); // read user input

        // by the end of each command, the status will change.
        // if the status becomes true then exit.
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

                // mark ONLY
                else if (userInput.equalsIgnoreCase("mark")) {
                    throw new NUTException("""
                                ____________________________________________________________
                                Please include which task to mark off.
                                (if you meant to actually add 'mark' to the task, please rephrase it ^-^)
                                ____________________________________________________________
                            """);
                }
                // unmark ONLY
                else if (userInput.equalsIgnoreCase("unmark")) {
                    throw new NUTException("""
                                ____________________________________________________________
                                Please include which task to unmark.
                                (if you meant to actually add 'unmark' to the task, please rephrase it ^-^)
                                ____________________________________________________________
                            """);
                }

                // mark
                else if (userInput.startsWith("mark ")) {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    status = new MarkCommand(list, index).execute();
                }
                // unmark
                else if (userInput.startsWith("unmark ")) {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    status = new UnmarkCommand(list, index).execute();
                }

                // todo
                else if (userInput.startsWith("todo ")) {
                    // remove the first word
                    String editedName = userInput.substring(userInput.indexOf(" ") + 1);

                    if (editedName.trim().isEmpty()) {
                        throw new NUTException("""
                        ____________________________________________________________
                        Oops! The description of a todo cannot be empty.
                        ____________________________________________________________
                    """);
                    }
                    list.add(new ToDos(editedName));
                }
                // deadline
                else if (userInput.startsWith("deadline ")) {
                    // remove the first word
                    String editedName = userInput.substring(userInput.indexOf(" ") + 1);
                    list.add(new Deadlines(editedName));
                }
                // event
                else if (userInput.startsWith("event ")) {
                    // remove the first word
                    String editedName = userInput.substring(userInput.indexOf(" ") + 1);
                    list.add(new Events(editedName));
                }

                // not valid input
                else {
                    throw new NUTException("""
                                ____________________________________________________________
                                Oops, I don't know what you just said :(
                                ____________________________________________________________
                            """);
                    // if (status) { break;}
                    // list.add(new Task(userInput)); // add to list
                }

            } catch (NUTException e) { // this just loops the error msg without terminating!
                System.out.println(e.getMessage());
            }
        }

        /*
        // break cause too many tasks or user said bye
        if (tasks.size() > 99) { // max out
            System.out.println("    ____________________________________________________________\n");
            System.out.println("    You're too overwhelmed! No more tasks for you >:( \n");
            System.out.println("    ____________________________________________________________\n");
        */

        sc.close();
    }
}