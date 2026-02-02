package nut;

import nut.Command.*;
import nut.Parser.*;
import nut.Storage.*;
import nut.Task.*;
import nut.Ui.*;

/**
 * Main program class for nut task manager.
 */
public class NUT {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a nut object with the specified file path.
     *
     * @param filePath The path to the file for storing tasks.
     */
    public NUT(String filePath) {
        ui = new Ui();  // Create UI for displaying messages
        storage = new Storage(filePath);  // Create Storage with file path

        try {
            tasks = new TaskList(storage.load());  // load tasks from file into TaskList
        } catch (NutException e) {
            ui.noFileError();
            tasks = new TaskList();  // start with empty list if loading fails
        }
    }

    /**
     * Runs the main program loop.
     */
    public void run() {
        ui.showWelcome();  // Show welcome message
        boolean status = false;  // false = keep running, true = exit

        while (!status) {  // Loop until user says "bye"
            try {
                String userInput = ui.readCommand();  // Read user's command
                Command command = Parser.parse(userInput, tasks);  // Parse input → create Command
                status = command.execute(ui);  // Execute command, returns true if "bye"
                storage.save(tasks);  // Save tasks to file after each command
            } catch (NutException e) {
                ui.showError(e.getMessage());  // Show error message if something goes wrong
            }
        }
    }

    /**
     * Main entry point of the program.
     */
    public static void main(String[] args) {
        new NUT("nut.txt").run();  // Create nut with "nut.txt" as data file, then run
    }
}