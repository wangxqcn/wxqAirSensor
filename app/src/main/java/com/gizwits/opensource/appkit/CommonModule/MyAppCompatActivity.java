package com.gizwits.opensource.appkit.CommonModule;


import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by wangxq on 2019/4/25 025.
 */

public class MyAppCompatActivity extends AppCompatActivity {

    //onCreate –> onContentChanged –> onStart –> onPostCreate
    // –> onResume –> onPostResume –> onPause –> onStop –> onDestroy

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
