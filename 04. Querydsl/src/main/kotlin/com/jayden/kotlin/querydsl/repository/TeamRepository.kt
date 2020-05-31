package com.jayden.kotlin.querydsl.repository

import com.jayden.kotlin.querydsl.entity.Team
import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<Team, Long>