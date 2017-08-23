package cn.com.builder.core;

import java.util.List;
import java.util.Map;

/**
 * 修饰接口
 * 想要自定义实现列加工,需要实现这个接口
 */
public interface Decorate {
    /**
     * 加工
     * @param list 数据库查询的列的信息
     * @return
     */
    List process(List<Map<String,String>> list) ;
}
