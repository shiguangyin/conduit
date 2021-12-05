package com.example.conduit.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * @author masker
 * @date 2021/12/5
 */
@Configuration
@EnableWebSecurity
class WebSecurityConfig: WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        // disable csrf to avoid 403
        http.csrf().disable()
        http.authorizeRequests()
                .antMatchers("/api/users", "/api/users/login")
                .permitAll()
                .anyRequest()
                .authenticated()
    }
}