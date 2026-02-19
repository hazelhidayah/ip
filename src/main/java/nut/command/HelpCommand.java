package nut.command;

import nut.ui.Ui;

/**
 * Command that displays a brief list of supported commands.
 */
public class HelpCommand implements Command {

    @Override
    public String execute(Ui ui) {
        return ui.showCommandRundown();
    }
}
