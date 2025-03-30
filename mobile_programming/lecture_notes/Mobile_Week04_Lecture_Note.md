# 4주차

---

# 클래스와 인스턴스

## 1. 클래스 정의와 인스턴스 생성

- 클래스는 변수(필드, 실세계의 상태)와 메소드(실세계의 행동)으로 구성된다.
- 예: 자동차라는 **객체**의 클래스는 자동차를 생성하기 위한 설계또이고, 이 **클래스(설계도)**를 통해 실제로 존재로 존재하는 여러 대의 자동차(**인스턴스**)를 생성할 수 있다.

### 예제3-8 exam06.kt

- 4: Car 클래스를 생성한다.
- 5,6: 자동차의 생상과속도필드를정의한다.
- 8~13: 추가속도(value)를 파라미터로 받아서 현재 속도를 증가시킨다. 추가속도가 200을 넘으면 더 이상 가속되지 않는다.
- 15~20: 추가속도(value)를 파라미터로 받아서 현재 속도를 감소시킨다. 감소된 속도가 0 이하이면 0으로 처리한다.
- 25~35: myCar1, myCar2, myCar3 인스턴스를 생성하고, 색상과 속도를 지정한다.
- 37~44 : 차 3대의 속도를 증가시키고, 색상과 속도를 출력한다.

```kotlin
// exam06.kt
package week03

class Car {
    var color: String = ""
    var speed: Int = 0

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
    var myCar1 = Car()
    myCar1.color = "빨강"
    myCar1.speed = 0
    
    var myCar2 = Car()
    myCar2.color = "파랑"
    myCar2.speed = 0
    
    var myCar3 = Car()
    myCar3.color = "초록"
    myCar3.speed = 0

    myCar1.upSpeed(50)
    println("자동차1의 색: ${myCar1.color}, 속도: ${myCar1.speed}km")

    myCar2.upSpeed(25)
    println("자동차2의 색: ${myCar2.color}, 속도: ${myCar2.speed}km")

    myCar3.upSpeed(250)
    println("자동차3의 색: ${myCar3.color}, 속도: ${myCar3.speed}km")
}
```

## 2. 생성자

- 인스턴스를 생성하고 해야 할 일을 클래스 안에 생성자로 만들어놓는다
- 메소드를 생성하면서 파라미터를 넘기면 데이터가 생성자로 넘어간다.
- 생성자의 이름은 클래스의 이름과 동일하게 하고, 메소드의 데이터 형식은 생략한다.
- 메소드 : 기능적인 부분
- constructor : 설계된 값 사용 가능

### 예제 3-9~10 | exam06.kt 수정

- 8~11: 생성자는 constructor() 메소드를 사용하고, 필요한 경우 파라미터를 지정할 수 있다. 지역변수 color과 speed를 클래스의 전역변수로 지정한다. this는 자기자신의 클래스를 의미한다.
- 31~33: 인스턴스를 생성할 때 값을 넘겨주면, Car 클래스에서 color과 speed를 파라미터로 받아서 초기화한다.

```kotlin
// exam06.kt
package week03

class Car {
    var color: String = ""
    var speed: Int = 0
    
    constructor(color: String, speed: Int) {
	    this.color = color
	    this.speed = speed
	  }

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

```

## 3. 메소드 오버로딩

- 한 클래스 내에서 메소드의 이름이 같아도 파라미터의 개수나 데이터 형식만 다르면 여러개를선언할 수 있다.

### 예제 3-11 | exam06.kt에 메소드 오버로딩 추가

```kotlin
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

```

## 4. 정적필드, 정적메소드, 상수필드

- 정적필드 : 인스턴스 생성하지 않고 클래스 자체에서 사용 - companion object
- 정적 메소드 : companion object로 만듦. 인스턴스를 생성하지 않고 클래스명.메소드명()으로 사용
- 상수 필드 : 정적 필드에 초기값 인력한 이후 값 변경 불가, const val을 사용하여 대문자로 선언

