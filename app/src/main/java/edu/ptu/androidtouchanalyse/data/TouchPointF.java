package edu.ptu.androidtouchanalyse.data;

/**
 * Created by WangAnshu on 2016/1/29.
 */
public class TouchPointF {
    private float axisX;
    private float axisY;
    public void setAxisX(float axisX) {
        this.axisX = axisX;
    }

    public void setAxisY(float axisY) {
        this.axisY = axisY;
    }

    public float deltaAxisX(float newAxisX){
        return newAxisX-axisX;
    }
    public float deltaAxisY(float newAxisY){
        return newAxisY-axisY;
    }
}
