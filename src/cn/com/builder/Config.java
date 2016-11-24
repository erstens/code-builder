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
    private static String fileTempletPath ;
    private static String fileOutPath ;
    private static Map<String,String> ownProp;
    private Config() {

    }
    private static Config config ;
    public static Config init(String path) {
        if(null == config) {
            //prop
            config = new Config() ;
            Properties prop = new Properties();
            InputStream in = Config.class.getResourceAsStream(path);
            try {
                prop.load(in);
            } catch (IOException e) {
                System.out.printf("加载配置文件异常!");
                e.printStackTrace();
            }
            //own prop
            ownProp = new HashMap();
            for (String name : prop.stringPropertyNames()) {
                String key = StringOptUtils.getSplitFirstToLow(name,"\\.") ;
                String val = prop.getProperty(name) ;

                ownProp.put(key,val) ;
            }
            //db
            db = new DBProperty();
            String dbUrl = ownProp.get("dbUrl") ;
            db.setUrl(dbUrl);
            db.setUser(ownProp.get("dbUser"));
            db.setPwd(ownProp.get("dbPwd"));
            db.setTableName(ownProp.get("dbTableName"));
            db.setTableSchema(dbUrl.contains("?") ? dbUrl.substring(dbUrl.lastIndexOf("/") + 1,dbUrl.indexOf("?")) : dbUrl.substring(dbUrl.lastIndexOf("/") + 1));

            fileTempletPath = ownProp.get("fileTempletPath") ;
            fileOutPath = ownProp.get("fileOutPath") ;

            //remove own
            ownProp.remove("dbUrl") ;
            ownProp.remove("dbUser") ;
            ownProp.remove("dbPwd") ;
            ownProp.remove("dbTaleName") ;

            ownProp.remove("fileTempletPath") ;
            ownProp.remove("fileOutPath") ;
        }
        return config ;
    }
    public static DBProperty getDb() {
        return db;
    }

    public static Map<String, String> getOwnProp() {
        return ownProp;
    }

    public static String getFileTempletPath() {
        return fileTempletPath;
    }

    public static String getFileOutPath() {
        return fileOutPath;
    }
}
