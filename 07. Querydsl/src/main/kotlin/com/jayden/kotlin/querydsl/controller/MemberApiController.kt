package com.jayden.kotlin.querydsl.controller

import com.jayden.kotlin.querydsl.controller.dto.CreateMemberDto
import com.jayden.kotlin.querydsl.controller.dto.MemberDto
import com.jayden.kotlin.querydsl.service.MemberService
import com.jayden.kotlin.querydsl.service.TeamService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.streams.toList

@RestController
@RequestMapping("/api/v1/members")
class MemberApiController @Autowired constructor(
    private val memberService: MemberService,
    private val teamService: TeamService
) {

    @GetMapping("/{id}")
    fun getMember(@PathVariable id: Long): ResponseEntity<Any> {
        val member = memberService.getMember(id)
        val status = if (member != null) HttpStatus.OK else HttpStatus.NOT_FOUND
        return ResponseEntity(member?.let { MemberDto(it) }, status)
    }

    @PostMapping
    fun createMember(@RequestBody request: CreateMemberDto) =
        ResponseEntity(memberService.createMember(request)?.let { MemberDto(it) }, HttpStatus.CREATED)

    @DeleteMapping("/{id}")
    fun deleteMember(@PathVariable id: Long): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (memberService.getMember(id) != null) {
            memberService.deleteMember(id)
            status = HttpStatus.OK
        }
        return ResponseEntity(Unit, status)
    }

    @GetMapping
    fun searchMember(@RequestParam(required = false) teamName: String?): ResponseEntity<Any> {
        val members = memberService.searchMember(teamName)
            .stream()
            .map { MemberDto(it) }
            .toList()
        return ResponseEntity(members, HttpStatus.OK)
    }

}