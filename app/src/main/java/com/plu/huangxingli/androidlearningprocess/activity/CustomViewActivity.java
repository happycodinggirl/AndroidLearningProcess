
package com.plu.huangxingli.androidlearningprocess.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.SilentInstall;

import java.io.File;

public class CustomViewActivity extends BaseActivity {

    private String apkPath="/sdcard/360/app-release.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onSilentInstall();


                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     * 判断手机是否拥有Root权限。
     * @return 有root权限返回true，否则返回false。
     */
    public boolean isRoot() {
        boolean bool = false;
        try {
            bool = (!new File("/system/bin/su").exists()) || (!new File("/system/xbin/su").exists());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }

    public void onSilentInstall() {
        if (!isRoot()) {
            Toast.makeText(this, "没有ROOT权限，不能使用秒装", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(apkPath)) {
            Toast.makeText(this, "请选择安装包！", Toast.LENGTH_SHORT).show();
            return;
        }
        //final Button button = (Button) view;
        //  button.setText("安装中");
        new Thread(new Runnable() {
            @Override
            public void run() {
                SilentInstall installHelper = new SilentInstall();
                final boolean result = installHelper.install(apkPath);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (result) {
                            Toast.makeText(CustomViewActivity.this, "安装成功！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CustomViewActivity.this, "安装失败！", Toast.LENGTH_SHORT).show();
                        }
                        Intent intent = new Intent("com.example.lily.innerapk.MainActivity");
                        intent.addCategory(Intent.CATEGORY_LAUNCHER);
                        ComponentName cn = new ComponentName("com.example.lily.innerapk", "com.example.lily.innerapk.MainActivity");
                        intent.setComponent(cn);
                        //startActivity(intent);
                        //Intent intent=new Intent("com.example.lily.innerapk.MainActivity");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        // button.setText("秒装");
                    }
                });

            }
        }).start();

    }

}
