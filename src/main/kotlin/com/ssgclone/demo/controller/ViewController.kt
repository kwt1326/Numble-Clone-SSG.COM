package com.ssgclone.demo.controller

import com.ssgclone.demo.dto.RequestSaveUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/")
class ViewController {
    @GetMapping
    fun landing() = "landing"

    @GetMapping("/signup")
    fun signup(model: Model): String {
        model.addAttribute("requestDto", RequestSaveUser())
        return "signup"
    }
}