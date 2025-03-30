// exam01.kt
package week03

fun main() {
    var var1 : Int = 10
    var var2 : Float = 10.1f
    var  var3 : Double = 10.2
    var var4 : Char = '안'
    var var5 : String = "안드로이드"
    println(var1)
    println(var2)
    println(var3)
    println(var4)
    println(var5)

    var var6 = 10
    var var7 = 10.1f
    var var8 = 10.2
    var var9 = '안'
    var var10 = "안드로이드"

    var myVar : Int = 100
    myVar = 200
    println(myVar) // 200

    val myVal : Int = 100
    // myVar = 200 오류 발생

    var a : Int = "100".toInt()
    var b : Double = "100.123".toDouble()

    // var notNull : Int = null 오류
    var okNull : Int? = null

    var ary = ArrayList<Int>(1)
    ary!!.add(100)
}