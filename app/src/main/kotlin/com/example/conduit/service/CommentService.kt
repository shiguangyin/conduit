package com.example.conduit.service

import com.example.conduit.model.Comment
import com.example.conduit.param.AddCommentParams

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