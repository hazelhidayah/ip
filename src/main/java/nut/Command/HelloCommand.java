package nut.Command;

import nut.Ui.Ui;

public class HelloCommand implements Command {

    @Override
    public boolean execute(Ui ui) {
        ui.showHello();
        return false;
    }
}