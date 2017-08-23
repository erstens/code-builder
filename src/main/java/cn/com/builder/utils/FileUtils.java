package cn.com.builder.utils;/**
 * Created by wang'ao on 2016/8/26 0026.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wang'ao
 * @version 1.0.0
 * @ClassName FileUtils.class
 * @Description by wang'ao(IO工具类)
 * @Date 2016/8/26 0026 下午 4:23
 */
public class FileUtils {
    public static void createFile(String context ,String path) {
        File f = new File(path) ;
        if(!f.exists()) {
            try {
                if(!f.getParentFile().exists()) {
                     f.getParentFile().mkdirs();
                }
                f.createNewFile() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fou = null ;
        try {
            fou = new FileOutputStream(f) ;
            byte[] b = context.getBytes();
            fou.write(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fou.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
