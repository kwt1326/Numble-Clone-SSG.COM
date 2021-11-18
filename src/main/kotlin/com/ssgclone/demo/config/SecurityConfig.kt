package com.ssgclone.demo.config

import com.ssgclone.demo.override.CustomAuthenticatioFailureHandler
import com.ssgclone.demo.override.CustomAuthenticationSuccessHandler
import com.ssgclone.demo.service.UserService
import com.ssgclone.demo.config.CommonConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(
    @Autowired private val userService: UserService,
    @Autowired private val passwordEncoder: PasswordEncoder,
): WebSecurityConfigurerAdapter() {

    companion object {
        val PERMIT_ALL_PATH = arrayOf("/", "/login", "/auth/**", "/signup", "/static/**", "/favicon.ico")
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .userDetailsService(userService)
            .passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        http
            .formLogin().loginPage("/login")
            .successHandler(CustomAuthenticationSuccessHandler())
            .failureHandler(CustomAuthenticatioFailureHandler())
            .and()
            .logout().logoutUrl("/logout")
            .and()
            .authorizeRequests()
            .antMatchers(*PERMIT_ALL_PATH).permitAll()
            .anyRequest().authenticated()
    }
}