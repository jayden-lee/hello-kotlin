package com.jayden.study.docker.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleApiController {

    @GetMapping("/hello")
    fun hello() = "hello from a Docker"
}