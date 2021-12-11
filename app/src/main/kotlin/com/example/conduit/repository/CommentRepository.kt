package com.example.conduit.repository

import com.example.conduit.model.Comment
import org.springframework.data.repository.CrudRepository

/**
 * @author masker
 * @date 2021/12/11
 */
interface CommentRepository : CrudRepository<Comment, Long> {
    fun findByObjectIdAndObjectType(objectId: Long, objectType: Int): Iterable<Comment>
}