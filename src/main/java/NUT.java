import java.util.Scanner;

public class NUT {
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n" +
                " Hello! I'm NUT\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(logo);

        Scanner sc = new Scanner(System.in); // read user input

        while (true) { // continue till false
            String userInput = sc.nextLine();

            // the user typed 'bye'
            // end conversation when user says 'bye'.
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("okey byebye");
                break; // to terminate
            }
            System.out.println(userInput);
        }

        sc.close();
    }
}
