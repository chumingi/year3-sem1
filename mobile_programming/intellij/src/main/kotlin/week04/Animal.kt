//// Animal.kt
//package week04
//
//abstract class Animal {
//    var name : String = ""
//    abstract fun move()
//}
//
//class Tiger : Animal() {
//    val age : Int = 0
//    override fun move() {
//        println("네 발로 이동한다.")
//    }
//}
//
//class Eagle : Animal() {
//    var home : String = ""
//    override fun move() {
//        println("날개로 날아간다.")
//    }
//}
//
//fun main() {
//    var animal : Animal
//
//    animal = Tiger()
//    animal.move()
//
//    animal = Eagle()
//    animal.move()
//}