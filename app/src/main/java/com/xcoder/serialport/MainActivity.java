package com.xcoder.serialport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xcoder.serialport.server.IotServer;
import com.xcoder.serialport.utensil.CommonUtil;
import com.xcoder.serialport.utensil.PermissionUtil;

import io.vertx.core.Vertx;

import static io.vertx.core.file.impl.FileResolver.CACHE_DIR_BASE_PROP_NAME;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vertx http server log4j configure
        System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory");

        // Vertx http server cache dir configure
        PermissionUtil.checkStoragePermission(this);
        System.setProperty(CACHE_DIR_BASE_PROP_NAME, CommonUtil.appendExternalStorageDirectory("/sp/vx"));

        Vertx.vertx().deployVerticle(new IotServer());

        Log.d("Vertx", "startup");
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


}
