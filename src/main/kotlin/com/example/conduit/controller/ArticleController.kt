package com.example.conduit.controller

import com.example.conduit.constants.RelationType
import com.example.conduit.dto.ArticleDTO
import com.example.conduit.dto.CommentDTO
import com.example.conduit.dto.ProfileDTO
import com.example.conduit.exception.InvalidAuthenticationException
import com.example.conduit.exception.ResourceNotFoundException
import com.example.conduit.extension.format
import com.example.conduit.model.Article
import com.example.conduit.model.User
import com.example.conduit.model.toSlug
import com.example.conduit.param.CreateArticleParams
import com.example.conduit.param.ListArticlesParams
import com.example.conduit.param.UpdateArticleParams
import com.example.conduit.repository.ArticleRepository
import com.example.conduit.service.ArticleService
import com.example.conduit.service.RelationService
import com.example.conduit.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import javax.persistence.criteria.Predicate

/**
 * @author masker
 * @date 2021/12/5
 */
@RestController
@RequestMapping("/api/articles")
class ArticleController @Autowired constructor(
    private val articleRepository: ArticleRepository,
    private val articleService: ArticleService,
    private val relationService: RelationService,
    private val userService: UserService,
) {

    private val logger = LoggerFactory.getLogger(ArticleController::class.java)

    @PostMapping("")
    fun createArticle(
        @AuthenticationPrincipal user: User,
        @RequestBody params: CreateArticleParams
    ): ResponseEntity<Any> {
        val article = Article(
            params.title,
            params.description,
            params.body,
            author = user.id,
        )
        try {
            articleRepository.save(article)
        } catch (e: DataIntegrityViolationException) {
            article.rebuildSlug()
            articleRepository.save(article)
        }
        val dto = ArticleDTO.build(article, user)
        return ResponseEntity.ok(mapOf("article" to dto))
    }

    @GetMapping("/{slug}")
    fun getArticleBySlug(@PathVariable("slug") slug: String): ResponseEntity<Any> {
        val article = articleService.findArticleBySlug(slug)
        val user = userService.findUserById(article.author) ?: throw ResourceNotFoundException()
        val dto = ArticleDTO.build(article, user, false, 0)
        return ResponseEntity.ok(mapOf("article" to dto))
    }

    @GetMapping("")
    fun listArticles(params: ListArticlesParams): ResponseEntity<Any> {
        logger.info("params : $params")
        val userId = params.author?.let { authorName ->
            val author = userService.findUserByUsername(authorName) ?: throw ResourceNotFoundException()
            author.id
        }
        val spec = Specification<Article> { root, query, builder ->
            val predicts = mutableListOf<Predicate>()
            userId?.let {
                val author = root.get<Long>("author")
                predicts.add(builder.equal(author, it))
            }
            builder.and(*predicts.toTypedArray())
        }
        val pageable = PageRequest.of(params.offset, params.limit)
        val articleResult = articleRepository.findAll(spec, pageable)
        val dto = articleResult.content.map { article ->
            val user = userService.findUserById(article.author) ?: throw ResourceNotFoundException()
            ArticleDTO.build(article, user)
        }
        return ResponseEntity.ok(mapOf("articles" to dto))
    }

    @GetMapping("/feed")
    fun getArticleFeed(
        @RequestParam("limit", required = false, defaultValue = "10") limit: Int,
        @RequestParam("offset", required = false, defaultValue = "0") offset: Int
    ): ResponseEntity<Any> {
        logger.info("limit : $limit, offset : $offset")
        // TODO fix offset
        val pageable = PageRequest.of(offset, limit)
        val articleResult = articleRepository.findAll(pageable)
        val dto = articleResult.content.map { article ->
            val user = userService.findUserById(article.author) ?: throw ResourceNotFoundException()
            ArticleDTO.build(article, user)
        }
        return ResponseEntity.ok(mapOf("articles" to dto))
    }

    @PutMapping("/{slug}")
    fun updateArticle(
        @PathVariable("slug") slug: String,
        @RequestParam params: UpdateArticleParams,
        @AuthenticationPrincipal user: User
    ): ResponseEntity<Any> {
        val article = articleService.findArticleBySlug(slug) ?: throw ResourceNotFoundException()
        if (user.id != article.author) {
            throw InvalidAuthenticationException()
        }
        params.title?.let {
            article.title = it
            article.slug = toSlug(it)
        }
        params.body?.let { article.body = it }
        params.description?.let { article.description = it }
        articleRepository.save(article)
        val dto = ArticleDTO.build(article, user)
        return ResponseEntity.ok(mapOf("article" to dto))
    }

    @DeleteMapping("/{slug}")
    fun deleteArticle(
        @PathVariable("slug") slug: String,
        @AuthenticationPrincipal user: User
    ): ResponseEntity<Any> {
        val article = articleService.findArticleBySlug(slug) ?: throw ResourceNotFoundException()
        if (user.id != article.author) {
            throw InvalidAuthenticationException()
        }
        articleRepository.delete(article)
        return ResponseEntity.ok(null)
    }

    @GetMapping("/{slug}/comments")
    fun getArticleComments(@PathVariable("slug") slug: String): ResponseEntity<Any> {
        val article = articleService.findArticleBySlug(slug) ?: throw ResourceNotFoundException()
        val author = userService.findUserById(article.author) ?: throw ResourceNotFoundException()
        val comment = CommentDTO(
            0,
            article.createdAt.format(),
            article.updatedAt.format(),
            article.body,
            ProfileDTO(author.username, author.bio, false, author.image)
        )
        val dto = listOf(comment)
        return ResponseEntity.ok(mapOf("comments" to dto))
    }

    @PostMapping("/{slug}/favorite")
    fun favoriteArticle(
        @PathVariable("slug") slug: String,
        @AuthenticationPrincipal user: User
    ): ResponseEntity<Any> {
        val article = articleService.findArticleBySlug(slug) ?: throw ResourceNotFoundException()
        val author = userService.findUserById(article.author) ?: throw ResourceNotFoundException()
        val favorited = relationService.exist(user.id, article.id, RelationType.ArticleFavorite)
        if (!favorited) {
            relationService.addRelation(user.id, article.id, RelationType.ArticleFavorite)
        }
        val dto = ArticleDTO.build(article, author, true, 1)
        return ResponseEntity.ok(mapOf("article" to dto))
    }

    @DeleteMapping("/{slug}/favorite")
    fun unFavoriteArticle(
        @PathVariable("slug") slug: String,
        @AuthenticationPrincipal user: User
    ): ResponseEntity<Any> {
        val article = articleService.findArticleBySlug(slug) ?: throw ResourceNotFoundException()
        val author = userService.findUserById(article.author) ?: throw ResourceNotFoundException()
        val rows = relationService.deleteRelation(user.id, article.author, RelationType.ArticleFavorite)
        logger.info("unFavorite delete rows : $rows")
        val dto = ArticleDTO.build(article, author, false, 0)
        return ResponseEntity.ok(mapOf("article" to dto))
    }

}