package com.gdcolella.remotebot;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Created by greg on 6/14/13.
 */
public class ConnectActivity extends Activity {

    EditText ipAddress;
    Button connectButton;

    protected void switchControl(){
        Intent switchControl = new Intent(this, ControlActivity.class);
        startActivity(switchControl);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        closeConnection();

        setContentView(R.layout.activity_connect);

        ipAddress = (EditText)findViewById(R.id.editText);
        connectButton = (Button)findViewById(R.id.connectbutton);

        connectButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 attemptConnection();
            }
        });

        Log.d("remotebot", "initialized main view..");
    }

    @Override
    public void onResume(){
        super.onResume();

        if(Data.defaultData() != null && Data.defaultData().myConnection != null)
            Data.defaultData().myConnection.close();
    }

    public void closeConnection(){
        if(Data.defaultData().myConnection != null)
            Data.defaultData().myConnection.close();

    }


    public void attemptConnection(){
        TryConnect cnct = new TryConnect();
        cnct.execute(new SocketConnection.ConnectionInfo(ipAddress.getText().toString().trim(), SocketConnection.DEFAULT_PORT));
    }

    class TryConnect extends AsyncTask<SocketConnection.ConnectionInfo, Void, Boolean> {
        @Override
        protected Boolean doInBackground(SocketConnection.ConnectionInfo... connectionInfos) {
            String connectIP = connectionInfos[0].IP;
            int port = connectionInfos[0].port;
            SocketConnection cnct = SocketConnection.prepareConnection(connectIP, port);

            try {
                cnct.connect();
                Data.defaultData().myConnection = cnct;

                return Boolean.TRUE;
            }catch(UnknownHostException e){
             //   Toast.makeText(getApplicationContext(), "Unknown Host", 50);
            }catch( IOException e){
                Log.e("remotebot",e.toString());
              //  Toast.makeText(getApplicationContext(), "Unknown Error", 50);
            }
            return Boolean.FALSE;
        }

        @Override
        protected void onPostExecute(Boolean result){
            if(result)
                switchControl();
            else
                Toast.makeText(ConnectActivity.this ,"Connection Issue..", 50);
        }
    }

}
