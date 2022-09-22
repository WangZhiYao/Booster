package com.yizhenwind.booster.logger;

import timber.log.Timber;

/**
 * @author WangZhiYao
 * @since 2022/9/17
 */
public class Logger {

    public static void d(String message, Object... args) {
        Timber.d(message, args);
    }

    public static void d(Throwable t) {
        Timber.d(t);
    }

    public static void d(Throwable t, String message, Object... args) {
        Timber.d(t, message, args);
    }

    public static void e(String message, Object... args) {
        Timber.e(message, args);
    }

    public static void e(Throwable t) {
        Timber.e(t);
    }

    public static void e(Throwable t, String message, Object... args) {
        Timber.e(t, message, args);
    }

    public static void i(String message, Object... args) {
        Timber.i(message, args);
    }

    public static void i(Throwable t) {
        Timber.i(t);
    }

    public static void i(Throwable t, String message, Object... args) {
        Timber.i(t, message, args);
    }

    public static void v(String message, Object... args) {
        Timber.v(message, args);
    }

    public static void v(Throwable t) {
        Timber.v(t);
    }

    public static void v(Throwable t, String message, Object... args) {
        Timber.v(t, message, args);
    }

    public static void w(String message, Object... args) {
        Timber.w(message, args);
    }

    public static void w(Throwable t) {
        Timber.w(t);
    }

    public static void w(Throwable t, String message, Object... args) {
        Timber.w(t, message, args);
    }

    public static void wtf(String message, Object... args) {
        Timber.wtf(message, args);
    }

    public static void wtf(Throwable t) {
        Timber.wtf(t);
    }

    public static void wtf(Throwable t, String message, Object... args) {
        Timber.wtf(t, message, args);
    }
}
