package edu.ptu.androidtouchanalyse.action.impl;

import android.graphics.PointF;
import android.view.MotionEvent;

import edu.ptu.androidtouchanalyse.action.AbstractAction;
import edu.ptu.androidtouchanalyse.action.SupperViewEvent;
import edu.ptu.androidtouchanalyse.action.SupperViewGroupEvent;
import edu.ptu.androidtouchanalyse.action.ViewAction;
import edu.ptu.androidtouchanalyse.action.ViewGroupAction;
import edu.ptu.androidtouchanalyse.config.Conf;
import edu.ptu.androidtouchanalyse.data.MotionEventInfo;

/**
 * 主要使用方法，单例就从简，有啥不妥，可以提下
 * Created by WangAnshu on 2016/1/29.
 */
public class DetectedTouchInViewToParent {
    public static DetectedTouchInViewToParent instance = new DetectedTouchInViewToParent();

    private ViewAction innerView;
    private ViewGroupAction containerView;

    public ViewAction getInnerView() {
        synchronized (DetectedTouchInViewToParent.class) {
            if (innerView == null) {
                innerView = new InnerViewAction();
            }
        }
        return innerView;
    }

    public ViewGroupAction getContainerView() {
        synchronized (DetectedTouchInViewToParent.class) {
            if (containerView == null) {
                containerView = new ContainerViewAction();
            }
        }
        return containerView;
    }

    private static ViewAction createInnerViewAction() {
        return instance.getInnerView();
    }

    private static ViewGroupAction createContainerView() {
        return instance.getContainerView();
    }

    final class ContainerViewAction extends AbstractAction implements ViewGroupAction {
        float[] touchLastPoint = new float[2];//touchLastX touchLastY
        int touchPointIndexX = 0;
        int touchPointIndexY = 1;
        PointF lastPoint = new PointF(0f, 0f);

        @Override
        public boolean handleTouchEvnet(MotionEvent event, SupperViewEvent view) {
            MotionEventInfo.printTouchResult(0, "TouchFrameLayout", "onTouchEvent", event.getAction(), true);
            return true;
        }

        @Override
        public boolean handleInterceptTouchEvent(MotionEvent ev, SupperViewGroupEvent viewGroup) {
            for (int i = 0; i < MotionEventInfo.ACTION_TYPE.length; i++) {
                if (ev.getAction() == MotionEventInfo.ACTION_TYPE[i]) {
                    if (i == MotionEvent.ACTION_DOWN) {
                        touchLastPoint[0] = ev.getRawX();
                        touchLastPoint[1] = ev.getRawY();
                    }
                    if (i == MotionEvent.ACTION_MOVE) {
                        float dx = ev.getRawX() - touchLastPoint[touchPointIndexX];
                        float dy = ev.getRawY() - touchLastPoint[touchPointIndexY];
                        int scaledTouchSlop = Conf.getInstance().getViewConfiguration().getScaledTouchSlop();
                        if (scaledTouchSlop < Math.abs(dx * dy)) {
                            MotionEventInfo.printTouchResult(0, "TouchFrameLayout", "InterceptTouchEvent", i, true);
                            return true;
                        }
                    }
                    break;
                }
            }

            boolean b = viewGroup.supperOnInterceptTouchEvent(ev);
            MotionEventInfo.printTouchResult(0, "TouchFrameLayout", "InterceptTouchEvent", ev.getAction(), b);
            return b;// FIXME: 2016/1/29 父类实现
        }
    }

    final class InnerViewAction extends AbstractAction implements ViewAction {
        @Override
        public boolean handleTouchEvnet(MotionEvent event, SupperViewEvent view) {
            boolean b = view.supperOnTouchEvent(event);// FIXME: 2016/1/29 父类实现
            b = true;
            MotionEventInfo.printTouchResult(1, "TouchTextView", "onTouchEvent", event.getAction(), b);
            return b;
        }
    }
}
