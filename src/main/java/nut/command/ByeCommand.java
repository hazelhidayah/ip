package nut.command;

import nut.ui.Ui;

/**
 * command that ends the application session.
 * <p>
 * When executed, this command displays a goodbye message via the {@link Ui}
 * and signals to the caller that the program should terminate.
 * </p>
 */
public class ByeCommand implements Command {

    @Override
    public String execute(Ui ui) {
        return ui.showGoodbye();
    }

}
