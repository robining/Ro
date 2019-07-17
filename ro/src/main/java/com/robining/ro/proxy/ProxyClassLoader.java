package com.robining.ro.proxy;

import com.robining.ro.RoManager;

public class ProxyClassLoader extends ClassLoader {
    private ClassLoader hotClassLoader;
    private ClassLoader[] classLoaders;
    private String[] paths;

    public ProxyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public void setClassLoaders(ClassLoader[] classLoaders, String[] paths) {
        if (classLoaders == null) {
            classLoaders = new ClassLoader[0];
        }
        this.classLoaders = classLoaders;

        if (paths == null) {
            paths = new String[0];
        }
        this.paths = paths;
    }

    public void setHotClassLoader(ClassLoader hotClassLoader) {
        this.hotClassLoader = hotClassLoader;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println(">>>load class:" + name);
        try {
            if (hotClassLoader == null) {
                throw new ClassNotFoundException();
            }
            Class<?> clz = hotClassLoader.loadClass(name);
            System.out.println(">>>hot fixed class:" + name + "--------------" + clz.getName());
            return clz;
        } catch (ClassNotFoundException e) {
            try {
                return getParent().loadClass(name);
            } catch (ClassNotFoundException e1) {
                for (int i = 0; i < classLoaders.length; i++) {
                    try {
                        Class<?> clz = classLoaders[i].loadClass(name);
                        RoManager.classSourcePaths.put(clz, paths[i]);
                        System.out.println(">>>load plugin class:" + name + "--------------" + clz.getName());
                        return clz;
                    } catch (ClassNotFoundException ignored) {
                    }
                }
            }
        }
        throw new ClassNotFoundException();
    }
}
