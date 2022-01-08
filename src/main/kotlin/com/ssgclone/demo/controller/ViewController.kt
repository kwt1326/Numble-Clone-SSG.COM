package com.ssgclone.demo.controller

import com.ssgclone.demo.dto.RequestSaveUser
import com.ssgclone.demo.dto.ResponseProducts
import com.ssgclone.demo.enums.DeliveryType
import com.ssgclone.demo.enums.ManageCompany
import com.ssgclone.demo.service.ProductService
import com.ssgclone.demo.utils.UserUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import kotlin.jvm.Throws


@Controller
@RequestMapping("/")
class ViewController(
    @Autowired private val userUtils: UserUtils,
    @Autowired private val productService: ProductService
) {
    @Throws(Exception::class)
    @GetMapping(value = ["/", "/mall", "/search_result"])
    fun landingPage(
        @RequestParam(required = false) page: Int?,
        @RequestParam(required = false) delivery: DeliveryType?,
        @RequestParam(required = false) company: ManageCompany?,
        @RequestParam(required = false) search: String?,
        @RequestParam(required = false) check: List<String>?,

        @ModelAttribute requestDto: RequestSaveUser,
        model: Model
    ): String {
        println(check)
        val result: ResponseProducts = productService.getList(page, delivery, company, search, check)

        model.addAttribute("data", result)

        return "landing"
    }

    @GetMapping("/login")
    fun loginPage(model: Model): String {
        return if (userUtils.isLogging()) "redirect:/" else "login"
    }

    @GetMapping("/signup")
    fun signUpPage(model: Model): String {
        model.addAttribute("requestDto", RequestSaveUser())
        return "signup"
    }
}