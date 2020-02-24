package com.jayden.kotlin.querydsl.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "members")
@Table(name = "members")
@EntityListeners(AuditingEntityListener::class)
class Member {

    constructor(name: String, email: String, team: Team) {
        this.name = name
        this.email = email
        this.team = team
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column
    var name: String? = ""

    @Column
    var email: String? = ""

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    var team: Team? = null

    @CreatedDate
    @Column
    var createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column
    var updatedAt: LocalDateTime = LocalDateTime.now()

}