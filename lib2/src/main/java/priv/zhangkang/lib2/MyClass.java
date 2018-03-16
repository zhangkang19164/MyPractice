package priv.zhangkang.lib2;


import java.lang.reflect.Field;

import priv.zhangkang.lib2.reflection.ReflectionClass;
import priv.zhangkang.lib2.reflection.Test1;

public class MyClass {
    public static void main(String args[]) {
        ReflectionClass reflectionClass = new ReflectionClass();
        reflectionClass.setString1("第一个字段");

        Class<? extends ReflectionClass> aClass = reflectionClass.getClass();
        try {
            Field mString1 = aClass.getDeclaredField("mMyTest");
            mString1.setAccessible(true);//允许访问私有字段
            Object fieldValue = mString1.get(reflectionClass);
            Class<?> aClass1 = fieldValue.getClass();
            Field mString = aClass1.getDeclaredField("mString");
            mString.setAccessible(true);//允许访问私有字段
            Object o = mString.get(fieldValue);
            System.out.println(o);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
