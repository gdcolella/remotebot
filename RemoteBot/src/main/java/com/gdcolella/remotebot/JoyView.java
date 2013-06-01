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
    float x,y;
    public JoyView(Context context){
        this(context,null);
    }
    public JoyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        myPaint = new Paint();
        myPaint.setColor(Color.GREEN);
        myPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        setOnTouchListener(this);
        x = getWidth()/2;
        y = getHeight()/2;
    }

    @Override
    public void onDraw(Canvas canvas){
        canvas.drawCircle(x, y, getHeight()/8, myPaint);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.d("remotebot", "Touch received: "+x+" : "+y);
        x = motionEvent.getX();
        y = motionEvent.getY();
        invalidate();
        return false;
    }
}
