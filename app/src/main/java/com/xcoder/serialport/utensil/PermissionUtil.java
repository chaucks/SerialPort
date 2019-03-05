package com.xcoder.serialport.utensil;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Permission util
 *
 * @author Chuck Lee
 */
public class PermissionUtil {

    public static final int REQUEST_CODE_STORAGE = 1;

    public static final String[] PERMISSIONS_STORAGE = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

    /**
     * 存储权限验证
     *
     * @param activity activity
     */
    public static void checkStoragePermission(Activity activity) {
        checkPermission(PERMISSIONS_STORAGE[1], activity, REQUEST_CODE_STORAGE, PERMISSIONS_STORAGE);
    }

    public static void checkPermission(String permission, Activity activity, int requestCode, String... permissions) {
        int csp = ActivityCompat.checkSelfPermission(activity, permission);
        if (PackageManager.PERMISSION_GRANTED != csp) {
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
        }
    }

}
