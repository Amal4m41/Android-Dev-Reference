package test_classes

fun main() {
    val p=Program()
//    p.addTwoNumbers(2,3)
//    val ob_a=a()
//    p.addTwoNumbers(5,7,ob_a)

    p.addTwoNumbers(5,3,object : Myinterface{
        override fun execute(val1: Int, val2: Int): Int {
            return val1+val2
        }
    })
}

class a:Myinterface{
    override fun execute(val1: Int, val2: Int): Int {
        return val1+val2
    }
}

class Program {
    fun addTwoNumbers(a:Int,b:Int,){
        print(a+b)
    }

    fun addTwoNumbers(a:Int,b:Int,action:Myinterface){
        val result=action.execute(a,b)
        print("result using interface = $result")
    }
}

interface Myinterface{
    fun execute(val1:Int,val2:Int):Int
}