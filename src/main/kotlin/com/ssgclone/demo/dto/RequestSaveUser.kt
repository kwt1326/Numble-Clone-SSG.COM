package com.ssgclone.demo.dto

data class RequestSaveUser(
    val username: String = "",
    val realname: String = "",
    val password: String = "",
    val password_check: String = "",
    val email: String = "",
    val address: String = "",
    val phoneNumber: String = "",
)