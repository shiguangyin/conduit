package com.example.conduit.dto

/**
 * @author masker
 * @date 2021/12/5
 */
data class UserDTO(
    val email: String,
    val username: String,
    val bio: String,
    val image: String? = null
)