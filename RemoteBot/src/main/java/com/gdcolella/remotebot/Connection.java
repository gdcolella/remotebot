package com.gdcolella.remotebot;

import java.io.IOException;

/**
 * Created by greg on 5/27/13.
 */
public abstract class Connection {

    public abstract boolean isReady() throws IOException;
    public abstract String readLine() throws IOException;

    // should append a newline
    public abstract boolean writeLine(String toWrite) throws IOException;
}
