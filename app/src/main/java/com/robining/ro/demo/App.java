package com.robining.ro.demo;

import android.app.Application;
import android.content.Context;

import com.robining.ro.Ro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.DexClassLoader;

public class App extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            Ro.init(this);
            installPlugin();
            installHotFix();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(">>>ro init failed");
        }
    }

    private void installPlugin() {
        try {
            InputStream is = getAssets().open("plugindemo-debug.apk");
            File file = File.createTempFile("plugindemo-debug", ".apk");
            System.out.println(">>>plugin path:" + file.getAbsolutePath());
            if (!file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                fos.write(buf, 0, len);
            }
            fos.close();
            is.close();
            file.deleteOnExit();
            List<String> dexPath = new ArrayList<>();
            dexPath.add(file.getAbsolutePath());
            Ro.installPlugins(dexPath);
            System.out.println(">>>plugin install success");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(">>>plugin install failed");
        }
    }

    private void installHotFix() {
        try {
            InputStream is = getAssets().open("hotfix.dex");
            File file = File.createTempFile("hotfix", ".dex");
            System.out.println(">>>hot fix path:" + file.getAbsolutePath());
            if (!file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                fos.write(buf, 0, len);
            }
            fos.close();
            is.close();
            file.deleteOnExit();
            try {
                Class<?> clz = new DexClassLoader(file.getAbsolutePath(),new File(getFilesDir(),"dex").getAbsolutePath(),null,getClassLoader().getParent().getParent()).loadClass("com.robining.ro.demo.MainActivity");
                System.out.println("");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Ro.fix(file.getAbsolutePath());
            System.out.println(">>>hotfix install success");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(">>>hotfix install failed");
        }
    }
}
