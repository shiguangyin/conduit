package com.conduit.app.service

import com.conduit.app.model.Article
import com.conduit.app.param.CreateArticleParams
import com.conduit.app.param.UpdateArticleParams

/**
 * @author masker
 * @date 2021/12/8
 */
interface ArticleService {

    fun createArticle(author: Long, params: CreateArticleParams): Article

    fun findArticleBySlug(slug: String): Article?

    fun updateArticle(id: Long, params: UpdateArticleParams): Article?

    fun deleteArticle(id: Long)

}