package com.jayden.beanvalidation.entity

import javax.persistence.*

@Table
@Entity
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long ?= null,

    @Column(nullable = false)
    var name: String ?= null,

    @Column(nullable = false)
    var email: String ?= null,

    @Column(nullable = false)
    var password: String ?= null
)