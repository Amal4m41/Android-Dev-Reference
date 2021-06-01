package com.example.basics.Basics

fun main() {
    val list= listOf<Int>(1,2,3,4,5)
//    println(findtheMultipliedValue(list))
    println(list.findtheMultipliedValue())
}
//fun findtheMultipliedValue(value:List<Int>):Int{
//    var j=1
//    for (i in 0..value.size-1){
////        print(value[i])
//        j *= value[i]
//    }
//    return j
//}
fun List<Int>.findtheMultipliedValue():Int{
    var j=1
    //this is the received list
    for (i in 0..this.size-1){
//        print(value[i])
        j *= this[i]
    }
    return j
}

