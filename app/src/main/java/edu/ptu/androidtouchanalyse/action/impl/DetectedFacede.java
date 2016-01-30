package edu.ptu.androidtouchanalyse.action.impl;

import edu.ptu.androidtouchanalyse.action.ViewAction;
import edu.ptu.androidtouchanalyse.action.ViewGroupAction;

/**
 * Created by WangAnshu on 2016/1/30.
 */
public class DetectedFacede {
    public static IDetected getInstance(){
        return DetectedTouchInViewCancelEvent.instance;
    }
    public static interface IDetected{
        public ViewGroupAction getContainerView();
        public ViewAction getInnerView();
    }
}
