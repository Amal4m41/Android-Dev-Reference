@file:JvmName("MyCustomKotlinFile")
package com.example.interoperabilitydemo

import com.example.interoperabilitydemo.myjava.MyJavaFile



fun add(a:Int, b:Int):Int{
    return a+b
}

fun main(args: Array<String>) {
    println(add(1,2))

    //Accessing java method from kotlin file
    val area = MyJavaFile.getArea(4,3)
    println(area)

    MyJavaFile().displayName()
}


//After compiling into byte code, this kotlin file will be no different from the byte code(MyFirstKt.Class) of a
//java file like:
/*
public class MyfirstKt{
    public static void main(String[] args){
        System.out.println(add(1,2))
    }

    public static Int add(Int a, Int b){
       return a+b;
    }
}
 */