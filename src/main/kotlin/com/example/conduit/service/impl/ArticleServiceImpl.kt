package com.example.conduit.service.impl

import com.example.conduit.dto.ArticleDTO
import com.example.conduit.exception.ResourceNotFoundException
import com.example.conduit.repository.ArticleRepository
import com.example.conduit.repository.UserRepository
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
    private val userRepository: UserRepository,
) : ArticleService {

    @Cacheable(value = ["article"], key = "#slug")
    override fun findArticleBySlug(slug: String): ArticleDTO {
        val article = articleRepository.findBySlug(slug) ?: throw ResourceNotFoundException()
        val user = userRepository.findById(article.author).orElseThrow { ResourceNotFoundException() }
        return ArticleDTO.build(article, user)
    }
}