package com.ssgclone.demo.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.ssgclone.demo.domain.ProductDetail
import com.ssgclone.demo.domain.Products
import com.ssgclone.demo.service.ProductService
import com.ssgclone.demo.vo.ProductParseVo
import org.hibernate.mapping.Collection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.util.ResourceUtils
import java.util.*
import java.util.stream.Collectors

@Configuration
class CommonConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}