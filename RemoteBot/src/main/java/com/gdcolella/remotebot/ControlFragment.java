package com.gdcolella.remotebot;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Greg on 6/1/13.
 */
public class ControlFragment extends Fragment {

    JoystickHandler controller;
    JoyView leftJoystick;
    RobotHandler myRobot;
    MainActivity myMain;


    public ControlFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View out = inflater.inflate(R.layout.fragment_control, container, false);
        myMain = (MainActivity)getActivity();
        myRobot = new RobotHandler(new EddieController(myMain.myConnection));
        controller = new JoystickHandler(myRobot);
        leftJoystick = ((JoyView)out.findViewById(R.id.joystick1));

        leftJoystick.setJoystickListener(controller);

        return out;
    }



}