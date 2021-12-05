package com.example.conduit

import com.example.conduit.dto.ArticleDTO
import com.example.conduit.dto.UserDTO
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ConduitApplicationTests {

    @Test
    fun testDTO2Json() {
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
            userDTO
        )
        val mapper = ObjectMapper().registerKotlinModule()
        val str = mapper.writeValueAsString(articleDTO)
        println(str)
        val dto2 = mapper.readValue(str, ArticleDTO::class.java)
        println(dto2)
    }

}
