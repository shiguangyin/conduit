package com.conduit.app.service

/**
 * @author masker
 * @date 2021/12/9
 */
interface RelationService {
    fun exist(from: Long, to: Long, type: Int): Boolean
    fun addRelation(from: Long, to: Long, type: Int)
    fun deleteRelation(from: Long, to: Long, type: Int)
    fun getRelationCount(from: Long,type: Int): Long
    fun getRelationReverseCount(to: Long, type: Int): Long
}