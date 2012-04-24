package com.diycomputerscience.refactored;

import java.io.IOException;

public interface SessionContext {
    String getUserName();

    void setUserName(String userName);

    boolean isLoggedIn();

    void setLoggedIn(boolean loggedIn);

    void invalidateLogin();

    void writeError(String msg);

    void writeSuccess(String msg);

    boolean login(String password);

    void closeSession() throws IOException;
}
