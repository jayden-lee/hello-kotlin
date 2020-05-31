package controlflow.range

fun main() {
    for (i in 0..3) {
        print("$i ")
    }

    println()

    for (i in 2..8 step 2) {
        print("$i ")
    }

    println()

    for (c in 'a'..'d') {
        print("$c ")
    }

    println()

    val x = 2
    if (x in 1..5) {
        println("x is in range from 1 to 5")
    }
}