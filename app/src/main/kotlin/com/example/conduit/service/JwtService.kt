package com.example.conduit.service

/**
 * @author masker
 * @date 2021/12/5
 */
interface JwtService {
    fun generateToken(subject: String): String
    fun getSubjectFromToken(token: String):String?
}