// exam02.kt
package week03

fun main() {
    var count : Int = 85

    // if문 판단
    if (count >= 90) {
        println("if문: 합격 (장학생)")
    } else if (count >= 60) {
        println("if문: 합격")
    } else {
        println("if문: 불합격")
    }

    // when문 판단
    val jumsu: Int = (count / 10) * 10
    when (jumsu) {
        100, 90 -> println("when문: 합격(장학생)")
        80, 70, 60 -> println("when문: 합격")
        else -> println("when문: 불합격")
    }

    // 1차원 배열
    val one = Array(4) { 0 }
    one[0] = 10
    one[3] = 20

    // 2차원 배열
    val four = Array(3) { IntArray(3) }
    four[0][0] = 10
    four[0][1] = 20
    four[1][0] = 30
    four[1][1] = 40
    println(four[0][0])            // 첫 번째 요소 출력
    println(four.size)             // 배열의 크기 출력
    println(four.indices)          // 배열 인덱스 범위 출력
    println(four[0][0])            // 첫 번째 요소 다시 출력
    println(four.contentDeepToString())  // 다차원 배열을 문자열로 출력

    // IntArray 사용
    val three: IntArray = intArrayOf(1, 2, 3)

    // ArrayList 사용
    val al = ArrayList<Int>()
    al.add(10)
    al.add(20)
    val nap = al[0] + al[1] // 30

    // 새로운 배열 정의
    val one2: IntArray = intArrayOf(10, 20, 30)

    // 첨자 i로 출력
    for (i in one2.indices) {
        println(one2[i])
    }

    // 첨자 없이 출력
    for (value in one2) {
        println(value)
    }
}
