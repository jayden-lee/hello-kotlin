package com.jayden.kotlin.querydsl.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "teams")
@Table(name = "teams")
@EntityListeners(AuditingEntityListener::class)
class Team {

    constructor(name: String) {
        this.name = name
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column
    var name: String? = ""

    @OneToMany(mappedBy = "team")
    var members: List<Member> = emptyList()

    @CreatedDate
    @Column
    var createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column
    var updatedAt: LocalDateTime = LocalDateTime.now()

}