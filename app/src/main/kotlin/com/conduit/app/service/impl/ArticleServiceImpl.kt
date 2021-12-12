package com.conduit.app.service.impl

import com.example.conduit.exception.ResourceNotFoundException
import com.example.conduit.model.Article
import com.example.conduit.repository.ArticleRepository
import com.example.conduit.service.ArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

/**
 * @author masker
 * @date 2021/12/8
 */
@Service
class ArticleServiceImpl @Autowired constructor(
    private val articleRepository: ArticleRepository,
) : ArticleService {

    @Cacheable(value = ["article"], key = "#slug")
    override fun findArticleBySlug(slug: String): Article {
        return articleRepository.findBySlug(slug) ?: throw ResourceNotFoundException()
    }
}