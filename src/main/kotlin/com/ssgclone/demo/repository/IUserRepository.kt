package com.ssgclone.demo.repository

import com.ssgclone.demo.domain.Users
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository

interface IUserRepository: CrudRepository<Users, Long>, QuerydslPredicateExecutor<Users> {
    fun findByUsername(username: String): Users?
}