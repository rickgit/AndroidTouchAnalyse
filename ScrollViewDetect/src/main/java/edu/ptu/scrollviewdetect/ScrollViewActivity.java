package edu.ptu.scrollviewdetect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScrollViewActivity extends AppCompatActivity {

    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        tvName = (TextView) findViewById(R.id.scroll_tv_name);
        View svContainer = findViewById(R.id.scroll_sv_name) ;
        svContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tvName.setText("scrollY" + ((ViewGroup) v).getScrollY());
                return false;
            }
        });
    }

}
