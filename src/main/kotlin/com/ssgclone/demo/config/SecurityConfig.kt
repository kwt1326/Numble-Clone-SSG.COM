package com.ssgclone.demo.config

import com.ssgclone.demo.override.CustomAuthenticatioFailureHandler
import com.ssgclone.demo.override.CustomAuthenticationSuccessHandler
import com.ssgclone.demo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
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
        val PERMIT_ALL_PATH = arrayOf(
            "/", "/auth/**", "/signup", "/login", "/mall", "/search_result",
            "/static/**", "/favicon.ico"
        )
    }

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
            "/v2/api-docs", "/configuration/ui",
            "/swagger-resources", "/configuration/security",
            "/swagger-ui.html", "/webjars/**", "/swagger/**",
            "/resources/**", "/static/**", "/css/**", "/js/**"
        )
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .userDetailsService(userService)
            .passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        http
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/auth/login")
            .successHandler(CustomAuthenticationSuccessHandler())
            .failureHandler(CustomAuthenticatioFailureHandler())
            .and()
            .logout()
            .logoutUrl("/auth/logout")
            .logoutSuccessUrl("/")
            .and()
            .authorizeRequests()
            .antMatchers("/auth/create/success").hasAnyRole()
            .antMatchers(*PERMIT_ALL_PATH).permitAll()
            .anyRequest().authenticated()
    }
}