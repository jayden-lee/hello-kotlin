package com.jayden.beanvalidation.entity.repository

import com.jayden.beanvalidation.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
}