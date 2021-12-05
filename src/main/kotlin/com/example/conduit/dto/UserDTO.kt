package com.example.conduit.dto

import com.example.conduit.model.User

/**
 * @author masker
 * @date 2021/12/5
 */
data class UserDTO(
    val email: String,
    val username: String,
    val bio: String,
    val image: String? = null
) {
    companion object {
        fun fromUser(user: User): UserDTO {
            return UserDTO(user.email, user.username, user.bio, user.image)
        }
    }
}
