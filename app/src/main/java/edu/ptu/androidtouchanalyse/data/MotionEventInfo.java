package edu.ptu.androidtouchanalyse.data;

import android.view.MotionEvent;

import edu.ptu.androidtouchanalyse.config.LogCfg;

/**
 * 定义了Action的名称，用来显示在日志里面，方便分析
 * Created by WangAnshu on 2016/1/27.
 */
public class MotionEventInfo {
    private static String[] ACTION_NAME = {"↓↓↓↓↓", "↑↑↑↑↑↑", "←↔↔↔↔↔→", "↗↗↗↗↗↗"};//搜狗输入法，特殊符号
    public static int[] ACTION_TYPE = new int[]{MotionEvent.ACTION_DOWN, MotionEvent.ACTION_UP, MotionEvent.ACTION_MOVE, MotionEvent.ACTION_CANCEL};

    private static String getActionName(int action) {
        if (action >= 0 && action < ACTION_NAME.length) {
            return ACTION_NAME[action];
        }
        return Integer.toString(action);
    }

    private static String printInterceptTouch(boolean interceptTouch) {
        if (interceptTouch)
            return "√√√√√√";
        return "";
    }

    static Object[] lastParamsNum = null;

    public static void printTouchResult(int index, String viewName, String viewMethod, int action, boolean result) {
        System.out.println(LogCfg.PRINT_INDEX_ARR[index] + viewName + LogCfg.PRINT_SPACE + viewMethod + LogCfg.PRINT_SPACE + getActionName(action) + LogCfg.PRINT_SPACE + printInterceptTouch(result));
    }

    public static void printPrettyTouchResult(int index, String viewName, String viewMethod, int action, boolean result) {
        Object[] paramsNum = new Object[]{index, viewName, viewMethod, action, result};
        if (lastParamsNum == null) {
            lastParamsNum = paramsNum;
            printTouchResult(index, viewName, viewMethod, action, result);
            return;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < paramsNum.length; i++) {
            if (lastParamsNum[i].equals(paramsNum[i])) {//用空格替换
                String paramString = getParamString(i, paramsNum[i]);
                for (int paramStringIndex = 0; paramStringIndex < paramString.length(); paramStringIndex++) {
                    sb.append(" ");
                }
            } else {
                for (int otherParams = i; otherParams < paramsNum.length; otherParams++, i++) {
                    sb.append(getParamString(otherParams, paramsNum[otherParams]));//直接输入字符串
                }
            }
        }
        lastParamsNum = paramsNum;
        System.out.println(sb);
    }

    private static String getParamString(int index, Object param) {
        switch (index) {
            case 0:
                return LogCfg.PRINT_INDEX_ARR[(Integer) param];
            case 1:
                return param.toString();
            case 2:
                return LogCfg.PRINT_SPACE + param.toString();
            case 3:
                return LogCfg.PRINT_SPACE + getActionName((Integer) param);
            case 4:
                return LogCfg.PRINT_SPACE + printInterceptTouch((Boolean) param);

        }
        return param.toString();

    }
}
