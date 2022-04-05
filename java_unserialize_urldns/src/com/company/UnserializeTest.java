package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.HashMap;

public class UnserializeTest {
    public static Object unserialize(String Filename) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Filename));
        Object obj = ois.readObject();
        /*
        读对象，反序列化
         */
        return obj;
    }
    public static void main(String[] args) throws Exception{
        //Person person = (Person)unserialize("ser.bin");
        //System.out.println(person);
        unserialize("ser.bin");
    }

}
