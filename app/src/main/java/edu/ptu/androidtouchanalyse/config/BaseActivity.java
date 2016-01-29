package edu.ptu.androidtouchanalyse.config;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by WangAnshu on 2016/1/29.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Conf.getInstance()==null) {
            Toast.makeText(getApplicationContext(), "未在Application初始化 edu.ptu.androidtouchanalyse.config.Conf ", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
