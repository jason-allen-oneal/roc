package com.jasononeal.roc;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class Storage {
    private static Storage instance;
    public static SharedPreferences data;
    SharedPreferences.Editor editor;
    public Storage(String type, Context context){
        data = context.getSharedPreferences(type, MODE_PRIVATE);
        editor = data.edit();
    }

    public static Storage getInstance(String type, Context context) {
        if (instance == null) {
            synchronized (Storage.class) {
                if (instance == null) {
                    instance = new Storage(type, context);
                }
            }
        }
        return instance;
    }

    public void add(String k, Integer v){
        editor.putInt(k, v);
    }

    public void add(String k, Boolean v){
        editor.putBoolean(k, v);
    }

    public void add(String k, String v){
        editor.putString(k, v);
    }
}
