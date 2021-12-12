package com.conduit.app.dto

/**
 * @author masker
 * @date 2021/12/7
 */
data class CommentDTO(
    val id: Long,
    val createdAt: String,
    val updatedAt: String,
    val body: String,
    val author: ProfileDTO,
)