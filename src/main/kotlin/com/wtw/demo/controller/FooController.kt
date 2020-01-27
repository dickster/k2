package com.wtw.demo.controller

import com.wtw.demo.comparator.DefaultComparator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
class FooController @Autowired constructor(private val comparator: DefaultComparator) {

    @GetMapping("/foo")
    fun blog(model: Model): String {
        println( "!!!!!!!!!!!!!!!foo com.wtw.demo.controller called ")
        comparator.compare(3,55)
        return "blog"
    }


}
