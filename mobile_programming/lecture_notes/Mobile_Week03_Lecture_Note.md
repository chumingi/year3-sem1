# 3주차

## Kotlin

- JAVA와 100% 호환된다
- 자바보다 문법이 간단하며, getter와 setter 없이도 값을 가져오고 설정할 수 있다.
- null을 엄격하게 다루어 안정성을 높이며, 오류 방지를 위해 연산자 ?와 !!를 사용한다.
- var이나 val로 데이터의 형식을 선언하지 않고도 변수를 상둉할 수 있다.

```kotlin
// HelloKotlin.kt
fun main() {
	println("Hello World")
}
```

1. 기본 함수 main() 선언
2. 콘솔에 글자 추력. 행 끝에 세미콜론(;) 붙여도 되고 안 붙여도 된다

## Kotlin 기본 문법

### 1. 변수와 데이터 형식

```kotlin
// exam01.kt
package com.office.week03

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
  }

```

1. 정수형 변수 var1에 값 대입

6, 7  실수형 변수 var1에 값 대입. float형 변수에 값을 대입할 때는 뒤에 f를 붙여야 한다.

8, 9  char형은 **문자 1개**를 ‘’로 묶는다. 문자열은 String으로 선언하고 “”로 묶는다.

10~14  println() 메소드를 사용하여 콘솔에 출력한다. 자동으로 줄바꿈된다.

- 암시적 선언 : 대입되는 값에 따라 타입 지정

```kotlin
var var1 = 10
var var2 = 10.1f
var var3 = 10.2
var var4 = '안'
var var5 = "안드로이드"
```

- 변수를 선언할 때 var(variable) 또는 val(value)를 사용한다.
- var : 일반 변수 선언, 필요할 때 재할당 가능
- val : 초기화 없이 선언 시 재할당 한번만 가능

```kotlin
var myVar : Int = 100
myVar = 200
println(myVar) // 200

val myVal : Int = 100
// myVar = 200 오류 발생
```

- 형변환 - toInt(), toDouble() 등

```kotlin
var a : Int = "100".toInt()
var b : Double = "100.123".toDouble()
```

- 변수에 null를 대입하려면 데이터 형식 뒤에 ?를 붙여야 한다.
- 변수에 null 값이 들어갈 수 없게 하려면 !!를 사용한다.

```kotlin
// var notNull : Int = null 오류
var okNull : Int? = null

var ary = ArrayList<Int>(1)
ary!!.add(100)
```

### 2. 조건문 if, when

```kotlin
if (조건식1) {
	// 조건1이 true이면 실행
} else if {
	// 조건1이 false이고 조건2가 true면 실행
} else {
	// 조건1, 조건2 모두 false면 실행
```

```kotlin
when (식) {
	값1 -> // 값1이면 실행
	값2 -> // 값2이면 실행
	else -> // 해당하는 값이 없으면 실행
```

- when 문으로 다중분기(=switch)

```kotlin
// exam02.kt
package week03

fun main() {
    var count : Int = 85
    if (count >= 90) {
        println("if문: 합격 (장학생)")
    } else if (count >= 60) {
        println("if문: 합격")
    } else {
        println("불합격")
    }

    var jumsu : Int = (count / 10) * 10
    when (count) {
        100 -> println("when문: 합격(장학생)")
        90 -> println("when문: 합격(장학생)")
        80, 70, 60 -> println("when문: 합격")
        else -> println("when문: 불합격")
    }
}
```

- count(85)는 else if의 조건에 해당하므로 "if문: 합격"가 출력된다.
- jumsu는 count에서 1의 자리 수를 없앤 80이므로 "when문: 합격"이 출력된다.

## 3. 배열

- 여러 개의 데이터를 하나의 변수에 저장하기 위해 사용한다.

### 1차원 배열

```kotlin
var 배열명 = Array<데이터 형식>(개수) {초깃값}
var 배열명 = Array<데이터 형식>(개수, {초깃값})
```

```kotlin
var one = Array<Int>(4, {0})
one[0] = 10
one[3] = 20
```

### 2차원 배열

```kotlin
var 배열명 = Array<배열 데이터 형식>{행 개수, {배열 데이터 형식(열 개수)})
var 배열명 = Array<데이터 형식>(개수) {초기값}
```

```kotlin
var four = Array(3) {IntArray(3)}
for[0][0] = 10
for[0][1] = 20
for[1][0] = 30
for[1][1] = 40
println(four[0][0])  // 첫 번째 요소 출력
println(four.size)   // 배열의 크기 출력
println(four.indices)  // 배열의 인덱스 범위 출력 (0..2)
println(four[0][0])  // 첫 번째 요소 다시 출력
println(four.contentToString())  // 배열을 문자열로 출력

```

