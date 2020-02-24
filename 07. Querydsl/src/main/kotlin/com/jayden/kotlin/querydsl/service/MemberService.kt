package com.jayden.kotlin.querydsl.service

import com.jayden.kotlin.querydsl.controller.dto.CreateMemberDto
import com.jayden.kotlin.querydsl.entity.Member
import com.jayden.kotlin.querydsl.repository.MemberRepository
import com.jayden.kotlin.querydsl.repository.TeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MemberService @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val teamRepository: TeamRepository
) {

    fun getMember(id: Long): Member? {
        return memberRepository.findByIdOrNull(id!!);
    }

    fun searchMember(teamName: String?): List<Member> {
        return memberRepository.searchMember(teamName)
    }

    fun createMember(request: CreateMemberDto): Member {
        val team = teamRepository.findById(request.teamId)
            .orElseThrow { throw IllegalArgumentException("Not Found Team") }

        val member = Member(request.name, request.email, team)

        return memberRepository.save(member)
    }

    fun deleteMember(id: Long) =
        memberRepository.deleteById(id)

}