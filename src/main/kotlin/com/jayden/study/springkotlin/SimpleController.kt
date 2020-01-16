package com.jayden.study.springkotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleController(val simpleService: SimpleService) {

    @GetMapping("/hello/{name}")
    fun hello(@PathVariable name: String) = simpleService.getHello(name)
}