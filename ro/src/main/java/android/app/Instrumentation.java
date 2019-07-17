package android.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.PersistableBundle;
import android.os.TestLooperManager;
import android.view.KeyEvent;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class Instrumentation {
    public Instrumentation.ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, String target,
            Intent intent, int requestCode, Bundle options) {
        return null;
    }
    
    public void onCreate(Bundle arguments) {
    }

    
    public void start() {
    }

    
    public void onStart() {
    }

    
    public boolean onException(Object obj, Throwable e) {
        return false;
    }

    
    public void sendStatus(int resultCode, Bundle results) {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    
    public void addResults(Bundle results) {
    }

    
    public void finish(int resultCode, Bundle results) {
    }

    
    public void setAutomaticPerformanceSnapshots() {
    }

    
    public void startPerformanceSnapshot() {
    }

    
    public void endPerformanceSnapshot() {
    }

    
    public void onDestroy() {
    }

    
    public Context getContext() {
        return null;
    }

    
    public ComponentName getComponentName() {
        return null;
    }

    
    public Context getTargetContext() {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    
    public String getProcessName() {
        return null;
    }

    
    public boolean isProfiling() {
        return false;
    }

    
    public void startProfiling() {
    }

    
    public void stopProfiling() {
    }

    
    public void setInTouchMode(boolean inTouch) {
    }

    
    public void waitForIdle(Runnable recipient) {
    }

    
    public void waitForIdleSync() {
    }

    
    public void runOnMainSync(Runnable runner) {
    }

    
    public Activity startActivitySync(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    
    public Activity startActivitySync(Intent intent, Bundle options) {
        return null;
    }

    
    public void addMonitor(Instrumentation.ActivityMonitor monitor) {
    }

    
    public Instrumentation.ActivityMonitor addMonitor(IntentFilter filter, ActivityResult result, boolean block) {
        return null;
    }

    
    public Instrumentation.ActivityMonitor addMonitor(String cls, ActivityResult result, boolean block) {
        return null;
    }

    
    public boolean checkMonitorHit(Instrumentation.ActivityMonitor monitor, int minHits) {
        return false;
    }

    
    public Activity waitForMonitor(Instrumentation.ActivityMonitor monitor) {
        return null;
    }

    
    public Activity waitForMonitorWithTimeout(Instrumentation.ActivityMonitor monitor, long timeOut) {
        return null;
    }

    
    public void removeMonitor(Instrumentation.ActivityMonitor monitor) {
    }

    
    public boolean invokeMenuActionSync(Activity targetActivity, int id, int flag) {
        return false;
    }

    
    public boolean invokeContextMenuAction(Activity targetActivity, int id, int flag) {
        return false;
    }

    
    public void sendStringSync(String text) {
    }

    
    public void sendKeySync(KeyEvent event) {
    }

    
    public void sendKeyDownUpSync(int key) {
    }

    
    public void sendCharacterSync(int keyCode) {
    }

    
    public void sendPointerSync(MotionEvent event) {
    }

    
    public void sendTrackballEventSync(MotionEvent event) {
    }

    
    public Application newApplication(ClassLoader cl, String className, Context context) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return null;
    }

    public static Application newApplication(Class<?> clazz, Context context) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return null;
    }

    
    public void callApplicationOnCreate(Application app) {
    }

    
    public Activity newActivity(Class<?> clazz, Context context, IBinder token, Application application, Intent intent, ActivityInfo info, CharSequence title, Activity parent, String id, Object lastNonConfigurationInstance) throws IllegalAccessException, InstantiationException {
        return null;
    }

    
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return null;
    }

    
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
    }

    
    public void callActivityOnCreate(Activity activity, Bundle icicle, PersistableBundle persistentState) {
    }

    
    public void callActivityOnDestroy(Activity activity) {
    }

    
    public void callActivityOnRestoreInstanceState(@androidx.annotation.NonNull Activity activity, @androidx.annotation.NonNull Bundle savedInstanceState) {
    }

    
    public void callActivityOnRestoreInstanceState(@androidx.annotation.NonNull Activity activity, @Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
    }

    
    public void callActivityOnPostCreate(@androidx.annotation.NonNull Activity activity, @Nullable Bundle savedInstanceState) {
    }

    
    public void callActivityOnPostCreate(@androidx.annotation.NonNull Activity activity, @Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
    }

    
    public void callActivityOnNewIntent(Activity activity, Intent intent) {
    }

    
    public void callActivityOnStart(Activity activity) {
    }

    
    public void callActivityOnRestart(Activity activity) {
    }

    
    public void callActivityOnResume(Activity activity) {
    }

    
    public void callActivityOnStop(Activity activity) {
    }

    
    public void callActivityOnSaveInstanceState(@androidx.annotation.NonNull Activity activity, @androidx.annotation.NonNull Bundle outState) {
    }

    
    public void callActivityOnSaveInstanceState(@androidx.annotation.NonNull Activity activity, @androidx.annotation.NonNull Bundle outState, @androidx.annotation.NonNull PersistableBundle outPersistentState) {
    }

    
    public void callActivityOnPause(Activity activity) {
    }

    
    public void callActivityOnUserLeaving(Activity activity) {
    }

    
    @Deprecated
    public void startAllocCounting() {
    }

    
    @Deprecated
    public void stopAllocCounting() {
    }

    
    public Bundle getAllocCounts() {
        return null;
    }

    
    public Bundle getBinderCounts() {
        return null;
    }

    
    public UiAutomation getUiAutomation() {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    
    public UiAutomation getUiAutomation(int flags) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    
    public TestLooperManager acquireLooperManager(Looper looper) {
        return null;
    }

    public static class ActivityResult{

    }

    public static class ActivityMonitor{

    }
}
