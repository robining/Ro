package com.robining.ro.proxy;

import android.content.ComponentName;
import android.content.Intent;

import com.robining.ro.RoManager;
import com.robining.ro.stub.StubActivity01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyIActvityManager implements InvocationHandler {
    private Object realIActivityManager;
    private String pkgName;

    public ProxyIActvityManager(Object realIActivityManager, String pkgName) {
        this.realIActivityManager = realIActivityManager;
        this.pkgName = pkgName;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        System.out.println(">>>invoke " + method.getName());
        if (method.getName().equals("startActivity")) {
            int intentArgIndex = -1;
            Intent intent = null;
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (arg instanceof Intent) {
                    intentArgIndex = i;
                    intent = (Intent) arg;
                    break;
                }
            }

            if (intentArgIndex != -1) {
                ComponentName realComponent = intent.getComponent();
                intent.setClassName(pkgName, StubActivity01.class.getName());
                intent.putExtra(RoManager.KEY_REAL_TARGET, realComponent);
            }
        }
        return method.invoke(realIActivityManager, args);
    }
}
