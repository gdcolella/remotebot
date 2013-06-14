package com.gdcolella.remotebot;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Created by greg on 6/11/13.
 */
public class EddieController extends RobotController {
    Connection myConnection;

    static final String MOTOR_PREFIX = "mmr ";
    static final String FREEZE_PREFIX = "frz ";

    static final int LMOTOR = 1;
    static final int RMOTOR = 3;


    public EddieController(Connection in) {
        myConnection = in;

    }

    @Override
    public void setMotorPower(double lmotor, double rmotor) {
        try {
            myConnection.writeLine(MOTOR_PREFIX + " " + lmotor + " " + rmotor + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }

    }

    @Override
    public void freezeMotors() {
        try {
            myConnection.writeLine(FREEZE_PREFIX + " " + LMOTOR + " " + RMOTOR);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    @Override
    public void close() {
        myConnection.close();
    }

    @Override
    public boolean ready() {
        return true;
    }

}
