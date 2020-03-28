package controlflow.equality

fun main() {
    val authors = setOf("Shakespeare", "Hemingway", "Twain")
    val writers = setOf("Twain", "Shakespeare", "Hemingway")

    // java에서 equals 역할이 kotlin에서는 ==로 비교
    // 내용 값이 일치하는지 비교
    println(authors == writers) // true

    // java에서 == 역할이 kotlin에서는 ===로 비교
    // 참조하는 주소 값 비교
    println(authors === writers) // false
}