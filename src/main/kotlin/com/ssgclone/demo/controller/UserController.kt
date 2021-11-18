package com.ssgclone.demo.controller

import com.ssgclone.demo.domain.Users
import com.ssgclone.demo.dto.RequestSaveUser
import com.ssgclone.demo.service.UserService
import com.ssgclone.demo.utils.UserUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("auth")
class UserController(
    @Autowired private val userService: UserService,
    @Autowired private val userUtil: UserUtils,
) {

    @GetMapping("/duplicate")
    fun duplicateCheck(@ModelAttribute requestDto: RequestSaveUser, bindingResult: BindingResult, rattr: RedirectAttributes): String {
        println(requestDto.username)
        rattr.addFlashAttribute(
            "isDuplicate",
            userService.findByUsername(requestDto.username) != null
        )
        rattr.addFlashAttribute("requestDto", requestDto)
        return "redirect:/signup"
    }

    @PostMapping("/create")
    fun create(@ModelAttribute requestDto: RequestSaveUser, bindingResult: BindingResult, model: Model): String {
        if (userUtil.validateRegisterUser(requestDto)) {
            userService.saveUser(requestDto)

            val realname: String? = userService.findByUsername(requestDto.username)?.realname

            if (realname != null) model.addAttribute("realname", realname)

            return if (realname != null) "success_apply" else "signup"
        }

        return "signup"
    }
}