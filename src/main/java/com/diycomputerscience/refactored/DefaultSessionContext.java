package com.diycomputerscience.refactored;

import java.io.IOException;

public final class DefaultSessionContext implements SessionContext {
    private static final String DEFAULT_PASSWORD = "amdocs";
    private String userName;
    private boolean loggedIn;
    private final Transport transport;

    public DefaultSessionContext(Transport transport) {
        super();
        this.transport = transport;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void invalidateLogin() {
        this.loggedIn = false;
    }

    @Override
    public void writeError(String msg) {
        write("ERROR: " + msg);
    }

    @Override
    public void writeSuccess(String msg) {
        write("SUCCESS: " + msg);
    }

    private void write(String msg) {
        try {
            transport.write(msg + "!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean login(String password) {
        boolean result = DEFAULT_PASSWORD.equals(password);
        if (result) {
            loggedIn = true;
        }

        return result;
    }

    @Override
    public void closeSession() throws IOException {
        transport.close();
    }
}
