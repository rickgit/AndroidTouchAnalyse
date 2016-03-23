package edu.ptu.scrollviewdetect;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by WangAnshu on 2016/3/23.
 */
public class InfoScrollView extends ScrollView {
    public InfoScrollView(Context context) {
        super(context);
    }

    public InfoScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InfoScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public InfoScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void computeScroll() {
        super.computeScroll();
        post(new Runnable() {
            @Override
            public void run() {
                System.out.println("" + getScrollY());
            }
        });

    }
}

