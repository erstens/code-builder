package cn.com.wa;

import cn.com.builder.core.Decorate;
import cn.com.builder.utils.StringOptUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by wang'ao on 2016/11/24 0024.
 */
public class DemoDecorate implements Decorate {
    @Override
    public List process(List<Map<String, String>> list) {
        for (Map<String, String> map : list) {
            map.put("javaType", "String");
            map.put("javaField", StringOptUtils.toHumpLower(map.get("name")));
        }
        return list;
    }
}
