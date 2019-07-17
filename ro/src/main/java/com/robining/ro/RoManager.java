package com.robining.ro;

import android.app.Application;

import java.util.HashMap;
import java.util.Map;

public class RoManager {
    public static String KEY_REAL_TARGET = "RO_KEY_REAL_TARGET";
    public static Application application;
    public static Map<Class<?>,String> classSourcePaths = new HashMap<>();
}
