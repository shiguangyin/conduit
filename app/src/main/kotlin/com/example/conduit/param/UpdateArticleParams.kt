package com.example.conduit.param

/**
 * @author masker
 * @date 2021/12/6
 */
data class UpdateArticleParams(
    val title: String? = null,
    val description: String? = null,
    val body: String? = null,
)