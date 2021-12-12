package com.conduit.app.service.impl

import com.example.conduit.model.User
import com.example.conduit.repository.UserRepository
import com.example.conduit.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

/**
 * @author masker
 * @date 2021/12/10
 */
@Service
class UserServiceImpl @Autowired constructor(
    private val userRepository: UserRepository
) : UserService {
    @Cacheable(cacheNames = ["user"], key = "#userId")
    override fun findUserById(userId: Long): User? {
        return userRepository.findById(userId).orElseGet { null }
    }

    override fun findUserByUsername(username: String): User? {
        return userRepository.findUserByUsername(username)
    }

    override fun findUserByEmail(email: String): User? {
        return userRepository.findUserByUsername(email)
    }
}