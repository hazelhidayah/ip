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

        //String[] list = new String[100];
        ArrayList<String> tasks = new ArrayList<>();

        while (tasks.size() < 100) { // continue till too many tasks
            String userInput = sc.nextLine();

            // the user typed 'bye'
            // end conversation
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("    ____________________________________________________________\n");
                System.out.println("    okay bai");
                System.out.println("    ____________________________________________________________\n");
                break; // to terminate
            }

            // if input is 'list'
            // print the list
            else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("    ____________________________________________________________\n");

                if (tasks.isEmpty()) { // no tasks
                    System.out.println("    You currently have 0 things to do, yay");
                } else { // not empty
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("    " + (i+1) + ". " + tasks.get(i));
                    }
                }
                System.out.println("    ____________________________________________________________\n");
                // System.out.println(userInput);
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
