package com.example.basics.Basics


fun main() {
    val list= listOf<Int>(1,2,3,4,5,6)

    //different ways to pass a function (lamda function)

    val checkGreaterThan3:(Int)->Boolean={value:Int -> value>3 }

    val result=list.customFilter(checkGreaterThan3)
//    val result=list.customFilter({value -> value>3})
//    val result=list.customFilter{value -> value>3}
//    val result=list.customFilter{it>3}   //since we only have a single parameter
    print(result)
}

fun List<Int>.customFilter(filterFunction:(Int)->Boolean):List<Int>{
    val resultList= mutableListOf<Int>()
    for(i in this){
        if(filterFunction(i)){
            resultList.add(i)
        }
    }
    return resultList as List<Int>
}

