package com.conduit.app.service

import com.conduit.app.model.Comment
import com.conduit.app.param.AddCommentParams

/**
 * @author masker
 * @date 2021/12/11
 */
interface CommentService {
    fun getCommentsByObject(objectId: Long, objectType: Int): Iterable<Comment>
    fun addComment(objectId: Long, objectType: Int, params: AddCommentParams, author: Long): Comment
    fun deleteById(id: Long)
    fun findCommentById(id: Long): Comment?
}