package nut;

import nut.Command.Command;
import nut.Command.ByeCommand;
import nut.Parser.Parser;
import nut.Storage.Storage;
import nut.Task.NutException;
import nut.Task.TaskList;
import nut.Ui.Ui;

/**
 * <p> Main program class for Nut task manager.
 * This class represents the core logic of the application, managing the task list,
 * storage operations, and user interface interactions. It supports both CLI and GUI modes.
 * </p>
 * The Nut class is responsible for:
 * <ul>
 *     <li> Initializing storage and loading saved tasks. </li>
 *     <li> Processing user commands through the parser. </li>
 *     <li> Managing the task list and persisting changes. </li>
 *     <li> Generating responses for the GUI. </li>
 * </ul>
 */
public class Nut { // app’s core logic
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Nut object with the default file path "nut.txt".
     */
    public Nut() {
        ui = new Ui();  // Create UI for displaying messages
        storage = new Storage("nut.txt");  // Create Storage with file path "nut.txt"

        try {
            tasks = new TaskList(storage.load());  // load tasks from a file into the TaskList
        } catch (NutException e) {
            System.out.println(ui.noFileError());
            tasks = new TaskList();  // start with an empty list if loading fails
        }
    }

    /**
     * Runs the main program loop (CLI mode).
     */
    public void run() {
        System.out.println(ui.showWelcome());  // Show welcome message
        boolean status = false;  // false = keep running, true = exit

        while (!status) {  // Loop until the user says "bye"
            try {
                String userInput = ui.readCommand();  // Read user's command
                Command command = Parser.parse(userInput, tasks);  // Parse input → create Command
                String response = command.execute(ui);  // Execute command, returns response
                if (!response.isEmpty()) {
                    System.out.println(response);
                }
                status = command instanceof ByeCommand;
                storage.save(tasks);  // Save tasks to file after each command
            } catch (NutException e) {
                System.out.println(ui.showError(e.getMessage()));  // Show an error message if something goes wrong
            }
        }
    }

    /**
     * Main entry point of the CLI program.
     */
    public static void main(String[] args) {
        new Nut().run();  // Create nut with "nut.txt" as a data file, then run
    }

    /**
     * Generates a response for the user's chat message (GUI mode).
     *
     * @param input User input.
     * @return Nut's response.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input, tasks);
            String response = command.execute(ui);
            storage.save(tasks);
            return response;
        } catch (NutException e) {
            return ui.showError(e.getMessage());
        }
    }
}
