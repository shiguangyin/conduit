package com.example.conduit.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*

/**
 * @author masker
 * @date 2021/12/5
 */
@Configuration
@EnableWebSecurity
class WebSecurityConfig @Autowired constructor(
    private val filter: JwtFilter
): WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        // disable csrf to avoid 403
        http.csrf().disable()
        http.authorizeRequests()
                .antMatchers("/api/users", "/api/users/login")
                .permitAll()
                .anyRequest()
                .authenticated()
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter::class.java)
    }


}