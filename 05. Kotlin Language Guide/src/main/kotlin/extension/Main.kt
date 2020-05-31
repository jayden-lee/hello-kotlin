package extension

import extension.datetime.convertEpochMilliToLocalDate
import extension.datetime.format

fun main(): Unit {
    val birthDayMs: Long = 210729600000

    // 1976-09-05
    println(birthDayMs.convertEpochMilliToLocalDate())

    // 19760905
    println(birthDayMs.convertEpochMilliToLocalDate().format())
}