package com.gdcolella.remotebot;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Greg on 5/28/13.
 */
class JoystickView extends SurfaceView implements Runnable {
    volatile boolean running;
    Thread drawingThread;
    SurfaceHolder myHolder;

    double x;
    double y;

    public double getJoyX(){
        return x;
    }
    public double getJoyY(){
        return y;
    }

    public JoystickView(Context context, AttributeSet attrs) {
        super(context);
        myHolder = getHolder();

        running = true;
        drawingThread = new Thread(this);
        drawingThread.start();

    }
/*
    private void init(Context ctx, AttributeSet attrs){

    }
*/
    @Override
    public void run() {
        Log.d("remotebot","Began drawing");
        Paint drawPaint = new Paint();
        while(running){
            if(!myHolder.getSurface().isValid())
                continue;
            Canvas c = myHolder.lockCanvas();
            drawPaint.setColor(Color.GREEN);
            c.drawCircle(c.getHeight()/2,c.getWidth()/2,c.getHeight()/2,drawPaint);
            c.drawRect((float)1,(float)1,(float)(c.getWidth()-1),(float)(c.getHeight()-1),drawPaint);
            myHolder.unlockCanvasAndPost(c);
        }
    }
}