package test_classes



fun main() {
    val p = Program1()
//    val myLambda:(Int)->Unit={s:Int->println(s)}
//    p.addNum(2,3,myLambda)      //1
//    p.addNum(2,3,{s:Int->println(s)})   //2
//    p.addNum(2,3) { s: Int -> println(s) }    //3(only when lambda expression is the last parameter)
    p.addNum(2,3) { s: Int -> println(s) }

//    p.reverseString("hello") { s: String -> s.reversed()}
    p.reverseString("hello") { it.reversed()}
}

class Program1{
    fun addNum(a:Int,b:Int,action:(Int)->Unit){    //high level function with lambda as a parameter
        val sum=a+b
        action(sum)    //println(s)
    }

    fun reverseString(string_value:String,action:(String)->String){
        var reversed=action(string_value)
        print(reversed)
    }
}



//fun main() {
//    var p=Person()
////    p.name="Amal"
////    p.age=19
//
////    with(p){
////        name="Amal"
////        age=19
////    }
////
////    p.startRun()
//    p.apply {
//        name="Amal"
//        age=19 }.startRun()
//
//}
//
//
//class Person {
//    var age=-1
//    var name=""
//
//    fun startRun(){
//        println("$name is running")
//    }
//
//}