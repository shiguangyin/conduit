package com.example.conduit.service

import com.example.conduit.model.User

/**
 * @author masker
 * @date 2021/12/5
 */
interface JwtService {
    fun generateToken(user: User): String
    fun getSubjectFromToken(token: String):String?
}