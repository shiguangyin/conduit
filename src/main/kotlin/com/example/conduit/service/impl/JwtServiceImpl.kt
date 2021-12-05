package com.example.conduit.service.impl

import com.example.conduit.model.User
import com.example.conduit.service.JwtService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*

/**
 * @author masker
 * @date 2021/12/5
 */
@Service
class JwtServiceImpl: JwtService {

    private val key = Keys.hmacShaKeyFor("helloworldhelloworldhelloworldhelloworld".toByteArray())

    override fun generateToken(user: User): String {
        val expiration = Date(System.currentTimeMillis() + 3600 * 24 * 1000)
        return Jwts.builder()
            .setSubject(user.id.toString())
            .setExpiration(expiration)
            .signWith(key).compact()
    }

    override fun getSubjectFromToken(token: String): String? {
        val jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
        return jws.body.subject
    }

}