```kotlin
// exam07.kt
package week04

class Car {
    var color: String = ""
    var speed: Int = 0
    companion object {
	    var carCount : Int = 0
	    const val MAXSPEED : Int = 200
	    const val MINSPEED : Int = 0
	    fun currentCarCount() : Int {
		    return carCount
		  }
		}

    constructor(color: String, speed: Int) {
        this.color = color
        this.speed = speed
        carCount ++
    }
    
    constructor(speed: Int) {
	    this.speed = speed
	  }
	  
	  constructor () {}

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

    println("생산된 차의 대수(정적 필드) ==>  " + Car.carCount)
    println("생성된 차의 대수(정적 메소드) ==> " + Car.currentCarCount())
    println("차의 최고 제한 속도 ==> " + Car.MAXSPEED)    

		println("PI의 값 ==> " + Math.PI)
		println("3의 5제곱 ==> " + Math.pow(3.0, 5.0))    
}

```

# 클래스 상속

## 1. 클래스 상속과 메소드 오버라이딩

- 부모 클래스가 가지고 있는 모든 필드와 메소드를 물려받으면서 필요한 필드와 메소드를 추가로 정의하는 것
- 부모는 슈퍼클래스, 자식은 서브클래스
- 오버라이딩(재정의) : open키워드 사용 | 슈퍼 클래스를 무시하고 자식클래스에서 새로 정의하여 사용

### 예제 3-14~15 AutoMobile.kt

- open 키워드로 클래스의 상속을 허용하여 AutoMobile 클래스가 Car 클래스의 필드와 메소드를 상속받는다.
- AutoMobile 클래스에서 seatNum 필드를 추가적으로 정의하고, override 키워드를 사용하여 Car 클래스의 upSpeed 메소드를 재정의한다.

```kotlin
// AutoMobile.kt
package week04

open class Car {
    var color: String = ""
    var speed: Int = 0

    constructor(color: String, speed: Int) {
        this.color = color
        this.speed = speed
        carCount ++
    }
    
    constructor(speed: Int) {
	    this.speed = speed
	  }
	  
	  constructor () {}

    open fun upSpeed(value: Int) {
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

class AutoMobile : Car {
	var seatNum : Int = 0
	
	constructor () {}
	
	fun countSeatNum() : Int {
		return seatNum
	}
	
	override fun upSpeed(value: Int) {
		if (speed+value >= 300) {
			speed = 300
		} else {
			speed = speed + value
		}
	}
}

fun main() {
		var auto : AutoMobile = AutoMobile()
		auto.upSpeed(250)
		println("승용차의 속도는 " + auto.speed + "km입니다.")
}

```

## 2. 추상클래스와 추상메소드

- 추상클래스 : 인스턴스화 금지하는 클래스. 클래스명 앞에 abstract 키워드를 붙여 지정
- 추상 메소드 : 본체가 없는 메소드. 메소드 앞에 abstract 키워드를 붙여 지정
- 목적 : 사용되는 기능을 추상메소드로 선언해놓고, 추상클래스를 상속받은 후에 반드시 오버라이딩을 하기 위함 **추상메소드를 구현한다**

### 예제 3-16 Animal.kt

- 4: Animal 클래스를 추상클래스로 설정한다.
- 6: move() 메소드를 추상메소드로 선언한다.
- 9,16: Animal 클래스를 상속받아서 Tiger과 Eagle 클래스를 만든다.
- 11~13,18~20: Animal 클래스의 추상메소드 move()를 구현한다.

```kotlin
// Animal.kt
package week04

abstract class Animal {
	var name : String = ""
	abstract fun move()
}

class Tiger : Animal() {
	val age : Int = 0
	override fun move() {
		println("네 발로 이동한다.")
	}
}

class Eagle : Animal() {
	var home : String = ""
	override fun move() {
		println("날개로 날아간다.")
	}
}

fun main() {
	var tiger1 = Tiger()
	var eagle1 = Eagle()
	
	tiger1.move()
	eagle1.move()
}
```

