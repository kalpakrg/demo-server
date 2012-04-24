package com.diycomputerscience.refactored;

import java.io.IOException;
import java.util.List;

public enum Commands implements Command {
    USER() {
        @Override
        public boolean execute(SessionContext context, List<String> params) throws IOException {
            context.setUserName(params.get(0));
            context.invalidateLogin();
            context.getResponder().respondWithOk();
            return maintainSession();
        }

        @Override
        public int getNumberOfArguments() {
            return 1;
        }

        @Override
        public String getCommandName() {
            return "user";
        }
    },
    
    PASSWORD() {
        @Override
        public boolean execute(SessionContext context, List<String> params)  throws IOException {
            if (context.getUserName() == null) {
                context.getResponder().respondWithIssueUserCommandFirst();
                return maintainSession();
            }
            
            if (!context.login(params.get(0))) {
                context.getResponder().respondWithInvalidPassword();
            } else {
                context.getResponder().respondWithUserSuccessfullyLoggedIn(context.getUserName());
            }

            return maintainSession();
        }
        
        @Override
        public int getNumberOfArguments() {
            return 1;
        }

        @Override
        public String getCommandName() {
            return "pass";
        }
    },

    HELLO() {
        @Override
        public boolean execute(SessionContext context, List<String> params) throws IOException {
            if (!context.isLoggedIn()) {
                context.getResponder().respondWithLoginFirst();
            } else {
                context.getResponder().respondWithHelloToUser(context.getUserName());
            }

            return maintainSession();
        }

        @Override
        public int getNumberOfArguments() {
            return 0;
        }

        @Override
        public String getCommandName() {
            return "hello";
        }
    },
    
    BYE() {
        @Override
        public boolean execute(SessionContext context, List<String> params) throws IOException {
            if (context.isLoggedIn()) {
                context.getResponder().respondWithByeToUser(context.getUserName());
            } else {
                context.getResponder().respondWithBye();
            }

            context.closeSession();

            return terminateSession();
        }

        @Override
        public int getNumberOfArguments() {
            return 0;
        }

        @Override
        public String getCommandName() {
            return "bye";
        }
    };

    public static Command resolveCommandByName(String name) throws InvalidCommandException {
        for (Command c : Commands.values()) {
            if (c.getCommandName().equals(name)) {
                return c;
            }
        }

        throw new InvalidCommandException();
    }

    private static boolean maintainSession() {
        return true;
    }

    private static boolean terminateSession() {
        return false;
    }
}
