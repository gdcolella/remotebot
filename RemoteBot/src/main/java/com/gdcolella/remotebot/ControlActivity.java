package com.gdcolella.remotebot;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by greg on 6/14/13.
 */
public class ControlActivity extends Activity {
    JoystickHandler controller;
    JoyView leftJoystick;
    RobotHandler myRobot;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        myRobot = new RobotHandler(new BotballController(Data.defaultData().myConnection));
        controller = new JoystickHandler(myRobot);
        leftJoystick = ((JoyView)findViewById(R.id.joystick1));

        leftJoystick.setJoystickListener(controller);
    }

}
