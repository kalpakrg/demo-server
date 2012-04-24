package com.diycomputerscience.refactored;

import java.io.IOException;
import java.util.List;

public final class ProtocolHandler implements Runnable {
    private final CommandParser commandParser;
    private final SessionContext session;
    
    public ProtocolHandler(CommandParser commandParser, SessionContext session) {
        super();
        this.commandParser = commandParser;
        this.session = session;
    }

    @Override
    public void run() {
        boolean sessionIsValid = true;
        while (sessionIsValid) {
            try {
                CommandContext commandContext = commandParser.nextCommand();
                Command command = commandContext.getCommand();
                List<String> params = commandContext.getParams();
                sessionIsValid = command.execute(session, params);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidCommandException e) {
                session.getResponder().respondWithInvalidCommand();
            } catch (InvalidArgumentsException e) {
                session.getResponder().respondWithInvalidParameters();
            } catch (Throwable t) {
                t.printStackTrace();
                throw new RuntimeException(t);
            }
        }
    }
}
