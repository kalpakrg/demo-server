package com.diycomputerscience.refactored;

import java.io.IOException;

public interface CommandParser {
    public CommandContext nextCommand() throws IOException, InvalidCommandException, InvalidArgumentsException;
}
