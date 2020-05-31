package com.jayden.app

import com.jayden.app.domain.User
import com.jayden.app.domain.UserRepoisotry
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.LocalDateTime
import java.util.stream.IntStream

@SpringBootApplication
class AppApplication {

    @Bean
    fun runner(userRepository: UserRepoisotry): CommandLineRunner {
        return CommandLineRunner {
            IntStream.rangeClosed(1, 200).forEach {
                User().apply {
                    this.username = "User $it"
                    this.kst_withdraw_at = LocalDateTime.now()
                    this.created_at = LocalDateTime.now()
                    this.updated_at = LocalDateTime.now()
                }.let {
                    userRepository.save(it)
                }
                Thread.sleep(50)
            }
        }
    }
}

fun main(args: Array<String>) {
    // application timezone utc
    System.setProperty("user.timezone", "UTC");
    runApplication<AppApplication>(*args)
}
