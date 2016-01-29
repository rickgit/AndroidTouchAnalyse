package edu.ptu.androidtouchanalyse.action.impl;

import android.view.MotionEvent;

import edu.ptu.androidtouchanalyse.action.AbstractAction;
import edu.ptu.androidtouchanalyse.action.SupperViewEvent;
import edu.ptu.androidtouchanalyse.action.SupperViewGroupEvent;
import edu.ptu.androidtouchanalyse.action.ViewAction;
import edu.ptu.androidtouchanalyse.action.ViewGroupAction;
import edu.ptu.androidtouchanalyse.config.Conf;
import edu.ptu.androidtouchanalyse.data.MotionEventInfo;

/**主要使用方法，单例就从简，有啥不妥，可以提下
 * Created by WangAnshu on 2016/1/29.
 */
public class DetectedTouchInViewToParent {
    public static DetectedTouchInViewToParent instance=new DetectedTouchInViewToParent();

    public ViewAction innerView;
    public ViewGroupAction containerView;

    public ViewAction getInnerView() {
        synchronized (DetectedTouchInViewToParent.class){
            if (innerView==null){
                innerView=createInnerViewAction();
            }
        }
        return innerView;
    }
    public ViewGroupAction getContainerView(){
        synchronized (DetectedTouchInViewToParent.class){
            if (containerView==null){
                containerView=new ContainerViewAction();
            }
        }
        return containerView;
    }

    private static ViewAction createInnerViewAction() {
         return instance.getInnerView();
    }

    private  static ViewGroupAction createContainerView() {
       return instance.getContainerView();
    }

    final class ContainerViewAction extends AbstractAction implements ViewGroupAction {
        float[] touchLastPoint = new float[2];//touchLastX touchLastY
        int touchPointIndexX = 0;
        int touchPointIndexY = 1;
        @Override
        public boolean handleTouchEvnet(MotionEvent event,SupperViewEvent view) {
            System.out.println("===]]] onTouchEvent " + MotionEventInfo.ACTION_NAME[event.getAction()]);
            return true;
        }

        @Override
        public boolean handleInterceptTouchEvent(MotionEvent ev,SupperViewGroupEvent viewGroup) {
            Class<?> superclass = viewGroup.getClass().getSuperclass();
            for (int i = 0; i < MotionEventInfo.ACTION_TYPE.length; i++) {
                if (ev.getAction() == MotionEventInfo.ACTION_TYPE[i]) {
                    System.out.println("===]]] TouchFrameLayout onInterceptTouchEvent " + MotionEventInfo.ACTION_NAME[i]);
                    if (i == 0) {
                        touchLastPoint[0] = ev.getRawX();
                        touchLastPoint[1] = ev.getRawY();
                    }
                    if (i == 1) {
                        float dx = ev.getRawX() - touchLastPoint[touchPointIndexX];
                        float dy = ev.getRawY() - touchLastPoint[touchPointIndexY];
                        int scaledTouchSlop = Conf.getInstance().getViewConfiguration().getScaledTouchSlop();
                        if (scaledTouchSlop < Math.abs(dx * dy)) {
                            System.out.println("===]]] TouchFrameLayout InterceptTouchEvent");
                            return true;
                        }
                    }
                    break;
                }
            }

            return viewGroup.supperOnInterceptTouchEvent(ev);// FIXME: 2016/1/29 父类实现
        }
    }

    final class InnerViewAction extends AbstractAction implements ViewAction{
        @Override
        public boolean handleTouchEvnet(MotionEvent event,SupperViewEvent view) {
            boolean b = view.supperOnTouchEvent(event);// FIXME: 2016/1/29 父类实现
            System.out.println("===]]] TouchTextView onTouchEvent " + MotionEventInfo.ACTION_NAME[event.getAction()] + "   " + b);
            return b;
        }
    }
}
