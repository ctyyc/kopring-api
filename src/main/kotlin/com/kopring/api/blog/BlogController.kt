package com.kopring.api.blog

import com.kopring.api.common.entity.Wordcount
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
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
//    @Operation(summary = "Blog 게시글 목록 조회", description = "Blog 게시글 목록을 조회합니다.")
//    @GetMapping("")
//    fun search(@Valid blogDto: BlogDto): ResponseEntity<String>? {
//        val result: String? = blogService.searchKakao(blogDto)
//
//        return ResponseEntity.ok(result)
//    }

    @Operation(summary = "Blog 게시글 목록 조회", description = "Blog 게시글 목록을 조회합니다.")
    @GetMapping("")
    fun search(query: String?, sort: String?, page: Int?, size: Int?): ResponseEntity<String>? {
        if(query == null) {
            return ResponseEntity.badRequest().body("query not allow empty")
        }
        val result: String? = blogService.searchKakao(query, sort, page, size)

        return ResponseEntity.ok(result)
    }

    @Operation(summary = "검색어 기록 조회", description = "검색어 기록을 조회합니다.")
    @GetMapping("/rank")
    fun searchWordRank(): List<Wordcount> = blogService.searchWordRank()
}