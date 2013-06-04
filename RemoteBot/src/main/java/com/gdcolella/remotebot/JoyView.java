package com.gdcolella.remotebot;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Greg on 6/1/13.
 */
public class JoyView extends View implements View.OnTouchListener {
    Paint myPaint;
    JoystickListener report;

    float centerx = getWidth()/2, centery = getHeight() /2, x = centerx,y = centery, rd;

    public JoyView(Context context){
        this(context,null);
    }

    public void setJoystickListener(JoystickListener in){
        report = in;
    }

    public JoyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        myPaint = new Paint();
        myPaint.setColor(Color.GREEN);
        myPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        setOnTouchListener(this);

        rd = getWidth()/8;
    }

    public void center(){
        setXPos(centerx);
        setYPos(centery);
        updateListener();
    }
    public void updateListener(){

        if(report != null){
            report.onChange(x,y);
        }
    }

    @Override
    public void onDraw(Canvas canvas){
        myPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getWidth()/2, getHeight()/2, getWidth()/2, myPaint);

        myPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y, getWidth()/8, myPaint);

    }

    private void setXPos(float inX){
        x = (inX<rd)?rd:(inX>(getWidth()-rd))?getWidth()-rd:inX;
        //x = inX;
    }

    private void setYPos(float inY){
        y = (inY<rd)?rd:(inY>(getHeight()-rd))?getHeight()-rd:inY;
        //y = inY;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.d("remotebot", "Touch received: "+motionEvent.getX()+" : "+motionEvent.getY()+" height: "+getHeight()+" width: "+getWidth()+" radius: "+rd);
        setXPos(motionEvent.getX());
        setYPos(motionEvent.getY());

        updateListener();

        invalidate();
        return true;
    }

    interface JoystickListener {
        void onChange(float dx, float dy);
    }
}
