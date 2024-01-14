package com.jasononeal;

import android.app.Application;
import android.content.Context;

public class RoC extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        RoC.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return RoC.context;
    }
}
