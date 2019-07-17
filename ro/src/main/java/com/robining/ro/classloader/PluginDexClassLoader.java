package com.robining.ro.classloader;

import dalvik.system.DexClassLoader;

public class PluginDexClassLoader extends DexClassLoader {
    public PluginDexClassLoader(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
        super(dexPath, optimizedDirectory, librarySearchPath, parent);
    }
}
