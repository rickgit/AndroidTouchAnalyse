package edu.ptu.androidtouchanalyse.action.impl;

import android.view.MotionEvent;
import android.view.View;

import edu.ptu.androidtouchanalyse.action.AbstractAction;
import edu.ptu.androidtouchanalyse.action.SupperViewEvent;
import edu.ptu.androidtouchanalyse.action.SupperViewGroupEvent;
import edu.ptu.androidtouchanalyse.action.ViewAction;
import edu.ptu.androidtouchanalyse.action.ViewGroupAction;
import edu.ptu.androidtouchanalyse.data.MotionEventInfo;

/**
 * 测试子类调用，RequestDisallowInterceptTouchEvent，是否能阻止父类onInterceptTouchEvent
 * <pre>
 *     <ol>
 *         <i>父控件，onDown事件不能拦截</i>
 *         <i>子控件，onDown开始拦截，则父控件不会走onInterceptTouchEvent</i>
 *     </ol>
 * </pre>
 * Created by WangAnshu on 2016/1/29.
 */
class DetectedRequestDisallowInterceptTouchEvent implements DetectedFacede.IDetected {
    public static DetectedRequestDisallowInterceptTouchEvent instance = new DetectedRequestDisallowInterceptTouchEvent();

    private ViewAction innerView;
    private ViewGroupAction containerView;

    public ViewAction getInnerView() {
        synchronized (DetectedRequestDisallowInterceptTouchEvent.class) {
            if (innerView == null) {
                innerView = new InnerViewAction();
            }
        }
        return innerView;
    }

    public ViewGroupAction getContainerView() {
        synchronized (DetectedRequestDisallowInterceptTouchEvent.class) {
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
        @Override
        public boolean handleTouchEvnet(MotionEvent event, SupperViewEvent view) {
            MotionEventInfo.printTouchResult(0, "TouchFrameLayout", "onTouchEvent", event.getAction(), true);
            return true;
        }

        @Override
        public boolean handleInterceptTouchEvent(MotionEvent ev, SupperViewGroupEvent viewGroup) {
            if (ev.getAction() != MotionEvent.ACTION_DOWN)//移动时候，尝试拦截事件
                return true;
            boolean b = viewGroup.supperOnInterceptTouchEvent(ev);
            MotionEventInfo.printTouchResult(0, "TouchFrameLayout", "InterceptTouchEvent", ev.getAction(), b);
            return b;// FIXME: 2016/1/29 父类实现
        }
    }

    final class InnerViewAction extends AbstractAction implements ViewAction {
        @Override
        public boolean handleTouchEvnet(MotionEvent event, SupperViewEvent view) {
            boolean b = view.supperOnTouchEvent(event);// FIXME: 2016/1/29 父类实现
            ((View) view).getParent().requestDisallowInterceptTouchEvent(true);
            MotionEventInfo.printTouchResult(1, "TouchTextView", "onTouchEvent", event.getAction(), b);
            return b;

        }
    }
}
