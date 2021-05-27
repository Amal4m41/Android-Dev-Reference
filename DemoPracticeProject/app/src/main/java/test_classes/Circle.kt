package test_classes

import kotlin.random.Random

class Circle(val radius:Double=0.0):Shape("Circle") {


    companion object{
        private const val pi=3.14

        fun createRandomCircle():Circle{    //returns an object of Circle
            var radius:Double= Random.nextDouble(1.0,10.0)   //Random itself is like a object companion(singleton class)
            return Circle(radius)
        }
    }


    init {
        println("this is a $name of radius $radius")
        println("area = ${area()}")
        println("perimeter = ${perimeter()}")
    }


    override fun area(): Double = pi*radius*radius
    override fun perimeter(): Double = 2*pi*radius
}