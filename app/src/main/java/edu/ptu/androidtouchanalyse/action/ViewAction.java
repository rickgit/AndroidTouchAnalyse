package edu.ptu.androidtouchanalyse.action;

import android.view.MotionEvent;

/**
 * Created by WangAnshu on 2016/1/29.
 */
public interface ViewAction {
    public boolean handleTouchEvnet(MotionEvent event,SupperViewEvent view);
}
