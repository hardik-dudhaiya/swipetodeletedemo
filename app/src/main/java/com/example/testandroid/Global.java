package com.example.testandroid;

public class Global {

    public static boolean IsNotNull(Object object) {

        return object != null && !object.equals("null") && !object.equals("");

    }

}
