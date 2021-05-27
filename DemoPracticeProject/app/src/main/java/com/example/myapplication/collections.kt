package com.example.myapplication

fun main() {
    var lst=Array<Int>(5) {0}   //mutable, fixed size
    lst[0]=100
    for(i in lst){
        println(i)
    }
}