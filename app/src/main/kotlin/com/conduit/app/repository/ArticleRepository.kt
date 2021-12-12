package com.conduit.app.repository

import com.conduit.app.model.Article
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * @author masker
 * @date 2021/12/5
 */
interface ArticleRepository: PagingAndSortingRepository<Article, Long>, JpaSpecificationExecutor<Article> {
    fun findBySlug(slug: String): Article?
}