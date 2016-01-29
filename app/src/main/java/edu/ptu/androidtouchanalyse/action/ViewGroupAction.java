package edu.ptu.androidtouchanalyse.action;

import android.view.MotionEvent;

/**
 * Created by WangAnshu on 2016/1/29.
 */
public interface ViewGroupAction extends edu.ptu.androidtouchanalyse.action.ViewAction{
    public  boolean handleInterceptTouchEvent(MotionEvent ev,SupperViewGroupEvent viewGroup);
}
