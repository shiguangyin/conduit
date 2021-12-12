package com.conduit.app.controller

import com.conduit.app.dto.UserDTO
import com.conduit.app.dto.UserWithTokenDTO
import com.conduit.app.exception.InvalidAuthenticationException
import com.conduit.app.model.User
import com.conduit.app.param.LoginParams
import com.conduit.app.param.RegisterParams
import com.conduit.app.param.UpdateUserParams
import com.conduit.app.repository.UserRepository
import com.conduit.app.service.JwtService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

/**
 * @author masker
 * @date 2021/12/5
 */
@RestController
class UserController @Autowired constructor(
    val repo: UserRepository,
    val jwtService: JwtService
) {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @PostMapping("/api/users/login")
    fun login(@RequestBody params: LoginParams): ResponseEntity<Any> {
        logger.info("login params : $params")
        val user = repo.findUserByEmail(params.email) ?: throw InvalidAuthenticationException()
        if (user.password != params.password) {
            throw InvalidAuthenticationException()
        }
        val token = jwtService.generateToken(user.id.toString())
        val dto = UserWithTokenDTO(
            user.email, user.username, user.bio,
            token, user.image
        )
        return ResponseEntity.ok(mapOf("user" to dto))
    }

    @PostMapping("/api/users")
    fun register(@RequestBody params: RegisterParams): ResponseEntity<Any> {
        val user = User(params.username, params.email, params.password)
        repo.save(user)
        val token = jwtService.generateToken(user.id.toString())
        val userDto = UserWithTokenDTO(
            user.email, user.username,
            user.bio, token
        )
        val data = mapOf("user" to userDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(data)
    }

    @GetMapping("/api/user")
    fun getCurrentUser(@AuthenticationPrincipal user: User): ResponseEntity<Any> {
        val userDto = UserDTO(user.email, user.username, user.bio, user.image)
        return ResponseEntity.ok(mapOf("user" to userDto))
    }

    @PutMapping("/api/user")
    fun updateUser(@AuthenticationPrincipal user: User, @RequestBody params: UpdateUserParams): ResponseEntity<Any> {
        params.bio?.apply { user.bio = this }
        user.image = params.image
        repo.save(user)
        val dto = UserDTO(
            user.email, user.username, user.bio,
            user.image
        )
        return ResponseEntity.ok(mapOf("user" to dto))
    }

}