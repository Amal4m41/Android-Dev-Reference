package com.example.basics.Basics

class customTriple<A:Number,B:String,C>(var first:A,var second:B,var third:C){

    fun printTypes(){
        println("type of first is : ${first::class.java}")
        println("type of second is : ${second::class.java}")
        println("type of third is : ${third!!::class.java}")
    }
}

fun main(){
    val c=customTriple(1.5,"string value ","lol")
    c.printTypes()
}