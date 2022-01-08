package com.ssgclone.demo.utils

import com.ssgclone.demo.domain.Users
import com.ssgclone.demo.dto.RequestSaveUser
import com.ssgclone.demo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class UserUtils(
    @Autowired private val userService: UserService
) {
    private val usernameRegex = Regex("/([a-zA-Z]|\\d){6,20}/g")
    private val passwordRegex = Regex("/[a-zA-Z\\d]{8,20}/g")

    fun validateRegisterUser(dto: RequestSaveUser): Boolean {
        val existUser: Users? = userService.findByUsername(dto.username)
        var result: Boolean = true

        if (existUser == null) {
            if (
                usernameRegex.matchEntire(dto.username) != null ||
                passwordRegex.matchEntire(dto.password) != null ||
                dto.password != dto.password_check ||
                dto.address == "" ||
                dto.email == "" ||
                dto.phoneNumber == "" ||
                dto.realname == ""
            )
                result = false
        } else {
            result = false
        }

        return result
    }

    fun isLogging(): Boolean {
        val authentication = SecurityContextHolder.getContext().authentication
        var username = if (authentication.principal is UserDetails) {
            (authentication.principal as UserDetails).username
        } else {
            authentication.principal.toString()
        }

        val users: Users? = userService.findByUsername(username)
        return users != null
    }
}