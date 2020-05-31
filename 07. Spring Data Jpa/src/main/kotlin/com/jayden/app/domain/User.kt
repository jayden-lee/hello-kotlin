package com.jayden.app.domain

import com.jayden.app.config.DateTimeConverter
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id: Long? = null,

    var username: String? = null,

    @Convert(converter = DateTimeConverter::class)
    var kst_withdraw_at: LocalDateTime? = null,

    var created_at: LocalDateTime? = null,

    var updated_at: LocalDateTime? = null
)