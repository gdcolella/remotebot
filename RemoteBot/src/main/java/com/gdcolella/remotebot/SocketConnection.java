package com.gdcolella.remotebot;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by greg on 5/27/13.
 */
public class SocketConnection extends Connection {
    public static final int DEFAULT_PORT = 7777;
    ConnectionInfo info;
    Socket mySocket;
    BufferedReader readStream;
    PrintWriter writeStream;

    public boolean close(){
        try {
        writeLine("END");
        mySocket.close();
        return true;
        } catch( IOException e){
            e.printStackTrace();
        }
        return false;
    }

    private SocketConnection(String ip, int port){
        info = new ConnectionInfo(ip,port);
    }

    public void connect() throws UnknownHostException, IOException {
        assert info != null;
        mySocket = new Socket(info.IP,info.port);
        readStream = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        writeStream = new PrintWriter(mySocket.getOutputStream());
    }

    public static Connection connectIP(String ipaddr, int port) throws UnknownHostException, IOException{
        SocketConnection out = prepareConnection(ipaddr, port);
        out.connect();
        return out;
    }

    //Creates a connection that must be opened by a call to connect
    public static SocketConnection prepareConnection(String ipaddr, int port){
        return new SocketConnection(ipaddr,port);
    }

    @Override
    public boolean isReady() throws IOException {
        return (readStream != null) && readStream.ready();
    }

    @Override
    public String readLine() throws IOException{
        return readStream.readLine();
    }

    @Override
    public boolean writeLine(String toWrite) {
        writeStream.write(toWrite + "\n");
        writeStream.flush();
        return true;
    }


    static class ConnectionInfo {
        public final String IP;
        public final int port;
        public ConnectionInfo(String ip, int port){
            this.IP = ip;
            this.port = port;
        }
    }

}
