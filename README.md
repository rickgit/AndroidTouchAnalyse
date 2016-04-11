## AndroidTouchAnalyse
 analyse android touch event


自定义控件分析触摸事件和点击事件

定义了Action的名称，用来显示在日志里面，方便分析（[MotionEventInfo](https://github.com/rickgit/AndroidTouchAnalyse/blob/master/app/src/main/java/edu/ptu/androidtouchanalyse/data/MotionEventInfo.java)）
<pre>
class MotionEventInfo{
    public static String[] ACTION_NAME = {"ACTION_DOWN", "ACTION_UP", "ACTION_MOVE", "ACTION_CANCEL"};
}
</pre>

通过修改方法里面的单例类，修改不同的触摸事件逻辑代码
[edu.ptu.androidtouchanalyse.action.impl.DetectedFacede#getInstance](https://github.com/rickgit/AndroidTouchAnalyse/blob/master/app/src/main/java/edu/ptu/androidtouchanalyse/action/impl/DetectedFacede.java)


##ScrollView
点击事件在Touch up时候判断，并加入到队列中。

``` java
    if (!mHasPerformedLongPress && !mIgnoreNextUpEvent) {
        // This is a tap, so remove the longpress check
        removeLongPressCallback();
        // Only perform take click actions if we were in the pressed state
        if (!focusTaken) {
            // Use a Runnable and post this rather than calling
            // performClick directly. This lets other visual state
            // of the view update before click actions start.
            if (mPerformClick == null) {
                mPerformClick = new PerformClick();
            }
            if (!post(mPerformClick)) {
                performClick();
            }
        }
    }
```