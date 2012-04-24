package com.diycomputerscience.refactored;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public final class SocketTransport implements Transport {
    private final BufferedWriter writer;
    private final BufferedReader reader;
    private final Socket socket;

    public SocketTransport(Socket socket) throws IOException {
        super();
        this.socket = socket;
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void write(String msg) throws IOException {
        writer.write(msg);
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }

    @Override
    public String readLine() throws IOException {
        return reader.readLine();
    }
}
