package com.gdcolella.remotebot;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Greg on 6/1/13.
 */
public class ConnectFragment extends Fragment implements View.OnClickListener {
    public ConnectFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        View toShow = inflater.inflate(com.gdcolella.remotebot.R.layout.fragment_connect,container,false);
        toShow.findViewById(R.id.connectbutton).setOnClickListener(this);
        return toShow;
    }

    @Override
    public void onClick(View view) {
        ((MainActivity)getActivity()).showControl();
    }
}