package com.xcoder.serialport;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xcoder.serialport.server.IotServer;
import com.xcoder.serialport.utensil.CommonUtil;

import io.vertx.core.Vertx;

import static io.vertx.core.file.impl.FileResolver.CACHE_DIR_BASE_PROP_NAME;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkStoragePermission(this);

        System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory");

        System.setProperty(CACHE_DIR_BASE_PROP_NAME, CommonUtil.appendExternalStorageDirectory("/sp/vx"));

        Vertx.vertx().deployVerticle(new IotServer());

        Log.d("vertx", "startup");
    }

//    /**
//     * MainActivity extends Application
//     * Override super.attachBaseContext
//     * Call MultiDex.install(this);
//     * Or just
//     * MainActivity extends MultiDexApplication
//     *
//     * @param base base
//     */
//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }

    private static String[] PERMISSIONS_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    public static void checkStoragePermission(Activity activity) {
        int csp = ActivityCompat.checkSelfPermission(activity, PERMISSIONS_STORAGE[1]);
        if (PackageManager.PERMISSION_GRANTED != csp) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }
}
