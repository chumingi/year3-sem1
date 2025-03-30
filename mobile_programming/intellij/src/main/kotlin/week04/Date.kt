// Date.kt
package week04

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun main() {
    var now = Date()
    var sFormat : SimpleDateFormat

    sFormat = SimpleDateFormat("yyyyMMdd")
    println(sFormat.format(now))

    sFormat = SimpleDateFormat("HH:mm:ss")
    println(sFormat.format(now))
}