package com.jayden.resttemplate.client.config

import com.jayden.resttemplate.client.common.log.Log
import org.apache.http.client.HttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpResponse
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.DefaultResponseErrorHandler
import org.springframework.web.client.RestTemplate
import java.time.Duration


@Configuration
class RestTemplateConfig {

    companion object : Log

    @Bean
    fun minimalHttpClient(): HttpClient {
        return HttpClients.createMinimal()
    }

    @Bean
    fun defaultHttpClient(): HttpClient {
        return HttpClients.createDefault()
    }

    @Bean
    fun customHttpClient(): HttpClient {
        val connectionManager = PoolingHttpClientConnectionManager()
        connectionManager.maxTotal = 300           // 총 커넥션 수
        connectionManager.defaultMaxPerRoute = 50  // 단일 IP:PORT 에 대한 커넥션 수
        return HttpClients.custom().setConnectionManager(connectionManager).build()
    }

    @Bean
    fun restTemplate(): RestTemplate {
        val httpClient = customHttpClient()

        val clientHttpRequestFactory = HttpComponentsClientHttpRequestFactory(httpClient)

        return RestTemplateBuilder()
            .requestFactory {
                clientHttpRequestFactory
            }
            .setConnectTimeout(Duration.ofSeconds(5L))
            .setReadTimeout(Duration.ofSeconds(5L))
            .errorHandler(CustomResponseErrorHandler())
            .build()
    }

    class CustomResponseErrorHandler : DefaultResponseErrorHandler() {

        companion object : Log

        override fun hasError(response: ClientHttpResponse): Boolean {
            return super.hasError(response)
        }

        override fun handleError(response: ClientHttpResponse) {
            return super.handleError(response)
        }
    }
}
