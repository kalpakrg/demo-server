package com.diycomputerscience.refactored;

import java.util.List;

public final class CommandContext {
    private final Command command;
    private final List<String> params;

    public CommandContext(Command command, List<String> params) {
        super();
        this.command = command;
        this.params = params;
    }

    public Command getCommand() {
        return command;
    }

    public List<String> getParams() {
        return params;
    }
}
