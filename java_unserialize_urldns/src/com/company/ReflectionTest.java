package com.company;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {
    public static void main(String[] args) throws Exception{
        Person person = new Person();
        Class c = person.getClass();
        //Class.forName("person");
        //反射也即操作Class

        //从原型Class实例化对象
        //qaq.newInstance();
        //感觉反射就是类似于镜像
        Constructor personconstructor = c.getConstructor(String.class,int.class);  //构造函数
        Person p = (Person) personconstructor.newInstance("abv",22);
        System.out.println(p);

        //获取类的属性,私有的属性也会打印c
//        Field[] personfields =  c.getDeclaredFields();
//        for(Field f:personfields){
//            System.out.println(f);
//        }
        //从原型修改类的属性,但是getField不能修改私有属性
//        Field namefield = c.getField("name");
//        namefield.set(p,"qaq");
//        System.out.println(p);
        //修改私有属性 namefield.setAccessible(true);
        //直接用getDeclaredField + namefield.setAccessible(true)即可
        Field namefield = c.getDeclaredField("age");
        namefield.setAccessible(true);
        namefield.set(p,25);
        System.out.println(p);



        //调用类的方法
//        Method[] personmethods= c.getMethods();
//        for(Method m:personmethods){
//            System.out.println(m);
//        }
        //调用某一方法,比如调用string的action
      //  Method actionmethod= c.getMethod("action",String.class);
       // actionmethod.invoke(p,"wadsewqeqw");


    }

}
