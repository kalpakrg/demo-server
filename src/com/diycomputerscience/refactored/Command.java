package com.diycomputerscience.refactored;

import java.io.IOException;
import java.util.List;

public interface Command {
    boolean execute(SessionContext context, List<String> params) throws IOException;
    int getNumberOfArguments();
    String getCommandName();
}
