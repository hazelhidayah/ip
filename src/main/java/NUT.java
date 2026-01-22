import java.util.Scanner;
import java.util.ArrayList;

public class NUT {
    public static void main(String[] args) {
        String logo = """
                ____________________________________________________________
                 Hello! I'm NUT
                 What can I do for you?
                ____________________________________________________________
                """;
        System.out.println(logo);

        Scanner sc = new Scanner(System.in); // read user input

        ArrayList<String> tasks = new ArrayList<>(); // track tasks
        boolean[] tracker = new boolean[100]; // track state of tasks, all false

        while (tasks.size() < 100) { // continue till too many tasks
            String userInput = sc.nextLine();

            // user typed 'bye'     ->  end conversation
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("    ____________________________________________________________\n");
                System.out.println("    okay bai");
                System.out.println("    ____________________________________________________________\n");
                break; // to terminate
            }

            // if input is 'list'   ->  print the list
            else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("    ____________________________________________________________\n");
                System.out.println("    Here are the tasks in your list:\n");

                if (tasks.isEmpty()) { // no tasks
                    System.out.println("    You currently have 0 things to do, yay");
                } else { // not empty
                    for (int i = 0; i < tasks.size(); i++) {
                        String s;
                        if (tracker[i]) { // tracker = true = done
                            s = "[x] ";
                        } else { // not done
                            s = "[ ] ";
                        }
                        System.out.println("    " + (i + 1) + "." + s + tasks.get(i));
                    }
                }
                System.out.println("    ____________________________________________________________\n");
                // System.out.println(userInput);
            }

            // user typed 'mark ___'     ->  change the boolean status
            else if (userInput.split(" ")[0].equalsIgnoreCase("mark")) {

                // split to get the second word
                String[] words = userInput.split(" ");

                // only said 'mark'
                if (words.length == 1) {
                    System.out.println("    ____________________________________________________________\n");
                    System.out.println("    please indicate which task you want to mark off :)");
                    System.out.println("    ____________________________________________________________\n");
                } else { // 2 words
                    int num = Integer.parseInt(words[1]); // change to int

                    if (num > tasks.size() || num < 1) {
                        System.out.println("    ____________________________________________________________\n");
                        System.out.println("    that doesn't exist, darling \n");
                        System.out.println("    ____________________________________________________________\n");
                    } else {
                        System.out.println("    ____________________________________________________________\n");

                        if (tracker[num - 1]) { // already true
                            System.out.println("    you've already done it babes!\n");
                        } else { // change from undone to done
                            tracker[num - 1] = true;// change to true
                            System.out.println("    Good job babes! This task is now done: \n");
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
                    System.out.println("    please indicate which task you want to unmark :)");
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

            else { // they gave tasks
                tasks.add(userInput);
                System.out.println("    ____________________________________________________________\n");
                System.out.println("    added: " + userInput + "\n");
                System.out.println("    ____________________________________________________________\n");
            }
        }

            // break cause too many tasks or user said bye
            if (tasks.size() > 99) { // max out
                System.out.println("    ____________________________________________________________\n");
                System.out.println("    You're too overwhelmed! No more tasks for you >:( \n");
                System.out.println("    ____________________________________________________________\n");
            }

            sc.close();
    }
}
