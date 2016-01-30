package edu.ptu.androidtouchanalyse.data;

import android.view.MotionEvent;

import edu.ptu.androidtouchanalyse.config.Conf;

/**
 * Created by WangAnshu on 2016/1/29.
 */
public class TouchPointF {
    private float axisX;
    private float axisY;

    public TouchPointF() {
    }

    public TouchPointF(MotionEvent event) {
        this.axisX = event.getRawX();
        this.axisY = event.getRawY();
    }
    public void setAxis(MotionEvent event) {
        this.axisX = event.getRawX();
        this.axisY = event.getRawY();
    }
    public void setAxisX(float axisX) {
        this.axisX = axisX;
    }

    public void setAxisY(float axisY) {
        this.axisY = axisY;
    }

    public float deltaAxisX(float newAxisX) {
        return newAxisX - axisX;
    }

    public float deltaAxisY(float newAxisY) {
        return newAxisY - axisY;
    }

    public boolean isMove(MotionEvent event){
        int i = Conf.getInstance().getViewConfiguration().getScaledTouchSlop() * Conf.getInstance().getViewConfiguration().getScaledTouchSlop();
        float dx = deltaAxisX(event.getRawX());
        float dy = deltaAxisY(event.getY());
        if (i< dx*dx + dy*dy)
            return true;
        return false;
    }

}
