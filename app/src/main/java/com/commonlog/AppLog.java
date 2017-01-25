package com.commonlog;

import android.util.Log;


public class AppLog {

    private static String className;
    private static String methodName;
    private static int lineNumber;

    /*public final class BuildConfig {
        public final static boolean DEBUG = true;
    }*/

    private AppLog() {
        /* Protect from instantiations */
    }

    public static boolean isDebuggable() {
        return BuildConfig.DEBUG;
    }

    private static String createLog(String log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        buffer.append(methodName);
        buffer.append(":");
        buffer.append(lineNumber);
        buffer.append("]");
        buffer.append(log);
        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] sElements) {

        if (sElements.length > 1) {
            className = sElements[1].getFileName();
            methodName = sElements[1].getMethodName();
            lineNumber = sElements[1].getLineNumber();
        } else if (sElements.length == 1) {
            className = sElements[0].getFileName();
            methodName = sElements[0].getMethodName();
            lineNumber = sElements[0].getLineNumber();
        }

    }

    public static void e(String message) {

        if (!isDebuggable())
            return;

        // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
        Log.e(className, createLog(message));
    }

    public static void e(String message, Throwable e) {

        if (!isDebuggable())
            return;

        // Throwable instance must be created before any methods
        getMethodNames(e.getStackTrace());
        Log.e(className, createLog(message), e);
    }

    public static void i(String message) {

        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.i(className, createLog(message));
    }

    public static void d(String message) {

        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.d(className, createLog(message));
    }

    public static void v(String message) {

        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.v(className, createLog(message));
    }

    public static void w(String message) {

        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.w(className, createLog(message));
    }
}
