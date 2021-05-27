package test_classes

abstract class Shape(var name: String) {
    init {

        println("I'm the super class!")
    }

    constructor(name:String,vararg dimensions: Double):this(name)

    abstract fun area():Double
    abstract fun perimeter():Double

    fun changeName(newName:String){
        this.name=newName
    }
}