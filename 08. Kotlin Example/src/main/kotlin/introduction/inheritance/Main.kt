package introduction.inheritance

// kotlin 클래스는 기본적으로 final 이기 때문에 상속을 허용하기 위해서는
// 반드시 open 키워드를 추가 한다
open class Dog {

    // kotlin 메서드는 기본적으로 final 이기 때문에 오버라이딩을 허용하려면
    // 반드시 open 키워드를 추가 한다
    open fun sayHello() {
        println("wow wow!")
    }
}

// 특정 클래스를 상속 받기 위해서는 클래스 이름 옆에
// : SuperclassName()을 추가 한다
class Yorkshire : Dog() {
    override fun sayHello() {
        println("wif wif!")
    }
}

open class Tiger(val origin: String) {
    fun sayHello() {
        println("A tiger from $origin says: grrhhh!")
    }
}

class SiberianTiger : Tiger("Siberia")

fun main() {
    val dog: Dog = Yorkshire()
    dog.sayHello()

    val tiger: Tiger = SiberianTiger()
    tiger.sayHello()
}