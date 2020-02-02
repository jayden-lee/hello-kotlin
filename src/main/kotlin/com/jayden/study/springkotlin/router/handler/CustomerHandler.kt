package com.jayden.study.springkotlin.router.handler

import com.jayden.study.springkotlin.dto.Customer
import com.jayden.study.springkotlin.error.ErrorResponse
import com.jayden.study.springkotlin.service.CustomerService
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.bodyToMono
import java.net.URI

/**
 *  요청을 응답으로 변환하는 로직 수행
 */
@Component
class CustomerHandler(val customerService: CustomerService) {

    fun get(serverRequest: ServerRequest) =
            customerService.getCustomer(serverRequest.pathVariable("id").toInt())
                    .flatMap { ok().body(fromObject(it)) }
                    .switchIfEmpty(status(NOT_FOUND).build())

    fun search(serverRequest: ServerRequest) =
            ok().body(customerService.searchCustomers(serverRequest.queryParam("nameFilter").orElse("")), Customer::class.java)

    fun create(serverRequest: ServerRequest) =
            customerService.createCustomer(serverRequest.bodyToMono()).flatMap {
                created(URI.create("/functional/customer/${it.id}")).build()
            }.onErrorResume(Exception::class.java) {
                badRequest().body(fromObject(ErrorResponse("error creating customer", it.message ?: "error")))
            }
}