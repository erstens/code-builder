package cn.com.builder.enums;

/**
 * Created by wang'ao on 2016/8/31 0031.
 */

/**
 * jdbc,java数据类型对应
 */
public enum JdbcType {
    VARCHAR("varchar","String") ,
    INTEGER("int","Integer")
    ;
    private String jdbcVal ;
    private String javaVal ;
    JdbcType(String varchar, String string) {
        this.jdbcVal = varchar;
        this.javaVal = string ;
    }
    public static String getJavaVal(String jdbcVal) {
        if(null == jdbcVal) return null ;
        JdbcType[] values = JdbcType.values();
        for (JdbcType value : values) {
            if(jdbcVal.toLowerCase().contains(value.getJdbcVal()))
            return value.getJavaVal() ;
        }
        return JdbcType.VARCHAR.getJavaVal();
    }

    public String getJdbcVal() {
        return jdbcVal;
    }

    public String getJavaVal() {
        return javaVal;
    }
}
