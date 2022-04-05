package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

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
        serialize(person);
        System.out.println(person);


    }

}
