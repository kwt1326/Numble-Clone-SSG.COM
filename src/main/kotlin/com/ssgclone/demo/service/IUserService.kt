package com.ssgclone.demo.service

import com.ssgclone.demo.domain.Users
import com.ssgclone.demo.dto.RequestSaveUser
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

interface IUserService: UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    fun saveUser(dto: RequestSaveUser): Users

    fun findByUsername(username: String): Users?
}