package com.diycomputerscience.refactored;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public final class DefaultCommandParser implements CommandParser {

    private final Transport transport;
    
    public DefaultCommandParser(Transport transport) {
        super();
        this.transport = transport;
    }

    @Override
    public CommandContext nextCommand() throws IOException, InvalidCommandException, InvalidArgumentsException {
        String commandString = transport.readLine();

        if (commandString == null || commandString.trim().length() == 0) {
            throw new InvalidCommandException();
        }

        String [] tokens = commandString.split(" ");

        Command command = Commands.resolveCommandByName(tokens[0]);

        if (command.getNumberOfArguments() != (tokens.length - 1)) {
            throw new InvalidArgumentsException();
        }

        List<String> params = tokens.length > 1? Arrays.asList(tokens).subList(1, tokens.length) : Arrays.asList(tokens);
        return new CommandContext(command, params);
    }

}
