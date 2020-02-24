package com.jayden.kotlin.querydsl.repository

import com.jayden.kotlin.querydsl.entity.Member
import com.jayden.kotlin.querydsl.entity.QMember.member
import com.jayden.kotlin.querydsl.entity.QTeam.team
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

interface MemberRepository : JpaRepository<Member, Long>, CustomMemberRepository

interface CustomMemberRepository {
    fun searchMember(teamName: String?): List<Member>
}

class CustomMemberRepositoryImpl : CustomMemberRepository, QuerydslRepositorySupport(Member::class.java) {

    override fun searchMember(teamName: String?): List<Member> =
        from(member)
            .join(member.team, team)
            .fetchJoin()
            .where(eqTeamName(teamName))
            .fetch()

    private fun eqTeamName(teamName: String?): BooleanExpression? {
        return if (teamName == null) {
            null
        } else {
            team.name.eq(teamName)
        }
    }
}