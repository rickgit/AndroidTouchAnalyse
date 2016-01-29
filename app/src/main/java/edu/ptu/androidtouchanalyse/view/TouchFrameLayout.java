package edu.ptu.androidtouchanalyse.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import edu.ptu.androidtouchanalyse.action.SupperViewGroupEvent;
import edu.ptu.androidtouchanalyse.action.ViewGroupAction;
import edu.ptu.androidtouchanalyse.action.impl.DetectedTouchInViewToParent;

/**
 * Created by WangAnshu on 2016/1/27.
 */
public class TouchFrameLayout extends FrameLayout implements SupperViewGroupEvent{
    public ViewGroupAction viewGroupAction= DetectedTouchInViewToParent.instance.getContainerView();
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
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewGroupAction.handleInterceptTouchEvent(ev,this);
    }

    @Override
    public boolean supperOnInterceptTouchEvent(MotionEvent event) {
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean supperOnTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
