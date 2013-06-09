package com.gdcolella.remotebot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Greg on 6/8/13.
 */
public class DebugConnection extends Connection {
    List<String> queuedMessages;

    InputThread myInputThread;

    public DebugConnection(){
        queuedMessages = Collections.synchronizedList(new ArrayList<String>());
    }

    class InputThread implements Runnable {

        Scanner myScanner;
        volatile boolean shouldContinue;

        public InputThread(){
            myScanner = new Scanner(System.in);
            shouldContinue = true;
        }

        @Override
        public void run() {
            while(shouldContinue){
                queuedMessages.add(myScanner.nextLine());
            }
        }

        public void halt(){
            shouldContinue = false;
        }

    }

    @Override
    public boolean isReady() throws IOException {
        return !queuedMessages.isEmpty();
    }

    @Override
    public String readLine() throws IOException {
        return queuedMessages.remove(0);
    }

    @Override
    public boolean writeLine(String toWrite) throws IOException {
        System.out.println(toWrite);
        return true;
    }
}
