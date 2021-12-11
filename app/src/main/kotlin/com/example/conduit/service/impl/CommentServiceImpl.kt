package com.example.conduit.service.impl

import com.example.conduit.model.Comment
import com.example.conduit.param.AddCommentParams
import com.example.conduit.repository.CommentRepository
import com.example.conduit.service.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author masker
 * @date 2021/12/11
 */
@Service
class CommentServiceImpl @Autowired constructor(
    private val repository: CommentRepository
) : CommentService {

    override fun getCommentsByObject(objectId: Long, objectType: Int): Iterable<Comment> {
        return repository.findByObjectIdAndObjectType(objectId, objectType)
    }

    override fun addComment(objectId: Long, objectType: Int, params: AddCommentParams, author: Long): Comment {
        val comment = Comment(
            params.body,
            objectId,
            objectType,
            author
        )
        repository.save(comment)
        return comment
    }

    override fun deleteById(id: Long) {
        repository.deleteById(id)
    }

    override fun findCommentById(id: Long): Comment? {
        return repository.findById(id).orElse(null)
    }

}