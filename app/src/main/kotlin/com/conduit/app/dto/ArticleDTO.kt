package com.conduit.app.dto

import com.conduit.app.extension.format
import com.conduit.app.model.Article
import com.conduit.app.model.User

/**
 * @author masker
 * @date 2021/12/5
 */
data class ArticleDTO(
    val slug: String,
    val title: String,
    val description: String,
    val body: String,
    val createdAt: String,
    val updatedAt: String,
    val author: UserDTO,
    val favorited: Boolean,
    val favoritesCount: Long,
    val tagList: List<String> = arrayListOf() // TODO
) {
    companion object {
        fun build(article: Article, user: User, favorited: Boolean = false, favoritesCount: Long = 0): ArticleDTO {
            return ArticleDTO(
                article.slug,
                article.title,
                article.description,
                article.body,
                article.createdAt.format(),
                article.updatedAt.format(),
                UserDTO.fromUser(user),
                favorited,
                favoritesCount,
            )
        }
    }
}
