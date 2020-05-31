package com.jayden.app.config

import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = false)
class DateTimeConverter : AttributeConverter<LocalDateTime, Timestamp> {

    override fun convertToDatabaseColumn(attribute: LocalDateTime?): Timestamp? {
        val localDateTime = attribute?.atZone(ZoneId.of("UTC"))?.withZoneSameInstant(ZoneId.of("Asia/Seoul"))?.toLocalDateTime()
        return Timestamp.valueOf(localDateTime ?: return null)
    }

    override fun convertToEntityAttribute(sqlTimestamp: Timestamp?): LocalDateTime? {
        return sqlTimestamp?.toLocalDateTime() ?: null
    }
}