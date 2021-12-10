package com.example.conduit

import com.example.conduit.dto.ArticleDTO
import com.example.conduit.dto.UserDTO
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import java.time.Duration
import javax.annotation.Resource

@SpringBootTest
class ConduitApplicationTests {


    @Resource
    private lateinit var redisTemplate: RedisTemplate<String, ArticleDTO>

    @Test
    fun testRedisSerialize() {
        val userDTO = UserDTO(
            "xxx",
            "yyy",
            "zzz",
            "000"
        )
        val articleDTO = ArticleDTO(
            "slug",
            "title",
            "desc",
            "body",
            "create",
            "update",
            userDTO,
            false,
            0
        )
        redisTemplate.opsForValue().set("test", articleDTO, Duration.ofSeconds(3))
        val ret = redisTemplate.opsForValue().get("test")
        println(ret)
    }

}
