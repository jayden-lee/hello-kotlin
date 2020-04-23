package com.jayden.app.domain

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepoisotry : JpaRepository<User, Long> {
}