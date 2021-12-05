package com.example.conduit.security

import com.example.conduit.repository.UserRepository
import com.example.conduit.service.JwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author masker
 * @date 2021/12/5
 */
@Component
class JwtRequestFilter @Autowired constructor(
    private val jwtService: JwtService,
    private val userRepository: UserRepository,
): OncePerRequestFilter() {

    companion object {
        const val headerName = "Authorization"
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        getTokenFromHeader(request)?.let { token ->
            val sub = jwtService.getSubjectFromToken(token)
            if (sub?.isNotEmpty() == true) {
                userRepository.findById(sub.toLong()).ifPresent{ user ->
                    logger.info("parsed user : $user")
                    val token = UsernamePasswordAuthenticationToken(user, null, emptyList())
                    //token.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = token
                }

            }
        }

        filterChain.doFilter(request, response)
    }

    private fun getTokenFromHeader(request: HttpServletRequest): String?{
        val header = request.getHeader(headerName)
        if (header.isNullOrEmpty()) {
            return null
        }
        val splits = header.split(" ")
        if (splits.size < 2) {
            return null
        }
        return splits[1]
    }
}