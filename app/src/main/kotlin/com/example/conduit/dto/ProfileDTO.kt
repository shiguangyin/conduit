package com.example.conduit.dto

/**
 * @author masker
 * @date 2021/12/5
 */
data class ProfileDTO(
    val username: String,
    val bio: String,
    val following: Boolean = false,
    val image: String? = null,
)