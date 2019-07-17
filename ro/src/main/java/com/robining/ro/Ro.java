package com.robining.ro;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.robining.ro.proxy.ProxyClassLoader;
import com.robining.ro.proxy.ProxyIActvityManager;
import com.robining.ro.proxy.ProxyInstrumentation;

import java.lang.ref.WeakReference;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dalvik.system.DexClassLoader;

public class Ro {
    public static ProxyClassLoader proxyClassLoader;

    public static void init(Application application) throws Exception {
        RoManager.application = application;
        hookAms(application);
        hookInstrument(application);
        hookClassLoader(application);
//        hookResources(application);
    }

    public static void installPlugins(List<String> dexPaths) {
        if (dexPaths == null) {
            dexPaths = new ArrayList<>();
        }
        DexClassLoader[] dexClassLoaders = new DexClassLoader[dexPaths.size()];
        for (int i = 0; i < dexClassLoaders.length; i++) {
            DexClassLoader dexClassLoader = new DexClassLoader(dexPaths.get(i), RoManager.application.getFilesDir().getAbsolutePath(), null, proxyClassLoader.getParent());
            dexClassLoaders[i] = dexClassLoader;
        }

        proxyClassLoader.setClassLoaders(dexClassLoaders, dexPaths.toArray(new String[0]));
    }

    public static void fix(String hotFixDexPath) {
        DexClassLoader dexClassLoader = new DexClassLoader(hotFixDexPath, RoManager.application.getFilesDir().getAbsolutePath(), null, proxyClassLoader.getParent().getParent());
        proxyClassLoader.setHotClassLoader(dexClassLoader);
    }

    private static void hookInstrument(Application application) throws Exception {
        @SuppressLint("PrivateApi") Class<?> activityThreadClz = Class.forName("android.app.ActivityThread");
        Object activityThread = ReflectUtil.getStaticField(activityThreadClz, "sCurrentActivityThread");
        Object mInstrumentation = ReflectUtil.getField(activityThread, "mInstrumentation");
        ReflectUtil.setField(activityThread, "mInstrumentation", new ProxyInstrumentation((Instrumentation) mInstrumentation));
    }

//    public static void hookActivityInstrument(Activity activity) throws Exception {
//        Object mInstrumentation = ReflectUtil.getField(Activity.class, activity, "mInstrumentation");
//        ReflectUtil.setField(Activity.class, activity, "mInstrumentation", new ProxyInstrumentation((Instrumentation) mInstrumentation));
//    }

    private static void hookClassLoader(Context context) throws Exception {
        @SuppressLint("PrivateApi") Class<?> activityThreadClz = Class.forName("android.app.ActivityThread");
        Object activityThread = ReflectUtil.getStaticField(activityThreadClz, "sCurrentActivityThread");
        String pkgName = context.getPackageName();

        @SuppressWarnings("unchecked") Map<String, WeakReference<?>> mPackages = (Map<String, WeakReference<?>>) ReflectUtil.getField(activityThread, "mPackages");
        assert mPackages != null;
        Object loadedApk = mPackages.get(pkgName).get();
//        Object loadedApk = ReflectUtil.invoke(activityThread, "peekPackageInfo", new Class[]{String.class, Boolean.class}, new Object[]{pkgName, true});
        ClassLoader classLoader = (ClassLoader) ReflectUtil.getField(loadedApk, "mClassLoader");
        proxyClassLoader = new ProxyClassLoader(classLoader);
        ReflectUtil.setField(loadedApk, "mClassLoader", proxyClassLoader);
    }

    private static void hookResources(Context context) throws Exception {
        @SuppressLint("PrivateApi") Class<?> activityThreadClz = Class.forName("android.app.ActivityThread");
        Object activityThread = ReflectUtil.getStaticField(activityThreadClz, "sCurrentActivityThread");
        String pkgName = context.getPackageName();

        @SuppressWarnings("unchecked") Map<String, WeakReference<?>> mPackages = (Map<String, WeakReference<?>>) ReflectUtil.getField(activityThread, "mResourcePackages");
        assert mPackages != null;
        Object loadedApk = mPackages.get(pkgName).get();
//        Object loadedApk = ReflectUtil.invoke(activityThread, "peekPackageInfo", new Class[]{String.class, Boolean.class}, new Object[]{pkgName, true});
        Resources resources = (Resources) ReflectUtil.getField(loadedApk, "mResources");
        Object mResourcesImpl = ReflectUtil.getField(resources, "mResourcesImpl");
        AssetManager assetManager = (AssetManager) ReflectUtil.getField(mResourcesImpl, "mAssets");
//        AssetManager assetManager = AssetManager.class.newInstance();
        ReflectUtil.setField(mResourcesImpl, "mAssets", assetManager);
        ReflectUtil.setField(loadedApk, "mResources", proxyClassLoader);
    }

    private static void hookAms(Context context) throws Exception {
        Object IActivityManagerSingleton = ReflectUtil.getStaticField(ActivityManager.class, "IActivityManagerSingleton");
        assert IActivityManagerSingleton != null;
        Object iActivityManager = ReflectUtil.getField(IActivityManagerSingleton.getClass().getSuperclass(), IActivityManagerSingleton, "mInstance");
        @SuppressLint("PrivateApi") Class<?> iActivityManagerClz = Class.forName("android.app.IActivityManager");
        ReflectUtil.setField(IActivityManagerSingleton.getClass().getSuperclass(), IActivityManagerSingleton, "mInstance", Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{iActivityManagerClz}, new ProxyIActvityManager(iActivityManager, context.getPackageName())));
    }

    public static void hookActivityResources(Activity activity, String resourcePath) throws Exception {
        Context contextImpl = (Context) ReflectUtil.getField(ContextWrapper.class, activity, "mBase");
        assert contextImpl != null;
        @SuppressLint("PrivateApi") Class<?> contextImplClz = Class.forName("android.app.ContextImpl");
        Resources resources = (Resources) ReflectUtil.getField(contextImplClz, contextImpl, "mResources");
        Object resourceImpl = ReflectUtil.getField(Resources.class, resources, "mResourcesImpl");
        assert resources != null;
        AssetManager assetManager = AssetManager.class.newInstance();
        ReflectUtil.invoke(assetManager, "addAssetPath", new Class[]{String.class}, new Object[]{resourcePath});
        @SuppressLint("PrivateApi") Class<?> resourceImplClz = Class.forName("android.content.res.ResourcesImpl");
        ReflectUtil.setField(resourceImplClz, resourceImpl, "mAssets", assetManager);
    }

    public static void hookActivityResources1(Activity activity, String resourcePath) throws Exception {
        Context contextImpl = (Context) ReflectUtil.getField(ContextWrapper.class, activity, "mBase");
        assert contextImpl != null;
        @SuppressLint("PrivateApi") Class<?> contextImplClz = Class.forName("android.app.ContextImpl");
        Resources resources = (Resources) ReflectUtil.getField(contextImplClz, contextImpl, "mResources");
        AssetManager assetManager = AssetManager.class.newInstance();
        ReflectUtil.invoke(assetManager, "addAssetPath", new Class[]{String.class}, new Object[]{resourcePath});
        Resources newResources = new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
        ReflectUtil.setField(contextImplClz, contextImpl, "mResources", newResources);
    }
}
