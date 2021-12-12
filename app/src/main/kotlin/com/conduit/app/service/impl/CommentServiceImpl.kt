package com.conduit.app.service.impl

import com.conduit.app.model.Comment
import com.conduit.app.param.AddCommentParams
import com.conduit.app.repository.CommentRepository
import com.conduit.app.service.CommentService
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