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
        return "Hey there lil acorn :)";
    }

    /**
     * Displays the welcome message when the program starts.
     */
    public String showWelcome() {
        return "Hello! I'm Nut.";
    }

    /**
     * Displays a brief help/rundown of supported commands.
     */
    public String showCommandRundown() {
        return """
                Here are some quick start commands:
                - todo <task>
                - deadline <task> /by <dd/MM/yyyy [HHmm]>
                - event <task> /from <start> /to <end>
                - list
                - find <keyword>
                - mark <task number>
                - unmark <task number>
                - delete <task number>
                - help
                - bye
                """;
    }

    /**
     * Displays the goodbye message when the program exits.
     */
    public String showGoodbye() {
        return "Shell you later. Stay cracking \\(^-^)/";
    }

    /**
     * Displays an error message when the index searched is out of bound.
     */
    public String showSearchError() {
        return "That task index is out of shell range.";
    }

    /**
     * Displays an error message when the task index is invalid.
     *
     * @param taskCount The total number of tasks in the list.
     */
    public String showInvalidTaskIndex(int taskCount) {
        if (taskCount <= 0) {
            return "Your nut stash is empty right now.";
        }
        return "Pick a task number between 1 and " + taskCount + ", little acorn.";
    }

    /**
     * Displays user's search results
     *
     * @param list The list to search through.
     * @param searchTerm The search term used.
     */
    public String showSearchResults(TaskList list, String searchTerm) {
        StringBuilder result = new StringBuilder();
        boolean hasMatch = false;

        for (int i = 0; i < list.size(); i++) { // loop through all tasks
            if (list.get(i).getName().contains(searchTerm)) { // if the task name contains the search term
                if (!hasMatch) {
                    result.append("Here are the matching tasks in your nut stash:\n");
                    hasMatch = true;
                }
                result.append("    ").append(i + 1).append(".").append(list.get(i)).append("\n");
            }
        }
        if (!hasMatch) {
            return "No results found for \"" + searchTerm + "\".";
        }
        return result.toString().trim();
    }

    /**
     * Displays a confirmation message when a task is added.
     * @param task The task that was added.
     * @param taskCount The total number of tasks in the list.
     */
    public String showTaskAdded(Task task, int taskCount) {
        return "Cracked it. I've added this task: " + task + "\n"
                + "Now you have " + taskCount + " tasks in your stash.";
    }

    /**
     * Prompts the user to confirm adding a duplicate task.
     *
     * @param task The duplicate task the user attempted to add.
     */
    public String showDuplicateTaskPrompt(Task task) {
        return "I already spotted a similar task:\n" +
                "[ " + task.getName() + "]\n" +
                "Still add the new one? (yes/no)";
    }

    /**
     * Displays a confirmation message when adding a duplicate task is cancelled.
     *
     * @param task The duplicate task that was not added.
     */
    public String showDuplicateTaskCancelled(Task task) {
        return "Alright, I shelved this and did not add it: " + task;
    }

    /**
     * Displays a confirmation message when a task is deleted.
     * @param task The task that was deleted.
     * @param taskCount The total number of tasks remaining in the list.
     */
    public String showTaskDeleted(Task task, int taskCount) {
        return "Shell removed. I deleted this task: " + task + "\n"
                + "Now you have " + taskCount + " tasks in your stash.";
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
        return "That description is emptier than a cracked shell.";
    }

    /**
     * Displays an error message to the user if no file is found.
     */
    public String noFileError() {
        return "No task file found. Starting with a fresh nut stash.";
    }


    /**
     * Displays all tasks in the task list.
     *
     * @param tasks The TaskList containing all tasks.
     */
    public String showTaskList(TaskList tasks) {
        StringBuilder result = new StringBuilder();
        if (tasks.size() == 0) {
            return "Your nut stash is empty!";
        }

        result.append("Here are the tasks in your nut stash:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append("    ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return result.toString().trim();
    }

    /**
     * Displays a task marked confirmation message to the user.
     *
     * @param tasks The TaskList containing all tasks.
     * @param index The index of the task that was updated.
     * @return A confirmation message with the updated task details.
     */
    public String markTask(TaskList tasks, int index) {
        return "Nut noted.\n"
                + "Updated task: " + tasks.get(index);
    }

}
