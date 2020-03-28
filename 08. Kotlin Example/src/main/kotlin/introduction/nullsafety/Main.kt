package introduction.nullsafety

fun main() {
    // null이 될 수 없는 변수에 null을 대입하면 컴파일 에러가 발생함
    var neverNull: String = "This can't be null"
    // neverNull = null

    // nullable 변수는 타입의 마지막에 ? 를 붙인다
    var nullable: String? = "You can keep a null here"
    nullable = null

    val a: String = "a"
    val b: String? = null

    println("value : $a , describe : ${describeString(a)}")
    println("value : $b , describe : ${describeString(b)}")
}

fun describeString(maybeString: String?): String {
    return if (maybeString != null && maybeString.isNotEmpty()) {
        "String of length ${maybeString.length}"
    } else {
        "Empty or null string"
    }
}