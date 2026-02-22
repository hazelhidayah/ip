package nut;

import nut.command.Command;
import nut.command.ByeCommand;
import nut.parser.Parser;
import nut.storage.Storage;
import nut.exception.NutException;
import nut.task.TaskList;
import nut.ui.Ui;

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
    private static final long EXIT_DELAY_MILLIS = 3000L;

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private boolean isExitRequested;

    /**
     * Constructs a Nut object with the default file path "nut.txt".
     */
    public Nut() {
        ui = new Ui();  // Create UI for displaying messages
        storage = new Storage("nut.txt");  // Create storage with a file path "nut.txt"

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
        System.out.println(ui.showCommandRundown());  // Show initial command guide
        boolean shouldExit = false;  // false = keep running, true = exit

        while (!shouldExit) {  // Loop until the user says "bye"
            try {
                String userInput = ui.readCommand();  // Read user's command
                Command command = Parser.parse(userInput, tasks);  // Parse input → create command
                String response = command.execute(ui);  // Execute command, returns response
                if (!response.isEmpty()) {
                    System.out.println(response);
                }
                shouldExit = command instanceof ByeCommand;
                storage.save(tasks);  // Save tasks to file after each command
                if (shouldExit) {
                    waitBeforeExit();
                }
            } catch (NutException e) {
                System.out.println(ui.showError(e.getMessage()));  // Show an error message if something goes wrong
            }
        }
    }

    /**
     * Main entry point of the CLI program.
     *
     * @param args Command-line arguments (not used).
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
            isExitRequested = false;
            Command command = Parser.parse(input, tasks);
            String response = command.execute(ui);
            storage.save(tasks);
            isExitRequested = command instanceof ByeCommand;
            return response;
        } catch (NutException e) {
            isExitRequested = false;
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Returns Nut's startup greeting message for GUI.
     *
     * @return The startup greeting message.
     */
    public String getWelcomeMessage() {
        return ui.showWelcome();
    }

    /**
     * Returns Nut's startup command guide for GUI.
     *
     * @return The startup command guide message.
     */
    public String getStartupGuideMessage() {
        return ui.showCommandRundown();
    }

    /**
     * Returns whether the most recently processed GUI command requested app exit.
     *
     * @return True if the last command was {@code bye}; false otherwise.
     */
    public boolean isExitRequested() {
        return isExitRequested;
    }

    /**
     * Returns the delay (in milliseconds) before exiting after {@code bye}.
     *
     * @return Exit delay in milliseconds.
     */
    public long getExitDelayMillis() {
        return EXIT_DELAY_MILLIS;
    }

    private void waitBeforeExit() {
        try {
            Thread.sleep(EXIT_DELAY_MILLIS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
