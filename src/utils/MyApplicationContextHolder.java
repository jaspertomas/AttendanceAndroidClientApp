package utils;

import android.content.Context;

public class MyApplicationContextHolder{

    private static Context appcontext;

    public static void setAppContext(Context appcontext){
    	MyApplicationContextHolder.appcontext=appcontext;
    }

    public static Context getAppContext() {
        return appcontext;
    }
}
