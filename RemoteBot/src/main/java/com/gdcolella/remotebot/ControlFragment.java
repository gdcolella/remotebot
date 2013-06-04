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

    JoyView leftJoystick;

    public ControlFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){



        return inflater.inflate(R.layout.fragment_control, container, false);
    }

}