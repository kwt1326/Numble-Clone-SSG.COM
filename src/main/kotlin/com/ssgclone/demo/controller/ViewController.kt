package com.ssgclone.demo.controller

import com.ssgclone.demo.dto.RequestSaveUser
import com.ssgclone.demo.service.UserService
import com.ssgclone.demo.utils.UserUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/")
class ViewController(
    @Autowired private val userUtils: UserUtils
) {
    @GetMapping
    fun landing() = "landing"

    @GetMapping("/login")
    fun login(model: Model): String {
        return if (userUtils.isLogging()) "redirect:/" else "login"
    }

    @GetMapping("/signup")
    fun signup(model: Model): String {
        model.addAttribute("requestDto", RequestSaveUser())
        return "signup"
    }
}