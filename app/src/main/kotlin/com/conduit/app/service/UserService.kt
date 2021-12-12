package com.conduit.app.service

import com.conduit.app.model.User

/**
 * @author masker
 * @date 2021/12/10
 */
interface UserService {
    fun findUserById(userId: Long): User?
    fun findUserByUsername(username: String): User?
    fun findUserByEmail(email: String): User?
}