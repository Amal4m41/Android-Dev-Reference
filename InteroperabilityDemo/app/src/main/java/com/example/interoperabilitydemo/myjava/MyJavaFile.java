package com.example.interoperabilitydemo.myjava;

import com.example.interoperabilitydemo.MyCustomKotlinFile;

public class MyJavaFile {

    public static void main(String[] args) {
        //Calling the kotlin method inside a java file
//        int result = MyfirstKt.add(1,2);
        int result = MyCustomKotlinFile.add(1,2);
        System.out.println("Printing from java file : "+result);
    }

    public static int getArea(int l, int b){
        return l*b;
    }



}
