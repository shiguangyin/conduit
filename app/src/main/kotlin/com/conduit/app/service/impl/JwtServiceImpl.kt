package com.conduit.app.service.impl

import com.conduit.app.service.JwtService
import com.conduit.pb_gen.service.user.AuthenticateRequest
import com.conduit.pb_gen.service.user.UserServiceGrpc
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.util.*


/**
 * @author masker
 * @date 2021/12/5
 */
@Service
class JwtServiceImpl : JwtService {

    private val key = Keys.hmacShaKeyFor(hash("conduit"))

    @GrpcClient("user_service")
    private lateinit var stub: UserServiceGrpc.UserServiceBlockingStub

    override fun generateToken(subject: String): String {
        val expiration = Date(System.currentTimeMillis() + 3600 * 24 * 1000)
        return Jwts.builder()
            .setSubject(subject)
            .setExpiration(expiration)
            .signWith(key).compact()
    }

    override fun getSubjectFromToken(token: String): String? {
        stub.authenticate(AuthenticateRequest.newBuilder().setToken(token).build())
        return try {
            val jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            jws.body.subject
        } catch (e: Exception) {
            null
        }
    }

    private fun hash(data: String): ByteArray {
        val md: MessageDigest = MessageDigest.getInstance("SHA-256")
        md.update(data.toByteArray())
        return md.digest()
    }

}