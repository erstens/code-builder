package cn.com.builder;/**
 * Created by wang'ao on 2016/8/26 0026.
 */

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;
import cn.com.builder.core.AbstractBuilder;
import cn.com.builder.utils.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author wang'ao
 * @version 1.0.0
 * @ClassName CodeBuilder.class
 * @Description todo by wang'ao(代码生成器)
 * @Date 2016/8/26 0026 下午 4:52
 */
public class CodeBuilder {
    private CodeBuilder() {

    }
    private static String getCodeStr(AbstractBuilder base, String temName)  {


        FileResourceLoader resourceLoader = new FileResourceLoader(Config.getFileTempletPath(),"utf-8");
        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template t = gt.getTemplate("/" +temName);
        t.binding(base.getBind());
        String str = t.render();
        return str ;
    }
    public static void toFile(AbstractBuilder base) {
        //configure
        File dir = new File(Config.getFileTempletPath()) ;
        File [] templets = dir.listFiles() ;
        for (File f : templets) {
            String text = getCodeStr(base,f.getName());
            FileUtils.createFile(text,Config.getFileOutPath() + File.separator + f.getName());
        }

    }
}
