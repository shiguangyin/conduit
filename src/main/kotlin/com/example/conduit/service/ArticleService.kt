package com.example.conduit.service

import com.example.conduit.dto.ArticleDTO

/**
 * @author masker
 * @date 2021/12/8
 */
interface ArticleService {

    fun findArticleBySlug(slug: String): ArticleDTO

}