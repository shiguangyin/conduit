package com.conduit.app.service.impl

import com.conduit.app.model.Article
import com.conduit.app.model.toSlug
import com.conduit.app.param.CreateArticleParams
import com.conduit.app.param.UpdateArticleParams
import com.conduit.app.repository.ArticleRepository
import com.conduit.app.service.ArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service

/**
 * @author masker
 * @date 2021/12/8
 */
@Service
class ArticleServiceImpl @Autowired constructor(
    private val articleRepository: ArticleRepository,
) : ArticleService {
    override fun createArticle(author: Long, params: CreateArticleParams): Article {
        val article = Article(
            params.title,
            params.description,
            params.body,
            author,
        )
        try {
            articleRepository.save(article)
        } catch (e: DataIntegrityViolationException) {
            article.rebuildSlug()
            articleRepository.save(article)
        }
        return article
    }

    @Cacheable(value = ["article"], key = "#slug")
    override fun findArticleBySlug(slug: String): Article? {
        return articleRepository.findBySlug(slug)
    }

    override fun updateArticle(id: Long, params: UpdateArticleParams): Article? {
        val article = articleRepository.findById(id).orElse(null) ?: return null
        params.title?.let {
            article.title = it
            article.slug = toSlug(it)
        }
        params.body?.let { article.body = it }
        params.description?.let { article.description = it }
        articleRepository.save(article)
        return article
    }

    override fun deleteArticle(id: Long) {
        articleRepository.deleteById(id)
    }
}