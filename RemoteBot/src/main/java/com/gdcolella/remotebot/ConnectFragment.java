package com.gdcolella.remotebot;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Created by Greg on 6/1/13.
 */
public class ConnectFragment extends Fragment implements View.OnClickListener {

    EditText input;
    final int PORTNO = 7777;

    public ConnectFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        View toShow = inflater.inflate(com.gdcolella.remotebot.R.layout.fragment_connect,container,false);
        toShow.findViewById(R.id.connectbutton).setOnClickListener(this);
        input = (EditText)toShow.findViewById(R.id.editText);
        return toShow;
    }

    @Override
    public void onClick(View view) {
       TryConnect taskAttempt = new TryConnect();
       taskAttempt.execute(new SocketConnection.ConnectionInfo(input.getText().toString().trim(), PORTNO));
    }

    class TryConnect extends AsyncTask<SocketConnection.ConnectionInfo, Void, Void> {
        MainActivity act = ((MainActivity)getActivity());

        @Override
        protected Void doInBackground(SocketConnection.ConnectionInfo... connectionInfos) {
            String connectIP = connectionInfos[0].IP;
            int port = connectionInfos[0].port;
            SocketConnection cnct = SocketConnection.prepareConnection(input.getText().toString(), PORTNO );

            try {
                cnct.connect();
                act.myConnection = cnct;
                act.showControl();
            }catch(UnknownHostException e){
                Toast.makeText(getActivity().getApplicationContext(), "Unknown Host", 50);
            }catch( IOException e){
                Log.e("remotebot",e.toString());
                Toast.makeText(getActivity().getApplicationContext(), "Unknown Error", 50);
            }
            return null;
        }
    }
}