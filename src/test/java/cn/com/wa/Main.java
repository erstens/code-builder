package cn.com.wa;
/**
 * Created by wang'ao on 2016/8/26 0026.
 */

import cn.com.builder.Config;
import cn.com.builder.core.CodeBuilder;

/**
 * @author wang'ao
 * @version 1.0.0
 * @ClassName Main.class
 * @Date 2016/8/26 0026 下午 4:39
 */
public class Main {
    public static void main(String[] args) {
        Config.init();
        CodeBuilder builder = CodeBuilder.getIntance(new DemoDecorate());
        builder.toFile();
        System.out.printf("gen success .");
    }
}