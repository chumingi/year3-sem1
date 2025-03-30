// exam06.kt
package week03

class Car {
    var color: String = ""
    var speed: Int = 0

    constructor(color: String, speed: Int) {
        this.color = color
        this.speed = speed
    }

    constructor(speed: Int) {
        this.speed = speed
    }

    constructor() {}

    fun upSpeed(value: Int) {
        if (speed + value >= 200) {
            speed = 200
        } else {
            speed += value
        }
    }

    fun downSpeed(value: Int) {
        if (speed - value <= 0) {
            speed = 0
        } else {
            speed -= value
        }
    }
}

fun main() {
    var myCar1 = Car("빨강", 0)
    var myCar2 = Car("파랑", 0)
    var myCar3 = Car("초록", 0)

    myCar1.upSpeed(50)
    println("자동차1의 색: ${myCar1.color}, 속도: ${myCar1.speed}km")

    myCar2.upSpeed(25)
    println("자동차2의 색: ${myCar2.color}, 속도: ${myCar2.speed}km")

    myCar3.upSpeed(250)
    println("자동차3의 색: ${myCar3.color}, 속도: ${myCar3.speed}km")
}
