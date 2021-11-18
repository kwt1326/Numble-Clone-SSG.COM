package com.ssgclone.demo.service

import com.ssgclone.demo.domain.Roles
import com.ssgclone.demo.domain.Users
import com.ssgclone.demo.dto.RequestSaveUser
import com.ssgclone.demo.repository.IUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired private val userRepository: IUserRepository,
    @Autowired private val passwordEncoder: PasswordEncoder
) : IUserService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username)?.getAuthorities()
            ?: throw UsernameNotFoundException("loadUserByUsername - not found username: $username")
    }

    override fun findByUsername(username: String): Users? {
        return userRepository.findByUsername(username)
    }

    @Throws(UsernameNotFoundException::class)
    override fun saveUser(dto: RequestSaveUser): Users {


        userRepository.save(
            Users(
                dto.username,
                dto.realname,
                passwordEncoder.encode(dto.password),
                dto.address,
                dto.email,
                dto.phoneNumber,
                mutableSetOf(Roles.CUSTOMER)
            )
        )

        return userRepository.findByUsername(dto.username)
            ?: throw UsernameNotFoundException("Not found user and Save Failed")
    }
}