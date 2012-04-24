package com.diycomputerscience.refactored;

import java.io.IOException;

public final class DefaultResponder implements Responder {
    private final Transport transport;
    
    public DefaultResponder(Transport transport) {
        super();
        this.transport = transport;
    }

    @Override
    public void respondWithUserSuccessfullyLoggedIn(String user) {
        writeSuccess("User " + user + " successfully logged in");
    }

    @Override
    public void respondWithIssueUserCommandFirst() {
        writeError("Issue \"user\" command first");
    }

    @Override
    public void respondWithHelloToUser(String user) {
        writeSuccess("Hello " + user);
    }

    @Override
    public void respondWithByeToUser(String user) {
        writeSuccess("Have a nice day "+ user);
    }

    @Override
    public void respondWithBye() {
        writeSuccess("Have a nice day");
    }
    
    @Override
    public void respondWithLoginFirst() {
        writeError("Login first");
    }

    @Override
    public void respondWithInvalidCommand() {
        writeError("Invalid command");
    }

    @Override
    public void respondWithInvalidParameters() {
        writeError("Invalid parameters");
    }

    @Override
    public void respondWithInvalidPassword() {
        writeError("Invalid password");
    }

    @Override
    public void respondWithOk() {
        writeSuccess("Ok");
    }

    private void writeError(String msg) {
        write("ERROR: " + msg);
    }

    private void writeSuccess(String msg) {
        write("SUCCESS: " + msg);
    }

    private void write(String msg) {
        try {
            transport.write(msg + "!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
