package com.company;

import jdk.internal.org.objectweb.asm.tree.ClassNode;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Person implements Serializable {
    public   String name;
    private int age;

    public Person() {

    }

    public  Person(String name, int age){
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    private void readObject(ObjectInputStream ois) throws IOException , ClassNotFoundException{
        ois.defaultReadObject();
        Runtime.getRuntime().exec("calc");
    }
}
