package specialclass.dataclass

// data class를 정의하기 위해서는 data를 붙인다
data class User(val name: String, val id: Int)

fun main() {
    val user = User("Alex", 1)
    // toString 자동 생성되고 값이 포맷팅 되어 편하게 볼 수 있다
    println(user)

    val secondUser = User("Alex", 1)
    val thirdUser = User("Max", 2)

    println("user == secondUser: ${user == secondUser}")
    println("user == thirdUser: ${user == thirdUser}")

    // equals(==) 비교에서 true 이면, 해시코드 값이 같다
    println(user.hashCode())
    println(secondUser.hashCode())
    println(thirdUser.hashCode())

    // copy 함수는 생성자 매개변수와 같은 순서로 인자를 전달받아서 복사한다
    println(user.copy())
    println(user.copy(name = "Max"))
    println(user.copy(id = 2))

    // componentN 함수를 이용해서 프로퍼티 값에 접근이 가능
    println("name = ${user.component1()}")
    println("id = ${user.component2()}")
}