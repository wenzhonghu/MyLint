package cn.somees.myrecycling;

import java.io.File;

/**
 * Created by wenzhonghu on 2017/12/28.
 */

public class FileUtils {
    public static final void test() throws Exception{
        File f = new File("/wenzhonghu");
        f = null;
        f.toString();
        f.canExecute();
        f.canRead();
        f.canRead();
        f.canWrite();
        f.canWrite();
        f.canExecute();
        f.canExecute();
        f.canWrite();
        f.canWrite();
        f.canWrite();
    }
}
