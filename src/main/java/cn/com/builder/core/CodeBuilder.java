package cn.com.builder.core;

import cn.com.builder.Config;
import cn.com.builder.db.StatementExe;
import cn.com.builder.utils.FileUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
        File[] templets = dir.listFiles();
        for (File f : templets) {
            String text = getCodeStr(f.getName());
            FileUtils.createFile(text, Config.getFileOutPath() + File.separator + f.getName());
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
        Template t = gt.getTemplate("/" + temName);
        t.binding(this.modalData);
        String str = t.render();
        return str;
    }
}
