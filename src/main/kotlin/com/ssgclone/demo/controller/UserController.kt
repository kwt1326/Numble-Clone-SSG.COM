package com.ssgclone.demo.controller

import com.ssgclone.demo.repository.IUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("auth")
class UserController(
    @Autowired private val userRepository: IUserRepository
) {

    @GetMapping("/ping")
    fun ping(): String {
        println("pong")
        return "pong"
    }

    @PostMapping("create")
    fun create(

    ): String {

        return "test"
    }
}