//// exam07.kt
//package week04
//
//class Car {
//    var color: String = ""
//    var speed: Int = 0
//    companion object {
//        var carCount : Int = 0
//        const val MAXSPEED : Int = 200
//        const val MINSPEED : Int = 0
//        fun currentCarCount() : Int {
//            return carCount
//        }
//    }
//
//    constructor(color: String, speed: Int) {
//        this.color = color
//        this.speed = speed
//        carCount ++
//    }
//
//    constructor(speed: Int) {
//        this.speed = speed
//    }
//
//    constructor () {}
//
//    fun upSpeed(value: Int) {
//        if (speed + value >= 200) {
//            speed = 200
//        } else {
//            speed += value
//        }
//    }
//
//    fun downSpeed(value: Int) {
//        if (speed - value <= 0) {
//            speed = 0
//        } else {
//            speed -= value
//        }
//    }
//}
//
//fun main() {
//    var myCar1 = Car("빨강", 0)
//    var myCar2 = Car("파랑", 0)
//    var myCar3 = Car("초록", 0)
//
//    println("생산된 차의 대수(정적 필드) ==>  " + Car.carCount)
//    println("생성된 차의 대수(정적 메소드) ==> " + Car.currentCarCount())
//    println("차의 최고 제한 속도 ==> " + Car.MAXSPEED)
//
//    println("PI의 값 ==> " + Math.PI)
//    println("3의 5제곱 ==> " + Math.pow(3.0, 5.0))
//}
