package com.lombardi.fuelmng.util;

import com.google.gson.Gson;

public class SingletonHolder {

    private static SingletonHolder instance = null;
    private static Gson gsonInstance = null;

    private SingletonHolder() {
    }

    public static SingletonHolder getInstance() {
        if (instance == null) {
            instance = new SingletonHolder();
        }
        return instance;
    }

    public Gson getGson() {
        if (gsonInstance == null) {
            gsonInstance = new Gson();
        }
        return gsonInstance;
    }


}
