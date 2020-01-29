package com.jayden.study.springkotlin.service

import org.springframework.stereotype.Service

@Service
class SimpleService {

    fun getHello(name: String) = "hello $name"
}