# AndroidTouchAnalyse
 analyse android touch event


自定义控件分析触摸事件和点击事件

定义了Action的名称，用来显示在日志里面，方便分析
<pre>
class MotionEventName {
    public static String[] ACTION_NAME = {"ACTION_DOWN", "ACTION_UP", "ACTION_MOVE", "ACTION_CANCEL"};
}
</pre>

通过修改方法里面的单例类，修改不同的触摸事件逻辑代码
[edu.ptu.androidtouchanalyse.action.impl.DetectedFacede#getInstance](https://github.com/rickgit/AndroidTouchAnalyse/blob/master/app/src/main/java/edu/ptu/androidtouchanalyse/action/impl/DetectedFacede.java)
