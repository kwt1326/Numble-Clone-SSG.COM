package com.ssgclone.demo.controller

import com.ssgclone.demo.dto.RequestSaveUser
import com.ssgclone.demo.service.UserService
import com.ssgclone.demo.utils.UserUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("auth")
class UserController(
    @Autowired private val userService: UserService,
    @Autowired private val userUtil: UserUtils,
) {

    @GetMapping("/duplicate")
    fun duplicateCheck(@ModelAttribute requestDto: RequestSaveUser, bindingResult: BindingResult, rattr: RedirectAttributes): String {
        val existUser: Boolean = userService.findByUsername(requestDto.username) != null
        val result = mutableMapOf(
            "isDuplicate" to existUser,
            "isFailedCreate" to false,
            "dto" to requestDto,
        )

        rattr.addFlashAttribute("data", result)
        return "redirect:/signup"
    }

    @PostMapping("/create")
    fun create(@ModelAttribute requestDto: RequestSaveUser, bindingResult: BindingResult, rattr: RedirectAttributes, model: Model): String {
        if (userUtil.validateRegisterUser(requestDto)) {
            userService.saveUser(requestDto)

            val realname: String? = userService.findByUsername(requestDto.username)?.realname

            if (realname != null) model.addAttribute("realname", realname)

            return if (realname != null) "success_apply" else "signup"
        }

        val existUser: Boolean = userService.findByUsername(requestDto.username) != null
        val result = mutableMapOf(
            "isDuplicate" to existUser,
            "isFailedCreate" to true,
            "dto" to requestDto
        )

        rattr.addFlashAttribute("data", result)

        return "redirect:/signup"
    }
}