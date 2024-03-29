package com.kopring.api.kakao

import com.kopring.api.common.entity.WordCount
import com.kopring.api.common.exception.InvalidInputException
import com.kopring.api.kakao.dto.BlogRequestDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Kakao API", description = "the Kakao API")
@RequestMapping("/api/v1/kakao")
@RestController
class KakaoApiController(
        val kakaoApiService: KakaoApiService
) {
    @Operation(summary = "Blog 게시글 목록 조회", description = "Blog 게시글 목록을 조회합니다.")
    @GetMapping("/blog")
    fun searchBlog(@Valid blogDto: BlogRequestDto): ResponseEntity<String>? {
        val result: String? = kakaoApiService.searchBlog(blogDto)

        return ResponseEntity.ok(result)
    }

    @Operation(summary = "검색어 기록 조회", description = "검색어 기록을 조회합니다.")
    @GetMapping("/blog/rank")
    fun searchWordRank(): List<WordCount> = kakaoApiService.searchWordRank()

    @Operation(summary = "책 목록 조회", description = "책 목록을 조회합니다.")
    @GetMapping("/book")
    fun searchBook(query: String, sort: String?, page: Int?, size: Int?, target: String?): ResponseEntity<String>? {
        if(query.isBlank()) {
            throw InvalidInputException("query is not blank")
        }
        val result: String? = kakaoApiService.searchBook(query, sort, page, size, target)

        return ResponseEntity.ok(result)
    }
}