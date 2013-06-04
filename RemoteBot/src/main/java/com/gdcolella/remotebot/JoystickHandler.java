package com.gdcolella.remotebot;

/**
 * Created by Greg on 6/4/13.
 */
public class JoystickHandler implements JoyView.JoystickListener {

    RobotHandler myRobot;

    @Override
    public void onChange(float dx, float dy) {

        myRobot.request(dy * dx, -1*(dy*dx));
    }

    public JoystickHandler(RobotHandler in){
        myRobot = in;
    }
}
