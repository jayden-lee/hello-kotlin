package introduction.functions

fun main() {
    // Default Parameter Values and Named Arguments
    println("<Default Parameter Values and Named Arguments>")
    printMessageWithPrefix("Hello Message")
    printMessageWithPrefix("Hello Message", "Log")

    println()

    // Infix Functions
    println("<Infix Functions>")
    val pair = "Ferrari" to "Katrina"
    println("[pair] ${pair.first} , ${pair.second}")

    val sophia = Person("Sophia")
    val claudia = Person("Claudia")
    sophia likes claudia
    println("[sophia.likedPeople] ${sophia.likedPeople.size}")

    println()

    // Operator Functions
    println("<Operator Functions>")
    println(2 * "Good Bye Rainist ")

    println()

    // Functions with vararg Parameters
    println("<Functions with vararg Parameters>")
    printAll("Hello", "Hallo", "Salut", "Hola", "你好", "안녕")
}

fun printMessageWithPrefix(message: String, prefix: String = "Info") {
    println("[$prefix] $message")
}

class Person(val name: String) {
    val likedPeople = mutableListOf<Person>()

    infix fun likes(other: Person) {
        likedPeople.add(other)
    }
}

operator fun Int.times(str: String) = str.repeat(this)

fun printAll(vararg messages: String) {
    messages.forEach {
        println(it)
    }
}