package com.conduit.app.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

/**
 * @author masker
 * @date 2021/12/5
 */
@Configuration
@EnableWebSecurity
class WebSecurityConfig @Autowired constructor(
    private val filter: JwtRequestFilter
) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        // enable cors and disable csrf to avoid 403
        http.cors().and().csrf().disable()
        http.exceptionHandling().authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
        http.authorizeRequests()
            .antMatchers("/api/users", "/api/users/login")
            .permitAll()
            .antMatchers(HttpMethod.GET, "/api/profiles/**")
            .permitAll()
            .antMatchers(HttpMethod.GET, "/api/articles/**")
            .permitAll()
            .antMatchers(HttpMethod.GET, "/api/tags")
            .permitAll()
            .anyRequest()
            .authenticated()
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter::class.java)
    }


    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods =
            listOf("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH")
        configuration.allowCredentials = false
        configuration.allowedHeaders = listOf("Authorization", "Cache-Control", "Content-Type")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

}