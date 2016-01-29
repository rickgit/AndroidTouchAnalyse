package edu.ptu.androidtouchanalyse.config;

import android.app.Application;
import android.view.ViewConfiguration;

/**
 * Created by WangAnshu on 2016/1/29.
 */
public class Conf {
    private static Conf ourInstance = null;
    private final ViewConfiguration vc;

    public Conf(Application app) {
        vc = ViewConfiguration.get(app);
    }

    /**
     * 必须在BaseApplication 调用
     * @param app
     */
    public static void init(Application app){
        synchronized (Conf.class){
            if (ourInstance==null)
                ourInstance=new Conf(app);
        }
    }
    public static Conf getInstance() {
        return ourInstance;
    }

    public ViewConfiguration getViewConfiguration() {
        return vc;
    }
}
