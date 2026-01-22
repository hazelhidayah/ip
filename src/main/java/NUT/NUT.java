package NUT;
import java.util.Scanner;
import NUT.Command.*;
import NUT.Task.*;

/*
 * This is the main program class for NUT
 */

public class NUT {
    private static final TaskList list = new TaskList();

    public static void main(String[] args) {
        System.out.println("""
                ____________________________________________________________
                 Hello! I'm NUT
                 What can I do for you?
                ____________________________________________________________
                """);

        Scanner sc = new Scanner(System.in); // read user input

        // by the end of each command, the status will change. if the status becomes true then exit.
        boolean status = false;

        while (!status) { // while false
            String userInput = sc.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                status = new ByeCommand().execute();
            } else if (userInput.equalsIgnoreCase("list")) {
                status = new ListCommand(list).execute();
            } else if (userInput.equalsIgnoreCase("mark")) { // mark ONLY
                System.out.println("    ____________________________________________________________\n");
                System.out.println("    Please include which task to mark off.");
                System.out.println("    (if you meant to actually add 'mark' to the task, please rephrase it ^-^) \n");
                System.out.println("    ____________________________________________________________\n");
            } else if (userInput.equalsIgnoreCase("unmark")) { // unmark ONLY
                System.out.println("    ____________________________________________________________\n");
                System.out.println("    Please include which task to unmark.");
                System.out.println("    (if you meant to actually add 'unmark' to the task, please rephrase it ^-^) \n");
                System.out.println("    ____________________________________________________________\n");
            } else if (userInput.startsWith("mark ")) {
                // if "only mark"
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                status = new MarkCommand(list, index).execute();
            } else if (userInput.startsWith("unmark ")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                status = new UnmarkCommand(list, index).execute();
            } else { // other things to add to the list
                list.add(new Task(userInput)); // add to list
            }
        }

        /*
        while (tasks.size() < 100) { // continue till too many tasks
            String userInput = sc.nextLine();

                // only said 'mark'
                if (words.length == 1) {
                    System.out.println("    ____________________________________________________________\n");
                    System.out.println("    please indicate which task you want to mark off :)");
                    System.out.println("    ____________________________________________________________\n");
                } else { // 2 words
                    int num = Integer.parseInt(words[1]); // change to int


                        if (tracker[num - 1]) { // already true
                            System.out.println("    you've already done it babes!\n");
                        } else { // change from undone to done
                            tracker[num - 1] = true;// change to true
                            System.out.println("    Good job babes! This NUT.task is now done: \n");
                        }
                        System.out.println("    [x] " + tasks.get(num - 1) + "\n");
                        System.out.println("    ____________________________________________________________\n");
                    }
                }
            }

            // user typed 'unmark ___'     ->  change the boolean status
            else if (userInput.split(" ")[0].equalsIgnoreCase("unmark")) {

                // split to get the second word
                String[] words = userInput.split(" ");

                // only said 'unmark'
                if (words.length == 1) {
                    System.out.println("    ____________________________________________________________\n");
                    System.out.println("    please indicate which NUT.task you want to unmark :)");
                    System.out.println("    ____________________________________________________________\n");
                } else {
                    int num = Integer.parseInt(words[1]);

                    if (num > tasks.size() || num < 1) {
                        System.out.println("    ____________________________________________________________\n");
                        System.out.println("    that doesn't exist, darling \n");
                        System.out.println("    ____________________________________________________________\n");
                    } else {
                        if (!tracker[num - 1]) { // already false
                            System.out.println("    er... still not done ah...\n");
                        } else { // change from undone to done
                            tracker[num - 1] = false;// change to false
                            System.out.println("    Okak, I've marked it as undone: \n");
                        }
                        System.out.println("    [ ] " + tasks.get(num - 1) + "\n");
                        System.out.println("    ____________________________________________________________\n");
                    }
                }
            }

        // break cause too many tasks or user said bye
        if (tasks.size() > 99) { // max out
            System.out.println("    ____________________________________________________________\n");
            System.out.println("    You're too overwhelmed! No more tasks for you >:( \n");
            System.out.println("    ____________________________________________________________\n");
        }
        */

        sc.close();
    }
}
