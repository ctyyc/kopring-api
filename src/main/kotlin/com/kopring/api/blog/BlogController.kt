package com.kopring.api.blog

import com.kopring.api.common.entity.Wordcount
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Blog", description = "the Blog API")
@RequestMapping("/api/v1/blog")
@RestController
class BlogController(
        val blogService: BlogService
) {
    @Operation(summary = "Blog 게시글 목록을 조회합니다.", description = "Blog 게시글 목록을 조회합니다.")
    @GetMapping("")
    fun search(@Parameter(description = "blog parameter", required = true) @RequestBody @Valid blogDto: BlogDto): String? {
        val result: String? = blogService.searchKakao(blogDto)
        return result
    }

    @Operation(summary = "검색어 기록을 조회합니다.", description = "검색어 기록을 조회합니다.")
    @GetMapping("/rank")
    fun searchWordRank(): List<Wordcount> = blogService.searchWordRank()
}