package com.jayden.kotlin.querydsl.controller.dto

import com.jayden.kotlin.querydsl.entity.Member
import com.jayden.kotlin.querydsl.entity.Team

data class MemberDto(private val member: Member) {

    val memberId: Long? = member?.id
    val name: String? = member?.name
    val email: String? = member?.email
    val team: TeamDto? = TeamDto(member?.team)

    data class TeamDto(private val team: Team?) {
        val teamId = team?.id
        val teamName = team?.name
    }

}