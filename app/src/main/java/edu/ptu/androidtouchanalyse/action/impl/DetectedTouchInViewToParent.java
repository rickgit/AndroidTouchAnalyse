package edu.ptu.androidtouchanalyse.action.impl;

import android.view.MotionEvent;

import edu.ptu.androidtouchanalyse.action.AbstractAction;
import edu.ptu.androidtouchanalyse.action.SupperViewEvent;
import edu.ptu.androidtouchanalyse.action.SupperViewGroupEvent;
import edu.ptu.androidtouchanalyse.action.ViewAction;
import edu.ptu.androidtouchanalyse.action.ViewGroupAction;
import edu.ptu.androidtouchanalyse.config.Conf;
import edu.ptu.androidtouchanalyse.data.MotionEventInfo;
import edu.ptu.androidtouchanalyse.data.TouchPointF;

/**
 * 主要使用方法，单例就从简，有啥不妥，可以提下
 * <pre>
 *     <ul>
 *         <i>尝试点击里面的按钮（可以是TextView），然后移动到父控件，事件变化</i>
 *         <i>——————————Parent Down {DOWN} 拦截事件</i>
 *         <i>——————————————Child View {MOVE}</i>
 *         <i>——————————————Child View {CANCEL}</i>
 *         <i>——————————Parent View {InterceptTouchEvent} 拦截事件</i>
 *     </ul>
 * </pre>
 * <red>注：即使拦截事件，也会从父控件，onInterceptTouchEvent，传递下来。必须执行完，Child view的UP事件，才会触发onClick事件</red>
 * Created by WangAnshu on 2016/1/29.
 */
class DetectedTouchInViewToParent implements DetectedFacede.IDetected {
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
        TouchPointF touchLastPoint = new TouchPointF();

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
                        touchLastPoint.setAxisX(ev.getRawX());
                        touchLastPoint.setAxisY(ev.getRawY());
                    }
                    if (i == MotionEvent.ACTION_MOVE) {
                        float dx = touchLastPoint.deltaAxisX(ev.getRawX());
                        float dy = touchLastPoint.deltaAxisY(ev.getRawY());
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
            MotionEventInfo.printTouchResult(1, "TouchTextView", "onTouchEvent", event.getAction(), b);
            return b;

        }
    }
}
