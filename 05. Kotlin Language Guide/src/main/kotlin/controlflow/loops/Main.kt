package controlflow.loops

fun main() {
    // for
    val cakes = listOf<String>("carrot", "cheese", "chocolate")
    cakes.forEach {
        println("Yummy, it's a $it cake!")
    }
    /*for (cake in cakes) {
        println("Yummy, it's a $it cake!")
    }*/

    // while
    var cakesEaten = 0
    while (cakesEaten < 5) {
        eatACake()
        cakesEaten++
    }

    // do-while
    var cakesBaked = 0
    do {
        bakeACake()
        cakesBaked++
    } while(cakesBaked < cakesEaten)

}

fun eatACake() = println("Eat a Cake")

fun bakeACake() = println("Bake a Cake")