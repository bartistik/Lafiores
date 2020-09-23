package com.example.lafiores.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class Constant {


    public static final String STATE_DATA_START_LOADING = "Start Loading";
    public static final String STATE_DATA_LOADING = "Loading";
    public static final String STATE_DATA_LOADED = "Loaded";
    public static final String STATE_DATA_ERROR = "Error";

    //проверяем интернет-соединение
    public static boolean checkNetworkConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

}
