package com.ssgclone.demo.utils

import com.ssgclone.demo.domain.Users
import com.ssgclone.demo.dto.RequestSaveUser
import com.ssgclone.demo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
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
                dto.password == dto.password_check
            )
                result = false
        } else {
            result = false
        }

        return result
    }
}