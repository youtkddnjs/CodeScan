package com.example.codescan;

import android.util.Log;

public class PrintLog {
    public static final boolean DBG = true;
    static final String TAG = "SW";

    public static void PrintVerbose(String p_msg) {
        PrintVerbose("", p_msg);
    }

    public static void PrintVerbose(String p_tag, String p_msg) {
        PrintVerbose(DBG, p_tag, p_msg);
    }

    public static void PrintVerbose(boolean p_state, String p_tag, String p_massage) {
        if (p_state) Log.v(TAG + "_" + p_tag, p_massage);
    }



    public static void PrintDebug(String p_msg) {
        PrintDebug("", p_msg);
    }

    public static void PrintDebug(String p_tag, String p_msg) {
        PrintDebug(DBG, p_tag, p_msg);
    }

    public static void PrintDebug(boolean p_state, String p_tag, String p_massage) {
        if (p_state) Log.d(TAG + "_" + p_tag, p_massage);
    }



    public static void PrintInfo(String p_msg) {
        PrintInfo("", p_msg);
    }

    public static void PrintInfo(String p_tag, String p_msg) {
        PrintInfo(DBG, p_tag, p_msg);
    }

    public static void PrintInfo(boolean p_state, String p_tag, String p_massage) {
        if (p_state) Log.i(TAG + "_" + p_tag, p_massage);
    }


    public static void PrintWarn(String p_msg) {
        PrintWarn("", p_msg);
    }

    public static void PrintWarn(String p_tag, String p_msg) {
        PrintWarn(DBG, p_tag, p_msg);
    }

    public static void PrintWarn(boolean p_state, String p_tag, String p_massage) {
        if (p_state) Log.w(TAG + "_" + p_tag, p_massage);
    }


    public static void PrintError(String p_msg) {
        PrintError("", p_msg);
    }

    public static void PrintError(String p_tag, String p_msg) {
        PrintError(DBG, p_tag, p_msg);
    }

    public static void PrintError(boolean p_state, String p_tag, String p_massage) {
        if (p_state) Log.e(TAG + "_" + p_tag, p_massage);
    }

}