package com.xcoder.serialport.utensil;

/**
 * Common utilities
 *
 * @author Chuck Lee
 */
public class CommonUtil {

    /**
     * Get android external storage path
     *
     * @param relativePaths relativePaths
     * @return
     */
    public static String appendExternalStorageDirectory(final String... relativePaths) {
        final StringBuilder builder = new StringBuilder(128);
        builder.append(android.os.Environment.getExternalStorageDirectory());
        for (String path : relativePaths) {
            if (null != path) {
                builder.append(path);
            }
        }
        final String result = builder.toString();
        return result;
    }

}
