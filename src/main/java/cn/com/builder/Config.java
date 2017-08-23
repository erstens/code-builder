package cn.com.builder;

import cn.com.builder.db.DBProperty;
import cn.com.builder.utils.StringOptUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by wang'ao on 2016/8/28.
 * 全局配置
 */
public class Config {
    private static DBProperty db;
    private static String fileTempletPath;
    private static String fileOutPath;
    private static Map<String, String> propAddon;
    private static final String PATH = "/conf.properties";

    private Config() {

    }

    private static Config config;

    public static Config init() {
        String path = PATH;
        if (null == config) {
            //prop
            config = new Config();
            Properties prop = new Properties();
            InputStream in = Config.class.getResourceAsStream(path);
            try {
                prop.load(in);
            } catch (IOException e) {
                System.out.printf("加载配置文件异常!");
                e.printStackTrace();
            }
            //addon prop
            propAddon = new HashMap();
            for (String name : prop.stringPropertyNames()) {
                String key = StringOptUtils.toHumpLower(name, "\\.");
                String val = prop.getProperty(name);

                propAddon.put(key, val);
            }
            //db
            db = new DBProperty();
            String dbUrl = propAddon.get("dbUrl");
            db.setUrl(dbUrl);
            db.setUser(propAddon.get("dbUser"));
            db.setPwd(propAddon.get("dbPwd"));
            db.setTableName(propAddon.get("dbTableName"));
            db.setTableSchema(dbUrl.contains("?") ? dbUrl.substring(dbUrl.lastIndexOf("/") + 1, dbUrl.indexOf("?")) : dbUrl.substring(dbUrl.lastIndexOf("/") + 1));

            fileTempletPath = Config.class.getResource("/template").getPath();
            fileOutPath = Config.class.getResource("/_out").getPath();

            //remove db prop,the db prop is saved in "DBProperty" .
            propAddon.remove("dbUrl");
            propAddon.remove("dbUser");
            propAddon.remove("dbPwd");
            propAddon.remove("dbTaleName");
        }
        return config;
    }

    public static DBProperty getDb() {
        return db;
    }

    public static Map<String, String> getPropAddon() {
        return propAddon;
    }

    public static String getFileTempletPath() {
        return fileTempletPath;
    }

    public static String getFileOutPath() {
        return fileOutPath;
    }
}
