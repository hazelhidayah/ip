package nut.ui;

import nut.task.TaskList;
import nut.task.Task;
import java.util.Scanner;

/**
 * Handles interactions with the user.
 * Deals with reading user input and displaying messages.
 */

public class Ui {
    private final Scanner sc;

    /**
     * Constructs a new ui object and initializes the scanner for reading user input.
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
     * Displays a greeting when the user says hello
     */
    public String showHello() {
        return "Hello <3";
    }

    /**
     * Displays the welcome message when the program starts.
     */
    public String showWelcome() {
        return "Hello! I'm Nut\n" +
                "What can I do for you?";
    }

    /**
     * Displays the goodbye message when the program exits.
     */
    public String showGoodbye() {
        return " Alrighty, bai bai \\(^-^)/";
    }

    /**
     * Displays an error message when the index searched is out of bound.
     */
    public String showSearchError() {
        return "Invalid task index, that doesn't exist darling :(";
    }

    /**
     * Displays an error message when the task index is invalid.
     *
     * @param taskCount The total number of tasks in the list.
     */
    public String showInvalidTaskIndex(int taskCount) {
        if (taskCount <= 0) {
            return "There are no tasks in the list.";
        }
        return "Please enter a task number between 1 and " + taskCount + ".";
    }

    /**
     * Displays user's search results
     *
     * @param list The list to search through.
     * @param searchTerm The search term used.
     */
    public String showSearchResults(TaskList list, String searchTerm) {
        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < list.size(); i++) { // loop through all tasks
            if (list.get(i).getName().contains(searchTerm)) { // if the task name contains the search term
                result.append("    ").append(i + 1).append(".").append(list.get(i)).append("\n");
            }
        }
        return result.toString().trim();
    }

    /**
     * Displays a confirmation message when a task is added.
     * @param task The task that was added.
     * @param taskCount The total number of tasks in the list.
     */
    public String showTaskAdded(Task task, int taskCount) {
        return "Got it. I've added this task: " + task + "\n"
                + "Now you have " + taskCount + " tasks in the list :)";
    }

    /**
     * Prompts the user to confirm adding a duplicate task.
     *
     * @param task The duplicate task the user attempted to add.
     */
    public String showDuplicateTaskPrompt(Task task) {
        return "You already have a similar task: \n" +
                "[ " + task.getName() + "]\n" +
                "Are you sure you want to add your new task? (yes/no)";
    }

    /**
     * Displays a confirmation message when adding a duplicate task is cancelled.
     *
     * @param task The duplicate task that was not added.
     */
    public String showDuplicateTaskCancelled(Task task) {
        return "Okay, task: " + task + " not added";
    }

    /**
     * Displays a confirmation message when a task is deleted.
     * @param task The task that was deleted.
     * @param taskCount The total number of tasks remaining in the list.
     */
    public String showTaskDeleted(Task task, int taskCount) {
        return "Got it. I've removed this task: " + task + "\n"
                + "Now you have " + taskCount + " tasks in the list :)";
    }

    /**
     * Displays an error message to the user.
     * @param errorMessage The error message to display.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Displays an error message to the user if the input is empty.
     */
    public String emptyError() {
        return "The description cannot be empty :(";
    }

    /**
     * Displays an error message to the user if no file is found.
     */
    public String noFileError() {
        return "No files found";
    }


    /**
     * Displays all tasks in the task list.
     *
     * @param tasks The TaskList containing all tasks.
     */
    public String showTaskList(TaskList tasks) {
        StringBuilder result = new StringBuilder();
        if (tasks.size() == 0) {
            return "Your task list is empty!";
        }

        result.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append("    ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return result.toString().trim();
    }

    /**
     * Displays a task marked confirmation message to the user.
     *
     * @param tasks The TaskList containing all tasks.
     */
    public String markTask(TaskList tasks, int index) {
        return "Good job babe!\n"
                + "Marked task: " + tasks.get(index);
    }

}