### 배열을 선언하면서 값 대입

```kotlin
var three : IntArray = intArrayOf(1, 2, 3)
```

### 배열의 크기

- 배열명.size

### ArrayList

```kotlin
var al = ArrayList<Int>(4)
one.add(10)
one.add(20)
var nap = one.get(0) + one.get(1) // 30
```

## 4. 반복문 for, while

### for 반복문

```kotlin
for (변수 in 시작..끝 step 증가량) {
	// 이 부분 반복 실행
}

for (변수 in 배열.indices) {
	이 부분 반복 실행
}
```

- 배열의 모든 값 출력

```kotlin
var one : IntArray = intArrayOf<Int>(10, 20, 30)
    for (i in one.indices) {
        println(one[i])
    }
    
    // 첨자 i 없이 출력
    for (value in one) {
        println(value)
    }
}
```

```kotlin

```

### whle 반복문

- break : 반복문 종류
- continue : continue 아래의 코드 실행 건너뛰고 다시 반복

```kotlin
while (조건식) {
	// 조건식이 true인 동안 실행
}
```

```kotlin
// exam03.kt
public week03

fun main() {
	var one : IntArray = intArrayOf(10, 20, 30)
	for (i in one.indices) {
		println(one[i])
	}
	for (value in one) {
		println(value)
	}
	
	var two : Array<String> = arrayOf("하나", "둘", "셋")
	for (i in 0..2 step 1) {
		println(two[i])
	}
	
	var k : Int = 0
	while (k < two.size) {
		println(two[k])
		k++
	}
}
```

## 4. 메소드와 전역변수, 지역변수

- 파일명과 메인클래스명은 동일해야 함. 클래스 내의 main()을 실행
- main() 외에 사용자가 별도로 메소드 선언 가능
- 전역변수 : 모든 메소드에서 사용 가능. 초기화하지 않아도 됨
- 지역변수 : 메소드 내에서만 사용 가능. 초기화가 필요함

```kotlin
// exam04.kt
package week03

var myVar : Int = 100
fun main() {
	var myVar : Int = 0
	println(myVar)
	
	var sum : Int = addFunction(10, 20)
	println(sum)
}

fun addFunction(num1 : Int, num2 : Int) : Int {
	var hap : Int
	hap = num1 + num2
	return hap
}
```

1. 전역변수 myVar에 100 대입

6, 7  지역변수 myVar에 0 대입 출력값은 0

- 두 정수의 합을 더하여 반환하는 함수를 만들고 main()에서 호출

### 6. 예외 처리 try=catch

- 예외 : 프로그램 실행 중 발생하는 오류
- 프로그램 실행 중 사용자가 발생시킬 수 잇는 오류를 미리 대비한다.

```kotlin
try {
	// 정상 실행
} catch (예외) {
	// 예외 발생 시 실행
} finally {
	예외 여부와 상관없이 반드시 실행
}
```

```kotlin
// exam05.kt
package week03

fun main() {
    var num1 : Int = 100
    var num2 : Int = 0

    try {
        println(num1/num2)
    } catch (e : ArithmeticException) {
        println("계산에 문제가 있습니다.")
    }
}
```

### 7. 연산자

| 연산자 | 설명 |
| --- | --- |
| `+`, `-`, `*`, `/`, `%` | 사칙 연산자로 `%`는 나머지 값을 계산한다. |
| `+`, `-` | 부호 연산자로 변수, 수, 식 앞에 붙일 수 있다. |
| `=` | 대입 연산자로 오른쪽을 왼쪽에 대입한다. |
| `++`, `--` | 1씩 증가 또는 감소시킨다. |
| `==`, `===`, `!=`, `!==`, `<`, `>`, `<=`, `>=` | 비교 연산자로 결과는 `true` 또는 `false`이며, `if`문이나 반복문의 조건식에 사용된다. |
| `&&`, ||, ! | 논리 연산자로, and, or, not을 나타낸다. |
| `and`, `or`, `xor`, `inv()` | 비트 연산자로 비트 단위로 and, or, exclusive or, not 연산을 한다. |
| `shr`, `shl` | 시프트 연산자로 비트를 오른쪽 또는 왼쪽으로 이동한다. |
| `+=`, `-=`, `*=`, `/=` | 복합 대입 연산자로 `a += b`는 `a = a + b`와 동일하다. |
| `toByte()`, `toShort()`, `toInt()`, `toLong()toFloat()`, `toDouble()`, `toChar()` | 데이터 형식을 강제로 변환하는 함수. 예: `var a: Int = (3.14).toInt()`는 실수 3.14를 정수형 3으로 변환한다. |