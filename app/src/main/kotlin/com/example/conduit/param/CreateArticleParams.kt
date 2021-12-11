package com.example.conduit.param

import com.fasterxml.jackson.annotation.JsonRootName

/**
 * @author masker
 * @date 2021/12/5
 */
@JsonRootName("article")
data class CreateArticleParams(
    val title: String,
    val description: String,
    val body: String,
    // TODO tagList
)