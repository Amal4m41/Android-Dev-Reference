package test_classes

fun main() {
    val arr=Array<Int>(5){0}
    for(i in 0..arr.size-1){
        arr[i]=i+1
    }

    val lst= ArrayList<Int>(6)
    for(i in 0..6-1){
        lst.add(i+1)
    }

    display(arr)
    display(lst)
}

fun display(value:Array<Int>){
    println("for array")
    val len=Math.ceil(value.size/2.0-1).toInt()
    for(i in 0..len){
        println(value[i])
        if(value.size-i-1 != i)
        {
            println(value[value.size-i-1])
        }
    }
}

fun display(value:ArrayList<Int>){
    println("for array list")
    val len=Math.ceil(value.size/2.0-1).toInt()
    for(i in 0..len){
        println(value[i])
        if(value.size-i-1 != i)
        {
            println(value[value.size-i-1])
        }
    }
}
