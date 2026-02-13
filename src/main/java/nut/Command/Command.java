package nut.Command;

import nut.Ui.Ui;

/**
 * This interface ensures every concrete command has an execute() method.
 * This guides the program to either continue or terminate.
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @param ui The Ui object for displaying messages to the user.
     * @return The response message to show to the user.
     */
    String execute(Ui ui);
}
