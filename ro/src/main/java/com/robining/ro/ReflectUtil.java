//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.robining.ro;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.PublicKey;

import org.apache.tools.ant.BuildException;

public class ReflectUtil {
    private ReflectUtil() {
    }

    public static Object newInstance(Class ofClass, Class[] argTypes, Object[] args) {
        try {
            Constructor con = ofClass.getConstructor(argTypes);
            return con.newInstance(args);
        } catch (Exception var4) {
            throwBuildException(var4);
            return null;
        }
    }

    public static Object invoke(Class<?> clz, Object obj, String methodName, Class<?>[] argTypes, Object[] args) {
        try {
            Method method = clz.getMethod(methodName, argTypes);
            return method.invoke(obj, args);
        } catch (Exception var3) {
            throwBuildException(var3);
            return null;
        }
    }

    public static Object invoke(Object obj, String methodName, Class<?>[] argTypes, Object[] args) {
        try {
            Method method = obj.getClass().getMethod(methodName, argTypes);
            return method.invoke(obj, args);
        } catch (Exception var3) {
            throwBuildException(var3);
            return null;
        }
    }

    public static Object invokeStatic(Class<?> clz, String methodName, Class<?>[] argTypes, Object[] args) {
        try {
            Method method = clz.getMethod(methodName, argTypes);
            return method.invoke(null, args);
        } catch (Exception var3) {
            throwBuildException(var3);
            return null;
        }
    }

    public static Object getField(Class<?> clz, Object obj, String fieldName) throws BuildException {
        try {
            Field field = clz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception var3) {
            throwBuildException(var3);
            return null;
        }
    }

    public static Object getField(Object obj, String fieldName) throws BuildException {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception var3) {
            throwBuildException(var3);
            return null;
        }
    }

    public static Object getStaticField(Class<?> cls, String fieldName) throws BuildException {
        try {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(null);
        } catch (Exception var3) {
            throwBuildException(var3);
            return null;
        }
    }

    public static void setStaticField(Class<?> cls, String fieldName, Object value) throws BuildException {
        try {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(null, value);
        } catch (Exception var3) {
            throwBuildException(var3);
        }
    }

    public static void setField(Class<?> clz, Object object, String fieldName, Object value) throws BuildException {
        try {
            Field field = clz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception var3) {
            throwBuildException(var3);
        }
    }

    public static void setField(Object object, String fieldName, Object value) throws BuildException {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception var3) {
            throwBuildException(var3);
        }
    }

    public static void throwBuildException(Exception t) throws BuildException {
        throw toBuildException(t);
    }

    public static BuildException toBuildException(Exception t) {
        if (t instanceof InvocationTargetException) {
            Throwable t2 = ((InvocationTargetException) t).getTargetException();
            return t2 instanceof BuildException ? (BuildException) t2 : new BuildException(t2);
        } else {
            return new BuildException(t);
        }
    }

    public static boolean respondsTo(Object o, String methodName) throws BuildException {
        try {
            Method[] methods = o.getClass().getMethods();

            for (int i = 0; i < methods.length; ++i) {
                if (methods[i].getName().equals(methodName)) {
                    return true;
                }
            }

            return false;
        } catch (Exception var4) {
            throw toBuildException(var4);
        }
    }
}
