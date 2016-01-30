package edu.ptu.androidtouchanalyse.action.impl;

import android.view.MotionEvent;
import android.view.View;

import edu.ptu.androidtouchanalyse.action.AbstractAction;
import edu.ptu.androidtouchanalyse.action.SupperViewEvent;
import edu.ptu.androidtouchanalyse.action.SupperViewGroupEvent;
import edu.ptu.androidtouchanalyse.action.ViewAction;
import edu.ptu.androidtouchanalyse.action.ViewGroupAction;
import edu.ptu.androidtouchanalyse.data.MotionEventInfo;
import edu.ptu.androidtouchanalyse.data.TouchPointF;

/**
 * 测试子类调用，移动后取消事件占用
 * <pre>
 *     1.虽然父控件OnInterceptTouchEvent返回true，事件还是会传到子控件，但是MotionEvent是Cancel
 * </pre>
 * Created by WangAnshu on 2016/1/29.
 */
class DetectedTouchInViewCancelEvent implements DetectedFacede.IDetected {
    public static DetectedTouchInViewCancelEvent instance = new DetectedTouchInViewCancelEvent();

    private ViewAction innerView;
    private ViewGroupAction containerView;

    public ViewAction getInnerView() {
        synchronized (DetectedTouchInViewCancelEvent.class) {
            if (innerView == null) {
                innerView = new InnerViewAction();
            }
        }
        return innerView;
    }

    public ViewGroupAction getContainerView() {
        synchronized (DetectedTouchInViewCancelEvent.class) {
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


            boolean b = viewGroup.supperOnInterceptTouchEvent(ev);
            if (ev.getAction() == MotionEvent.ACTION_MOVE) b = true;//移动时候，尝试拦截事件
            MotionEventInfo.printTouchResult(0, "TouchFrameLayout", "InterceptTouchEvent", ev.getAction(), b);
            return true;// FIXME: 2016/1/29 父类实现
        }
    }

    final class InnerViewAction extends AbstractAction implements ViewAction {
        TouchPointF touchPointF = new TouchPointF();

        @Override
        public boolean handleTouchEvnet(MotionEvent event, SupperViewEvent view) {
            boolean b = view.supperOnTouchEvent(event);// FIXME: 2016/1/29 父类实现
            ((View) view).getParent().requestDisallowInterceptTouchEvent(true);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                ((View) view).getParent().requestDisallowInterceptTouchEvent(true);
                touchPointF.setAxis(event);
            } else if (MotionEvent.ACTION_MOVE == event.getAction()) {
                if (touchPointF.isMove(event)) {
                    ((View) view).getParent().requestDisallowInterceptTouchEvent(false);
                    event.setAction(MotionEvent.ACTION_CANCEL);
                }
            }
            MotionEventInfo.printTouchResult(1, "TouchTextView", "onTouchEvent", event.getAction(), b);
            return b;

        }
    }
}
