package com.gdcolella.remotebot;

/**
 * Created by Greg on 6/4/13.
 */
public abstract class RobotController {
    //Accepts PERCENTAGE powers for left and right motors
    public abstract void setMotorPower(double lmotor, double rmotor);
    public abstract void freezeMotors();
    public abstract void close();
}
