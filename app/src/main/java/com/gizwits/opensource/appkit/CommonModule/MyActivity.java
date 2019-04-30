package com.gizwits.opensource.appkit.CommonModule;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by wangxq on 2019/4/25 025.
 */

public class MyActivity extends Activity {
    //onCreate –> onContentChanged –> onStart –> onPostCreate
    // –> onResume –> onPostResume –> onPause –> onStop –> onDestroy

    /**
     * 网上搞来的函数,貌似不能工作
     *
     *
     */
    public static List<Activity> getActivitiesByApplication(Application application) {
        List<Activity> list = new ArrayList<>();
        try {
            Class<Application> applicationClass = Application.class;
            Field mLoadedApkField = applicationClass.getDeclaredField("mLoadedApk");
            mLoadedApkField.setAccessible(true);
            Object mLoadedApk = mLoadedApkField.get(application);
            Class<?> mLoadedApkClass = mLoadedApk.getClass();
            Field mActivityThreadField = mLoadedApkClass.getDeclaredField("mActivityThread");
            mActivityThreadField.setAccessible(true);
            Object mActivityThread = mActivityThreadField.get(mLoadedApk);
            Class<?> mActivityThreadClass = mActivityThread.getClass();
            Field mActivitiesField = mActivityThreadClass.getDeclaredField("mActivities");
            mActivitiesField.setAccessible(true);
            Object mActivities = mActivitiesField.get(mActivityThread);
            // 注意这里一定写成Map，低版本这里用的是HashMap，高版本用的是ArrayMap
            if (mActivities instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<Object, Object> arrayMap = (Map<Object, Object>) mActivities;
                for (Map.Entry<Object, Object> entry : arrayMap.entrySet()) {
                    Object value = entry.getValue();
                    Class<?> activityClientRecordClass = value.getClass();
                    Field activityField = activityClientRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Object o = activityField.get(value);
                    list.add((Activity) o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String parent = "";
        if (this.getPackageManager().resolveActivity(this.getIntent(),0) !=null) {
            parent += "resolveActivity";
        }
        if (this.getPackageManager().resolveService(this.getIntent(),0) !=null) {
            parent += "resolveService";
        }
        if (this.isTaskRoot()) {
            parent += " is TaskRoot";
        } else {
            parent += " not TaskRoot";
        }
        Log.w("wxq",this.toString() + "[" + this.getTaskId()+ "]" + ".onCreate" + " <==> " + parent);
        ComponentName componentName = this.getCallingActivity();
        if (componentName == null){
            //通过startActivity()启动
        } else {
            //通过startActivityForResult()启动
            Log.w("wxq","通过startActivityForResult()启动" + componentName.getShortClassName());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w("wxq",this.toString() + "[" + this.getTaskId()+ "]"  + ".onStart");
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.w("wxq",this.toString() + "[" + this.getTaskId()+ "]"  + ".onPostCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("wxq",this.toString() + "[" + this.getTaskId()+ "]"  + ".onResume");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.w("wxq",this.toString() + "[" + this.getTaskId()+ "]"  + ".onPostResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("wxq",this.toString() + "[" + this.getTaskId()+ "]"  + ".onPause");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.w("wxq",this.toString() + "[" + this.getTaskId()+ "]"  + ".onNewIntent");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("wxq",this.toString() + "[" + this.getTaskId()+ "]"  + ".onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("wxq",this.toString() + "[" + this.getTaskId()+ "]"  + ".onDestroy");
    }
}
