package extension.datetime

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun LocalDate.toEpochMilli() = this.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()

fun LocalDate.format(pattern: String = "yyyyMMdd"): String {
    return this.format(DateTimeFormatter.ofPattern(pattern))
}

fun Long.convertEpochMilliToLocalDate(zoneId: ZoneId = ZoneId.systemDefault()) = Instant.ofEpochMilli(this).toLocalDate(zoneId)

fun Instant.toLocalDate(zoneId: ZoneId): LocalDate {
    val rules = zoneId.rules
    val offset = rules.getOffset(this)
    val localSecond = this.epochSecond + offset.totalSeconds.toLong()
    val localEpochDay = Math.floorDiv(localSecond, 86400)
    return LocalDate.ofEpochDay(localEpochDay)
}

fun LocalDateTime.toEpochMilli() = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
