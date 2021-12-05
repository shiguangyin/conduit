package com.example.conduit.repository

import com.example.conduit.model.User
import org.springframework.data.repository.CrudRepository

/**
 * @author masker
 * @date 2021/12/5
 */
interface UserRepository: CrudRepository<User,Long> {

    fun findUserByEmail(email: String): User?

    fun findUserByUsername(username: String): User?
}