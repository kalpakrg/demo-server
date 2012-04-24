package com.diycomputerscience.legacy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public final class ProtocolHandler implements Runnable {
    private static final String USER_COMMAND = "user";
    private static final String HELLO_COMMAND = "hello";
    private static final String BYE_COMMAND = "bye";
    private static final String PASSWORD_COMMAND = "pass";

    private static final String DEFAULT_PASSWORD = "amdocs";

    private static final String RESPONSE_OK = "Ok.\n";

    private final BufferedReader in;
    private final BufferedWriter out;
    private final Socket s;
    private String username;
    private boolean loggedin;

    public ProtocolHandler(Socket s) throws IOException {
        super();
        this.s = s;
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
    }

    @Override
    public void run() {
        while (true) {
            try {
                String command = in.readLine();

                if (command == null) {
                    out.write("Enter a valid command!");
                    out.flush();
                    continue;
                }

                if (command.startsWith(USER_COMMAND)) {
                    String [] tokens = command.trim().split(" ");
                    if (tokens != null && tokens.length != 2) {
                        out.write("Invalid Parameters!\n");
                        out.flush();
                    } else {
                        username = tokens[1];
                        loggedin = false;
                        out.write(RESPONSE_OK);
                        out.flush();
                    }
                } else if (command.startsWith(PASSWORD_COMMAND)) {
                    if (username == null) {
                        out.write("Issue user command first!\n");
                        out.flush();
                        continue;
                    }

                    String tokens [] = command.trim().split(" ");
                    if (tokens != null && tokens.length != 2) {
                        out.write("Invalid parameters!\n");
                        out.flush();
                        continue;
                    }

                    if (tokens[1] != null && DEFAULT_PASSWORD.equals(tokens[1])) {
                        out.write(RESPONSE_OK);
                        out.flush();
                        loggedin = true;
                    } else {
                        out.write("Invalid Password!\n");
                        out.flush();
                    }
                } else if (command.startsWith(HELLO_COMMAND)) {
                    if (!loggedin) {
                        out.write("Login first.\n");
                        out.flush();
                        continue;
                    } else {
                        out.write("Hello " + username + "!!\n");
                        out.write(RESPONSE_OK);
                        out.flush();
                    }
                } else if (command.startsWith(BYE_COMMAND)) {
                    out.write("Have a nice day" + (loggedin? " " + username : "") + "!\n");
                    out.flush();
                    s.close();
                    break;
                } else {
                    out.write("Invalid Command!\n");
                    out.flush();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
                break;
            }
        }
    }
}
