package edu.ptu.androidtouchanalyse;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by WangAnshu on 2016/1/27.
 */
public class TouchTextView extends TextView {
    //---------------
    public TouchTextView(Context context) {
        super(context);
    }

    public TouchTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TouchTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);
        System.out.println("===]]] TouchTextView onTouchEvent " + MotionEventName.ACTION_NAME[event.getAction()] + "   " + b);
        return b;
    }
}
