package com.example.conduit.service

/**
 * @author masker
 * @date 2021/12/9
 */
interface RelationService {
    fun exist(from: Long, to: Long, type: Int): Boolean
    fun addRelation(from: Long, to: Long, type: Int)
    fun deleteRelation(from: Long, to: Long, type: Int)
}