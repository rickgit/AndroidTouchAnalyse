package edu.ptu.androidtouchanalyse;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

/**
 * Created by WangAnshu on 2016/1/27.
 */
public class TouchFrameLayout extends FrameLayout {
    float[] touchLastPoint = new float[2];//touchLastX touchLastY
    int touchPointIndexX = 0;
    int touchPointIndexY = 1;
    private int[] eventTypes = new int[]{MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL};
    private String[] eventTypeNames = new String[]{"ACTION_DOWN", "ACTION_MOVE", "ACTION_UP", "ACTION_CANCEL"};

    //--------------
    public TouchFrameLayout(Context context) {
        super(context);
    }

    public TouchFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TouchFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
//        System.out.println("===]]] TouchFrameLayout onTouchEvent" );
        System.out.println("===]]] onTouchEvent " + MotionEventName.ACTION_NAME[event.getAction()]);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        for (int i = 0; i < eventTypes.length; i++) {
            if (ev.getAction() == eventTypes[i]) {
                System.out.println("===]]] TouchFrameLayout onInterceptTouchEvent " + eventTypeNames[i]);
                if (i == 0) {
                    touchLastPoint[0] = ev.getRawX();
                    touchLastPoint[1] = ev.getRawY();
                }
                if (i == 1) {
                    float dx = ev.getRawX() - touchLastPoint[touchPointIndexX];
                    float dy = ev.getRawY() - touchLastPoint[touchPointIndexY];
                    int scaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
                    if (scaledTouchSlop < Math.abs(dx * dy)) {
                        System.out.println("===]]] TouchFrameLayout InterceptTouchEvent");
                        return true;
                    }
                }
                break;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }
}
