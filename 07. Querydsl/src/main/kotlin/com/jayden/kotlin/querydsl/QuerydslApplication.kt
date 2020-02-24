package com.jayden.kotlin.querydsl

import com.jayden.kotlin.querydsl.entity.Member
import com.jayden.kotlin.querydsl.entity.Team
import com.jayden.kotlin.querydsl.repository.MemberRepository
import com.jayden.kotlin.querydsl.repository.TeamRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class QuerydslApplication {

    @Bean
    fun init(memberRepository: MemberRepository, teamRepository: TeamRepository) = CommandLineRunner {
        val teamA = teamRepository.save(Team("Team A"))
        val teamB = teamRepository.save(Team("Team B"))

        memberRepository.save(Member("jayden", "jayden@test.com", teamA))
        memberRepository.save(Member("ella", "ella@test.com", teamA))
        memberRepository.save(Member("kelly", "kelly@test.com", teamA))
        memberRepository.save(Member("robb", "robb@test.com", teamB))
        memberRepository.save(Member("issac", "issac@test.com", teamB))
        memberRepository.save(Member("brant", "brant@test.com", teamB))
        memberRepository.save(Member("andrew", "andrew@test.com", teamB))
    }
}

fun main(args: Array<String>) {
    runApplication<QuerydslApplication>(*args)
}
