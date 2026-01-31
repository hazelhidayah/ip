package NUT.Ui;

import NUT.Task.*;
import java.util.Scanner;

/**
 * Handles interactions with the user.
 * Deals with reading user input and displaying messages.
 */

public class Ui {
    private final Scanner sc;

    /**
     * Constructs a new Ui object and initializes the scanner for reading user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the user's command from the console.
     * @return The user's input as a String.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the welcome message when the program starts.
     */
    public void showWelcome() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm NUT");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays the goodbye message when the program exits.
     */
    public void showGoodbye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Alrighty, bai bai \\(^-^)/");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays an error message when index searched is out of bound.
     */
    public void showSearchError() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Invalid task index, that doesn't exist darling :(");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays confirmation message when a task is added.
     * @param task The task that was added.
     * @param taskCount The total number of tasks in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task: " + task);
        System.out.println("    Now you have " + taskCount + " tasks in the list :)");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays confirmation message when a task is deleted.
     * @param task The task that was deleted.
     * @param taskCount The total number of tasks remaining in the list.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've removed this task: " + task);
        System.out.println("    Now you have " + taskCount + " tasks in the list :)");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays an error message to the user if the input is empty.
     */
    public void emptyError() {
        System.out.println("The description cannot be empty :(");
    }

    /**
     * Displays all tasks in the task list.
     * @param tasks The TaskList containing all tasks.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays a task marked confirmation message to the user.
     * @param tasks The TaskList containing all tasks.
     */
    public void markTask(TaskList tasks, int index) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    good job babes!");
        System.out.println("    Marked task: " + tasks.get(index));
        System.out.println("    ____________________________________________________________");
    }

}