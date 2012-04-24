package com.diycomputerscience.refactored;

import java.io.IOException;

public interface Transport {
    void write(String msg) throws IOException;
    void close() throws IOException;
    String readLine() throws IOException;
}
