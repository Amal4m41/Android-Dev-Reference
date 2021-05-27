package com.example.constraintlayoutdemo

fun main() {
    Myclass.a=5
    println(Myclass.custMethod())
    println(Myclass.a)

    val ob:Myclass=Myclass()
    ob.b=3
    println(ob.b)
    println(ob.dummy())
}

class Myclass{
    var b=3
    companion object           //what we define inside of companion object acts as static
    {
        var a=1               //like a static property

        fun custMethod():String{    //like a static method
            return "this is customer"
        }
    }
    fun dummy(){
        print("Inside dummy instance specific method")
        println(a)
        print(custMethod())    //OR print(Myclass.custMethod())
    }
}



//object Customer{   //behaves singleton class
//    var a=1
//
//    fun custMethod():String{
//        return "this is customer"
//    }
//}