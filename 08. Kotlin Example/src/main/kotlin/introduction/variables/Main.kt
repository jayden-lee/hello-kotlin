package introduction.variables

import kotlin.random.Random

fun main() {
    // 가변변수 선언 및 초기화
    var a: String = "initial"
    println(a)

    // 불변 변수 선언 및 초기화
    val b: Int = 1

    // 명시적인 타입이 없더라도 컴파일이 Int로 추론
    val c = 3

    // 가변변수 선언하고 초기화하지 않고 사용하면 컴파일러는 에러를 발생시킴
    // "Variable 'e' must be initialized"
    var e: Int
    // println(e)

    // 조건에 따라 변수의 값을 초기화 할 수 있음
    // 실제 변수를 사용하기전에만 초기화가 선행되어지면 에러가 발생하지 않음
    val d: Int
    if (Random.nextInt(100) / 2 == 0) {
        d  = 2
    } else {
        d = 1
    }
    println(d)
}