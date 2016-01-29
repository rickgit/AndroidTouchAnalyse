package edu.ptu.androidtouchanalyse.data;

import android.view.MotionEvent;

/**
 * 定义了Action的名称，用来显示在日志里面，方便分析
 * Created by WangAnshu on 2016/1/27.
 */
public class MotionEventInfo {
    public static String[] ACTION_NAME = {"ACTION_DOWN", "ACTION_UP", "ACTION_MOVE", "ACTION_CANCEL"};
    public static int[] ACTION_TYPE = new int[]{MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL};
}