## 3. 클래스 변수의 다향성

- 다형성 : 서브클래스에서 생성한 인스턴스를 자신의 클래스변수에 대입할 수 있는 것
- 하나의 변수에 여러 종류의 인스턴스 대입 가능

### 예제 3-17 Animal.kt

- 26,29: Animal 클래스 변수에 서브클래스의 인스턴스 Tiger와 Eagle을 대입한다.

```kotlin
// Animal.kt
package week04

abstract class Animal {
	var name : String = ""
	abstract fun move()
}

class Tiger : Animal() {
	val age : Int = 0
	override fun move() {
		println("네 발로 이동한다.")
	}
}

class Eagle : Animal() {
	var home : String = ""
	override fun move() {
		println("날개로 날아간다.")
	}
}

fun main() {
	var animal : Animal
	
	animal = Tiger()
	animal.move()
	
	animal = Eagle()
	animal.move()
}
```

## 4. 인터페이스의 다중상속

- 인터페이스 : class 키워드 대신 interface 키워드를 사용하여 정의하고, 내부에는 추상메소드를 선언한다.
- kotlin 다중 상속 불가하므로, 인터페이스를 사용하여 비슷하게 사용한다.

### 예제 3-18 exam10.kt

- 9~11: iAnimal 인터페이스를 선언하고 추상메소드 eat()을 선언한다.
- 13: iAnimal 인터페이스를 구현하여 iCat 클래스를 생성하고, 추상메소드를 오버라이딩한다.
- 19: iTiger 클래스를 생성하기 위해 Animal 클래스를 상속받음과 동시에 iAnimal 인터페이스를 구현한다. 즉 다중상속의 효과를 낸다.

```kotlin
// exam10.kt
package week04

abstract class Animal {
	var name : String = ""
	abstract fun move()
}

interface iAnimal {
	abstract fun eat()
}

class iCat : iAnimal {
	override fun eat() {
		println("생선을 좋아한다.")
	}
}

class iTiger : Animal(), iAnimal {
	override fun move() {
		println("네 발로 이동한다.")
	}
	override fun eat() {
		println("멧돼지를 잡아먹는다.")
	}
}

class Eagle : Animal() {
	var home : String = ""
	override fun move() {
		println("날개로 날아간다.")
	}
}

fun main() {
	var cat = iCat()
	cat.eat()
	
	var tiger = iTiger()
	tiger.move()
	tiger.eat()

	var eagle = Eagle()
	eagle.move()
}
```

## 5. 람다식

- 람다식 : 함수를 익명함수 형태로 간단히 표현한 것
- 코드가 간결해지면서 가독성이 높아진다.
- {}로 감싸고, 매개변수와 메소드의 모든 내용을 선언한다.
- fun 없이 →를 사용하고, return을 생략한다.

```kotlin
var addNUmber = {n1: Int, num2 : Int -> n1 + n2)
```

# 추가로 알아둘 것

## 1. 패키지

- 클래스와 인터페이스 많아지면 관리하기 어렵기 때문에 패키지 단위로 관리

```kotlin
package 패키지명
```

## 2. 제네릭스

- 데이터 형식의 안정성을 보장하기 위해 사용한다.
- <> 안에 타입 명시 <String>, <Int>, <Double> 등

```kotlin
var strList = ArrayList<String>(4)
```

## 3. 문자열 비교, 날짜 형식

### 문자열 비교

- String 클래스의 equals() 메소드 사용

```kotlin
var str : String = "안녕하세요"
if str.equals("안녕하세요") {
	// 문자열이 같다면 이곳을 실행
}
```

### 날짜 형식

- 날짜 형식을 표현할 때 DateFormat을 사용하거나, 이를 상속받은 SimpleDateFormatDate를 사용하면 ‘연월일’이나 ‘시분초’와 같은 일반적인 표현이 가능하다.

```kotlin
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
```

---

# 4장 위젯

