package cn.com.builder.core;

import cn.com.builder.enums.JdbcType;
import cn.com.builder.utils.StringOptUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by wang'ao on 2016/11/24 0024.
 */
public class ModelBuilder extends AbstractBuilder {
    @Override
    protected List conversionColumn(List<Map<String,String>> list) {
        for (Map<String,String> map : list) {
            map.put("mType", JdbcType.getJavaVal(map.get("type"))) ;
            map.put("mName", StringOptUtils.getSplitFirstToLow(map.get("name").toLowerCase())) ;
            map.put("mNameFirstUpper", StringOptUtils.getSplitEveryFirstToUpper(map.get("name").toLowerCase())) ;
        }
        return list;
    }
}
