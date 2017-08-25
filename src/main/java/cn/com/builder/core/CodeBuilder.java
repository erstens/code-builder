package cn.com.builder.core;

import cn.com.builder.Config;
import cn.com.builder.db.StatementExe;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 建造类,用于将数据列绑定在模版中
 * Created by wang'ao on 2016/11/24 0024.
 */
public class CodeBuilder {
    private Map modalData;
    private static CodeBuilder codeBuilder;

    private CodeBuilder(Decorate d) {
        modalData = new HashMap<>();
        modalData.put("columns", d.process(StatementExe.queryColumns()));
        modalData.putAll(Config.getPropAddon());
    }

    public static CodeBuilder getIntance(Decorate d) {
        return codeBuilder == null ? new CodeBuilder(d) : codeBuilder;
    }

    public void toFile() {
        File dir = new File(Config.getFileTempletPath());
        List<File> templets = new ArrayList<>(FileUtils.listFiles(dir, TrueFileFilter.INSTANCE,TrueFileFilter.INSTANCE));
        for (File f : templets) {
            String relativePath = f.getPath().split(dir.getPath().replaceAll("\\\\","\\\\\\\\"))[1] ;
            String relativeUrl = f.getPath().replaceAll("\\\\","/").split(dir.getPath().replaceAll("\\\\","/"))[1] ;
            String text = getCodeStr(relativeUrl);
            try {
                FileUtils.writeStringToFile(new File(Config.getFileOutPath() + relativePath),text, Charsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    String getCodeStr(String temName) {
        FileResourceLoader resourceLoader = new FileResourceLoader(Config.getFileTempletPath(), "utf-8");
        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template t = gt.getTemplate(temName);
        t.binding(this.modalData);
        String str = t.render();
        return str;
    }
}
