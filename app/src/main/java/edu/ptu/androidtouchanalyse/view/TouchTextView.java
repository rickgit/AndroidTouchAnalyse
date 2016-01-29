package edu.ptu.androidtouchanalyse.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import edu.ptu.androidtouchanalyse.action.SupperViewEvent;
import edu.ptu.androidtouchanalyse.action.ViewAction;
import edu.ptu.androidtouchanalyse.action.impl.DetectedTouchInViewToParent;

/**
 * Created by WangAnshu on 2016/1/27.
 */
public class TouchTextView extends TextView implements SupperViewEvent {
    public ViewAction viewAction= DetectedTouchInViewToParent.instance.getInnerView();
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
                return viewAction.handleTouchEvnet(event,this);
    }

    @Override
    public boolean supperOnTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
