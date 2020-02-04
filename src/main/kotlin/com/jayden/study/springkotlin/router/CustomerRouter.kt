package com.jayden.study.springkotlin.router

import com.jayden.study.springkotlin.router.handler.CustomerHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

/**
 * 리액트 서비스가 응답하는 경로와 메서드 처리
 */
@Component
class CustomerRouter(private val customerHandler: CustomerHandler) {

    @Bean
    fun customerRoutes() = router {
        "/functional".nest {
            "/customer".nest {
                GET("/{id}", customerHandler::get)
                POST("/", customerHandler::create)
                DELETE("/{id}", customerHandler::delete)
            }
            "/customers".nest {
                GET("/", customerHandler::search)
            }
        }
    }
}