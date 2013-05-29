package com.gdcolella.remotebot;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity implements FragmentManager.OnBackStackChangedListener {
    boolean showingConnect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_flip);
        Log.d("remotebot", "content view set..");

        if(savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new ConnectFragment())
                    .commit();
            showingConnect = true;
        }

        Log.d("remotebot", "transaction completed..");

        getFragmentManager().addOnBackStackChangedListener(this);



    }

    private void showConnect(){
  //      assert !showingConnect;
        showingConnect = true;
        getFragmentManager().popBackStack();
    }
    private void showControl(){
  //     assert showingConnect;
        showingConnect = false;
        getFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in , R.animator.card_flip_left_out)

                .replace(R.id.container, new ControlFragment())

                .addToBackStack(null)

                .commit();



    }

    @Override
    public void onBackStackChanged() {
       showingConnect = !(getFragmentManager().getBackStackEntryCount() > 0);
    }

    class ConnectFragment extends Fragment implements View.OnClickListener {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
            View toShow = inflater.inflate(com.gdcolella.remotebot.R.layout.fragment_connect,container,false);
            toShow.findViewById(R.id.connectbutton).setOnClickListener(this);
            return toShow;
        }

        @Override
        public void onClick(View view) {
            showControl();
        }
    }
    class ControlFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            return inflater.inflate(R.layout.fragment_control, container, false);
        }

    }


}
