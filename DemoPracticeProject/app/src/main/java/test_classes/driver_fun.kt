package test_classes

fun main() {
//    val r=Rectangle(10,5)
////
////    println(r.a)
////    println(r.b)
////    println(r.isSquare())
////    println(r.area())
////    println(r.perimeter())
////    println(r.name)
//    val c=Circle(5.0)
//    val c1=Circle(10.0)
//    println("max area = ${maxArea(r,c,c1)}")



    val randomCircleob=Circle.createRandomCircle()




}

fun maxArea(shape1:Shape,shape2:Shape):Double{
    val area1=shape1.area()
    val area2=shape2.area()
    return if(area1>area2) area1 else area2
}

fun maxArea(shape1:Shape,shape2:Shape,shape3:Shape):Double {
    val maxarea12=maxArea(shape1,shape2)
    return if(maxarea12>shape3.area()) maxarea12 else shape3.area()
}

