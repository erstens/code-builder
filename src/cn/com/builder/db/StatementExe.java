package cn.com.builder.db;/**
 * Created by wang'ao on 2016/8/26 0026.
 */

import cn.com.builder.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wang'ao
 * @version 1.0.0
 * @ClassName StatementExe.class
 * @Description todo by wang'ao(查询执行器)
 * @Date 2016/8/26 0026 下午 6:44
 */
public class StatementExe {

    private static Connection conn ;
    static {
        Connector.init(Config.getDb());
        conn = Connector.getConnection() ;
    }
    private StatementExe() {

    }

    /**
     * 查询sql
     * @param sql
     * @return
     */
    public static List<Map<String,Object>> query(String sql) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount() ;
            List<Map<String,Object>> list = new ArrayList<>() ;
            while(rs.next())  {
                Map<String,Object> map = new HashMap<>() ;
                for(int i = 0 ;i < colCount ;i ++) {
                    String name = rsmd.getColumnName(i + 1);
                    map.put(name,rs.getString(name)) ;
                }
                list.add(map);
            }

            rs.close();
            ps.close();
            return list ;

        } catch (SQLException e) {
            System.err.println("此数据库表不存在.");
            System.err.println(sql);
        }
        return null ;
    }

    /**
     * 查询设定的表的列
     * @return
     */
    public static List queryColumns() {
        DBProperty db = Config.getDb();
        //query
        List<Map<String, Object>> columns = StatementExe.query("SELECT column_name ,column_type ,column_comment,is_nullable ,column_key ,extra FROM INFORMATION_SCHEMA.Columns WHERE table_name='" + db.getTableName() +"' AND table_schema='" + db.getTableSchema() +"' ");
        Connector.close();

        for (Map<String, Object> column : columns) {
            column.put("name",column.get("COLUMN_NAME")) ;
            column.put("type",column.get("COLUMN_TYPE")) ;
            column.put("comment",column.get("COLUMN_COMMENT")) ;
            column.put("isNullable",column.get("IS_NULLABLE")) ;
            column.put("key",column.get("COLUMN_KEY")) ;

            column.remove("COLUMN_NAME") ;
            column.remove("COLUMN_TYPE") ;
            column.remove("COLUMN_COMMENT") ;
            column.remove("IS_NULLABLE") ;
            column.remove("COLUMN_KEY") ;
        }
        return columns ;
    }
}
