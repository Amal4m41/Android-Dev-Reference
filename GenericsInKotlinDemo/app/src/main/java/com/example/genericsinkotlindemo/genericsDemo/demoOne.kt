package com.example.genericsinkotlindemo.genericsDemo

import kotlin.reflect.typeOf

//Generics is a powerful feature that we can use to define classes or functions that can work with different data types

//class ArrayUtil(private val array: Array<Int>){
//    fun findElement(element: Int,action: (foundAt: Int, elementValue: Int?)->Unit){
//
//        for(i in array.indices){
//            if(array[i]==element){
//                action(i,array[i])
//                return //exit this function
//            }
//        }
//        action(-1,null)
//    }
//}

//1- Creating a generic class so that it supports multiple datatypes
//class ArrayUtil<T>(private val array: Array<T>){
//    fun findElement(element: T,action: (foundAt: Int, elementValue: T?)->Unit){
//
//        for(i in array.indices){
//            if(array[i]==element){
//                action(i,array[i])
//                return //exit this function
//            }
//        }
//        action(-1,null)
//    }
//}

//2- Doing the same thing with a generic function

fun<T> findElement(array: Array<T>,element: T,action: (foundAt: Int, elementValue: T?)->Unit){

    for(i in array.indices){
        if(array[i]==element){
            action(i,array[i])
            return //exit this function
        }
    }
    action(-1,null)
}

fun<T,X> justForTesting(a:T, b:X){
    println("$a and $b")
}


fun main() {
//    val arrInt = arrayOf(1,2,3,4,5);
    val arrString = arrayOf("Apple","Pineapple","Orange");
//
//    val arrayUtil1 = ArrayUtil<Int>(arrInt)
//    val arrayUtil2 = ArrayUtil<String>(arrString)
//
//    arrayUtil1.findElement(4,fun(a, b){
//        println("$b found at $a")
//    })
//    arrayUtil2.findElement("Ornge",fun(a, b){
//        println("$b found at $a")
//    })


//    val arr = arrayOf(1,2,3,4,5)
//
//    findElement<Int>(arr,4,fun(a, b){
//        println("$b found at $a")
//    })

    justForTesting<Int,String>(1,"burno")
}