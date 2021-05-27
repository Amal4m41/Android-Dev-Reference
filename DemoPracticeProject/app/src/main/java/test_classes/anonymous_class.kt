package test_classes

fun main() {
    val a=1.0
    val b=2.0
    val height=2.0
    val parallelogram=object : Shape("parallelogram",a,b,height){
        init {
            println("this is anonymous class")
            println("value of a=$a, b=$b and height=$height")
            println("area = ${area()}")
            println("perimeter = ${perimeter()}")
        }

        override fun area(): Double {
            return a*b
        }

        override fun perimeter(): Double {
            return 2*a + 2*b
        }

        fun isRectange():Boolean{
            return height==b
        }
    }

    println(parallelogram.isRectange())
//    println(parallelogram.perimeter())
//    println(parallelogram.area())
//    println(parallelogram.name)



    val programmer=object : Human,Animal{
        override fun thinklikeAnimal() {
            println("thinking like animal")
        }

        override var height: Double=9.9

        override fun think() {
            println("thinking like humans")
        }
    }
    programmer.think()
    programmer.thinklikeAnimal()
    println(programmer.height)



    //anonymous class with no super type
    val helloworld=object {
        val hello="Hello"
        // object expressions extend Any, so `override` is required on `toString()`
        override fun toString(): String = "$hello world"

        fun dummy(){
            println("dummy function")
        }
    }

    println(helloworld)
    helloworld.dummy()

}

interface Human{
    fun think()
}

interface Animal{
    var height:Double
    fun thinklikeAnimal()
}