package com.conduit.app.dto

import com.conduit.app.model.User

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
