package cn.com.builder.core;

import cn.com.builder.Config;
import cn.com.builder.db.StatementExe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang'ao on 2016/11/24 0024.
 */
public abstract class AbstractBuilder {
    private Map bind;
    public AbstractBuilder() {
        bind = new HashMap<>() ;
        bind.put("columns",conversionColumn(StatementExe.queryColumns()));
        bind.putAll(Config.getOwnProp());
    }

    /**
     * 实现数据库列的转换
     * @param list
     * @return
     */
    protected abstract List conversionColumn(List<Map<String,String>> list) ;
    public Map getBind() {
        return this.bind;
    }
}
