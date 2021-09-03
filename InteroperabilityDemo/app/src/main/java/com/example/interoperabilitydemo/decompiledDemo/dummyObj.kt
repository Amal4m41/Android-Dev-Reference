package com.example.interoperabilitydemo.decompiledDemo

val c=100

fun dummyFunctionTopLevel(){
    println("this is a top level function declaration")
}

object dummyObj {

    val a=10
    var b=20

    fun diplaySmthg(){
        //Something goes here
    }



    interface dummyInterface{
        fun success()
        fun failed()
    }
}

//To check the decompiled code in java or the byte code:
//Tools -> Kotlin -> Show kotlin bytecode


class dummyClass(var a:Int){

    var b:Int=0;

    init {
        this.a=c;
    }

    constructor(a:Int,b:Int):this(a){
        this.b=c
    }

    fun dummyFunction(){

    }

    companion object{
        fun dummyStaticFunction(){

        }

        var d:Int=40
    }

    interface dummyInterfaceInsideClass{
        fun abc()
    }

}