## 1. 뷰의 개념

- 뷰(view) : 실행하는 구성하는 요소들
- 테스트뷰, 버튼, 라디오버튼, 이미지 등

### 뷰와 뷰그룹

- 안드로이드 화면에서 사용되는 모든 것들은 View 클래스의 상속을 받는다.
- 화면에서는 버튼을 **버튼 위젯**, 실제 코드에서는 **버튼 클래스**라고 부른다.
- 레이아웃(위젯에 포함) : 다른 위젯을 담는 컨테이너 역할. ViewGroup을 상속받는다.

### 계층 구조도

- 최상위 : object
- vew 내에 viewGroup
- linearLayout 주로 사용

```kotlin
- Object  
  └── View  
      ├── ViewGroup  
      │   ├── LinearLayout
      │   │   ├── TableLayout  
      │   │   └── RadioGroup  
      │   ├── RelativeLayout  
      │   ├── FrameLayout  
      │   │   └── TabHost  
      │   ├── GridLayout  
      │   ├── AdapterView  
      │   │   ├── AbsListView  
      │   │   │   ├── ListView  
      │   │   │   ├── GridView  
      │   │   └── AbsSpinner  
      │   │       ├── Gallery  
      │   │       └── Spinner  
      │   └── ToolBar  
      ├── TextView  
      │   ├── EditText  
      │   └── Button  
      │       └── CompoundButton  
      │           ├── CheckBox  
      │           ├── ToggleButton  
      │           ├── Switch  
      │           └── RadioButton  
      ├── ImageView  
      │   └── ImageButton  
      └── ProgressBar  
          └── AbsSeekBar  
              ├── SeekBar
		          └── RatingBar

```

### view클래스 xml 속성

- 모든 위젯과 레이아웃 등은 View 클래스의 속성과 메소드를 상속받는다
- width, heigh 필수
1. id 
    - 모든 위젯의 아이디를 나타내며, Kotlin에서 위젯에 접근할 때 id속성에 저장된 아이디를사용한다.
    - **@+id/** 형식으로 지정하고, / 뒤에 새로 지정할 id를 넣는다.
    - 위젯에 접근 | `위젯 변수 = findViewById<위젯>(R.id.위젯id` 형식 사용
    
    ```kotlin
    var button1 : Button
    button1 = findViewById<Button>(R.id.button1)
    ```
    
    ```kotlin
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        >
    
        <TextView
            android:id="@+id/textView1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="성별 선택"
        />
        <RadioButton
            android:id="@+id/female"
            android:layout_height="wrap_content"
            android:layout_width="wrap_content"
            android:text="여성"
        />
        <RadioButton
            android:id="@+id/male"
            android:layout_height="wrap_content"
            android:layout_width="wrap_content"
            android:text="남성"
        />
    </LinearLayout>
    ```
    
2. layout_width, layout_height
    - 위젯의 너비와 높이를 나타내는 속성으로, match_parent, wrap_content 값으로 설정
    - match_parent : 부모(대개는 레이아웃)에 너비와 높이를 맞춘다는 의미
    - wrap_content : 글자가 꼭 들어갈 정도로만 너비와 높이를 설정한다는 의미
    - 직접 값을 지정할 때, 단위에 유의 px(픽셀), sp, pt, dp(화면 비율)
    
    ```kotlin
    <LinearLayout
    	-- 생략 --
    	<Button
    		android:layout_width="1080px"
    		android:layout_height="1920px"
    		android:text="버튼입니다" />
    </LinearLayout>
    ```
    
3. background 속성
    - 위젯의 색상을 주로 #RRGGBB 값으로 지정.
    - 각 값은 16진수 00~FF로 표현
    
    ```kotlin
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:backgroundTint="ff0000"
        >
        <Button
    		    android:layout_width="wrap_content"
    		    android:layout_height="wrap_content"
    		    android:backgroundTint="#00ff00"
    		    android:text="버튼입니다"
    </LinearLayout>
    ```