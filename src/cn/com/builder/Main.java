package cn.com.builder; /**
 * Created by wang'ao on 2016/8/26 0026.
 */

import cn.com.builder.core.ModelBuilder;

/**
 * @author wang'ao
 * @version 1.0.0
 * @ClassName Main.class
 * @Description todo by wang'ao(Main)
 * @Date 2016/8/26 0026 下午 4:39
 */
public class Main {
    public static void main(String[] args) {
        Config.init("/conf/conf.properties") ;
        CodeBuilder.toFile(new ModelBuilder()) ;
        System.out.printf("gen success .");
    }
}
