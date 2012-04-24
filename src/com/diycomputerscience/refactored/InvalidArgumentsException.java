package com.diycomputerscience.refactored;

public final class InvalidArgumentsException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidArgumentsException() {

    }

    public InvalidArgumentsException(String message) {
        super(message);
    }

    public InvalidArgumentsException(Throwable cause) {
        super(cause);
    }

    public InvalidArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }
}
