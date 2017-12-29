package com.xiaoniu.gradle.mylint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.System.getProperty;

/**
 * Created by wenzhonghu on 2017/12/28.
 */

public class FileUtils {
    private static final int BUFFER_SIZE = 2 * 1024 * 1024; //2M
    public static void copyResourceFile(String name, File dest) throws IOException {
        FileOutputStream os = null;
        File parent = dest.getParentFile();
        if (parent != null && (!parent.exists())) {
            parent.mkdirs();
        }
        InputStream is = null;

        try {
            is = FileUtils.class.getResourceAsStream("/" + name);
            os = new FileOutputStream(dest, false);

            byte[] buffer = new byte[BUFFER_SIZE];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }

    public static boolean isWin(){
        String os  = System.getProperty("os.name");
        return os.toLowerCase().startsWith("win");
    }
}
