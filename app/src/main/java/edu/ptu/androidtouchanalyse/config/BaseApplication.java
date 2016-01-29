package edu.ptu.androidtouchanalyse.config;

import android.app.Application;

/**
 * Created by WangAnshu on 2016/1/29.
 */
public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Conf.init(this);
    }
}
