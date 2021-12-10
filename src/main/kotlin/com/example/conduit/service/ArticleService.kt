package com.example.conduit.service

import com.example.conduit.model.Article

/**
 * @author masker
 * @date 2021/12/8
 */
interface ArticleService {

    fun findArticleBySlug(slug: String): Article

}