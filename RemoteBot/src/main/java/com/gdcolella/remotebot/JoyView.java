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

    float centerx = getWidth()/2, centery = getHeight() /2, x = centerx,y = centery, rd = getWidth()/2, radius = centerx;


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
    }

    public void center(){
        setPos(centerx,centery);
        updateListener();
    }
    public void updateListener(){

        if(report != null){
            float xPercent = (float)((x-centerx)/getWidth() - .5) * 2f;
            float yPercent = (float)((y-centery)/getHeight() - .5) * 2f;
            Log.d("remotebot","Joystick reporting: "+xPercent+ " "+yPercent);
            report.onChange(xPercent, yPercent);
        }
    }

    @Override
    public void onDraw(Canvas canvas){
        myPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getWidth()/2, getHeight()/2, getWidth()/2, myPaint);

        myPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y, getWidth()/8, myPaint);

    }
    private double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt(((x1-x2)*(x1-x2)) + ((y1-y2)*(y1-y2)));
    }
    private boolean within(double inX, double inY){
        return distance(centerx,centery,inX,inY) < radius;
    }

    private void setPos(float inX, float inY){
 /*       if(within(inX,inY)){
            this.x = inX;
            this.y = inY;
        }
        else {
            double theta = Math.atan2(inY,inX);

            x = (float)(Math.cos(theta) * radius);
            y = (float)(Math.sin(theta) * radius);
        }
        */
        this.x = inX;
        this.y = inY;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
     //   Log.d("remotebot", "Touch received: "+motionEvent.getX()+" : "+motionEvent.getY()+" height: "+getHeight()+" width: "+getWidth()+" radius: "+rd);
        setPos(motionEvent.getX() ,motionEvent.getY());

        updateListener();

        invalidate();
        return true;
    }

    interface JoystickListener {
        void onChange(float dx, float dy);
    }
}
