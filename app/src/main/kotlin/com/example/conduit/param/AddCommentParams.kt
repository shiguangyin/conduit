package com.example.conduit.param

import com.fasterxml.jackson.annotation.JsonRootName

/**
 * @author masker
 * @date 2021/12/11
 */
@JsonRootName("comment")
data class AddCommentParams(
    val body: String,
)