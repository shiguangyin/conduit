package com.example.conduit.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author masker
 * @date 2021/12/5
 */
@RestController
class UserController {

    @PostMapping("/api/users/login")
    fun login(): ResponseEntity<Any> {
        return ResponseEntity.ok("login")
    }

    @PostMapping("/api/users")
    fun register(): ResponseEntity<Any> {
        return ResponseEntity.ok("register")
    }

    @GetMapping("/api/user")
    fun getCurrentUser(): ResponseEntity<Any> {
        return ResponseEntity.ok("get user")
    }

    @PutMapping("/api/user")
    fun updateUser(): ResponseEntity<Any> {
        return ResponseEntity.ok("update user")
    }

}