package com.wtw.demo

import com.wtw.demo.comparator.DefaultComparator
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans


@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    val application = SpringApplication(DemoApplication::class.java)
    application.addInitializers(ApplicationContextInitializer<GenericApplicationContext> { ctx ->
        beans {
            bean {
                bean<DefaultComparator>()
            }
        }.initialize(ctx)


    })
    application.run(*args)
}
