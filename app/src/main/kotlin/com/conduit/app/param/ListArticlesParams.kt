package com.conduit.app.param

/**
 * @author masker
 * @date 2021/12/6
 */
data class ListArticlesParams (
    val tag: String? = null,
    val author: String? = null,
    val favorited: String? = null,
    val limit: Int = 20,
    val offset: Int = 0,
)