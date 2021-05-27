package test_classes


//class inheriting from abstract class
    class Rectangle(val a:Double , val b:Double):Shape("Rectangle") {
    init {
        println("${this.name} created with a=$a and b=$b")
    }

    constructor(a:Double):this(a,a)

    constructor(a:Int,b:Int):this(a.toDouble(),b.toDouble())

    override fun area(): Double = a*b

    override fun perimeter(): Double = 2*a + 2*b

    fun isSquare():Boolean = a==b
}

