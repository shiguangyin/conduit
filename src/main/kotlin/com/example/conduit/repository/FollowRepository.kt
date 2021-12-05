package com.example.conduit.repository

import com.example.conduit.model.FollowRelation
import org.springframework.data.repository.CrudRepository
import javax.transaction.Transactional

/**
 * @author masker
 * @date 2021/12/5
 */
interface FollowRepository : CrudRepository<FollowRelation, Long> {
    fun findFollowRelationByUserId(userId: Long): FollowRelation?
    fun findFollowRelationByUserIdAndTargetUser(userId: Long, targetUser: Long): FollowRelation?
    @Transactional
    fun deleteByUserIdAndTargetUser(userId: Long, targetUser: Long): Long
}