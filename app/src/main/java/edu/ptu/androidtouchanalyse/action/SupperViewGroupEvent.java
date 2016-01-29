package edu.ptu.androidtouchanalyse.action;

import android.view.MotionEvent;

public interface SupperViewGroupEvent extends SupperViewEvent{
    public boolean supperOnInterceptTouchEvent(MotionEvent event);
}