package NUT.Command;

import NUT.Ui.Ui;

/**
 * This interface ensures every concrete command has an execute() method.
 * This guides the program to either continue or terminate.
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @param ui The Ui object for displaying messages to the user.
     * @return true if program should exit, false otherwise.
     */
    boolean execute(Ui ui);
}
