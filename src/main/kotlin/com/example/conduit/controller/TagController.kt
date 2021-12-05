package com.example.conduit.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.PermitAll

/**
 * @author masker
 * @date 2021/12/7
 */
@RestController
class TagController {
    @GetMapping("/api/tags")
    @PermitAll
    fun getTags(): ResponseEntity<Any> {
        val tags = listOf("hello", "world")
        return ResponseEntity.ok(mapOf("tags" to tags))
    }
}