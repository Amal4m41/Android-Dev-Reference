package com.example.basics.Basics

fun main() {
    val num:Int?= readLine()?.toInt()

//    print(num?.checkOdd())
    print(checkOdd(num?:1))
}


//fun Int.checkOdd():Boolean{
//    return (this%2==1)
//}

fun checkOdd(value:Int):Boolean{
    return value%2==1
}