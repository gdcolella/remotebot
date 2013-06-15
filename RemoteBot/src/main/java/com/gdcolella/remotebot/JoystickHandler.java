package com.gdcolella.remotebot;

import android.util.Log;

/**
 * Created by Greg on 6/4/13.
 */
public class JoystickHandler implements JoyView.JoystickListener {

    RobotHandler myRobot;

    @Override
    public void onChange(float dx, float dy) {

        Log.d("remotebot", "Handler request: " + dx + " " + -1 * dy);
        //myRobot.request(dy * dx, -1*(dy*dx));
        myRobot.request(dx,dy);
    }

    public JoystickHandler(RobotHandler in){
        myRobot = in;
    }
}
