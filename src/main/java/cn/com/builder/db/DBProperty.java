package cn.com.builder.db;/**
 * Created by wang'ao on 2016/8/26 0026.
 */

/**
 * @author wang'ao
 * @version 1.0.0
 * @ClassName DBProperty.class
 * @Description by wang'ao(数据库属性)
 * @Date 2016/8/26 0026 下午 6:41
 */
public class DBProperty {
    private String url ;
    private String user ;
    private String pwd ;
    private String tableName ;
    private String tableSchema ;

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
