package com.jayden.kotlin.querydsl.service

import com.jayden.kotlin.querydsl.repository.TeamRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TeamService(
    private val teamRepository: TeamRepository
) {

    fun getTeamById(id: Long) {
        teamRepository.findByIdOrNull(id)
    }
}