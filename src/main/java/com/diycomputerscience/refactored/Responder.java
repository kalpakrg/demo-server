package com.diycomputerscience.refactored;

public interface Responder {
    void respondWithUserSuccessfullyLoggedIn(String user);
    void respondWithIssueUserCommandFirst();
    void respondWithHelloToUser(String user);
    void respondWithByeToUser(String user);
    void respondWithBye();
    void respondWithLoginFirst();
    void respondWithInvalidCommand();
    void respondWithInvalidParameters();
    void respondWithOk();
    void respondWithInvalidPassword();
}
