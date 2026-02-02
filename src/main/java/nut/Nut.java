package nut;

import nut.Command.Command;
import nut.Parser.Parser;
import nut.Storage.Storage;
import nut.Task.TaskList;
import nut.Task.NutException;
import nut.Ui.Ui;

/**
 * Main program class for Nut task manager.
 */
public class Nut {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Nut object with the specified file path.
     *
     * @param filePath The path to the file for storing tasks.
     */
    public Nut(String filePath) {
        ui = new Ui();  // Create UI for displaying messages
        storage = new Storage(filePath);  // Create Storage with a file path

        try {
            tasks = new TaskList(storage.load());  // load tasks from a file into a TaskList
        } catch (NutException e) {
            ui.noFileError();
            tasks = new TaskList();  // start with an empty list if loading fails
        }
    }

    /**
     * Runs the main program loop.
     */
    public void run() {
        ui.showWelcome();  // Show welcome message
        boolean status = false;  // false = keep running, true = exit

        while (!status) {  // Loop until the user says "bye"
            try {
                String userInput = ui.readCommand();  // Read user's command
                Command command = Parser.parse(userInput, tasks);  // Parse input → create Command
                status = command.execute(ui);  // Execute command, returns true if "bye"
                storage.save(tasks);  // Save tasks to file after each command
            } catch (NutException e) {
                ui.showError(e.getMessage());  // Show an error message if something goes wrong
            }
        }
    }

    /**
     * Main entry point of the program.
     */
    public static void main(String[] args) {
        new Nut("nut.txt").run();  // Create a Nut object with "Nut.txt" as a data file, then run
    }
}