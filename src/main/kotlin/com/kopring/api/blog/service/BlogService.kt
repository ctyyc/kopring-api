package com.kopring.api.blog.service

import com.kopring.api.blog.dto.BlogDto
import com.kopring.api.blog.entity.Wordcount
import com.kopring.api.blog.repository.WordRepository
import com.kopring.api.common.exception.InvalidInputException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class BlogService(
        val wordRepository: WordRepository
) {
    @Value("\${REST_API_KEY}")
    lateinit var restApiKey: String

    fun searchKakao(blogDto: BlogDto): String? {
        val webClient: WebClient = WebClient
                .builder()
                .baseUrl("https://dapi.kakao.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()

        val response = webClient
                .get()
                .uri {
                    it.path("/v2/search/blog")
                        .queryParam("query", blogDto.query)
                        .queryParam("sort", blogDto.sort)
                        .queryParam("page", blogDto.page)
                        .queryParam("size", blogDto.size)
                        .build()
                }
                .header("Authorization", "KakaoAK $restApiKey")
                .retrieve()
                .bodyToMono<String>()

        val result = response.block()

        val lowQuery: String = blogDto.query.lowercase()
        val word: Wordcount = wordRepository.findById(lowQuery).orElse(Wordcount(lowQuery))
        word.cnt++

        wordRepository.save(word)

        return result
    }

    fun searchWordRank(): List<Wordcount> = wordRepository.findTop10ByOrderByCntDesc()
}
