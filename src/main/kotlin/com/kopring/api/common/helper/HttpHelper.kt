package com.kopring.api.common.helper

import com.kopring.api.common.entity.Wordcount
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@Component
class HttpHelper {
    @Value("\${REST_API_KEY}")
    lateinit var restApiKey: String

//    fun requestGetResponseString(url: String): ResponseEntity<String>? {
//        val webClient: WebClient = WebClient
//                .builder()
//                .baseUrl(url)
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .build()
//
//        val response = webClient
//                .get()
//                .uri {
//                    it.path("/v2/search/blog")
//                            .queryParam("query", 1)
//                            .queryParam("sort", 1)
//                            .queryParam("page", 1)
//                            .queryParam("size", 1)
//                            .build()
//                }
//                .header("Authorization", "KakaoAK $restApiKey")
//                .retrieve()
//                .bodyToMono<ResponseEntity<String>>()
//                .block()
//
//        return response
//    }

    fun requestGetResponseString(path: String, headers: HttpHeaders?, params: Map<String, Any>?): ResponseEntity<String>? {
        val webClient: WebClient = WebClient.builder().build()

        val requestBuilder = webClient
                .get()
                .uri { uriBuilder ->
                    uriBuilder.path(path)
                    params?.let {
                        it.forEach { (key, value) -> uriBuilder.queryParam(key, value) }
                    }
                    uriBuilder.build()
                }

        headers?.let { requestBuilder.headers { it.addAll(headers) } }

        return requestBuilder.retrieve().bodyToMono<ResponseEntity<String>>().block()
    }

    fun requestGetResponseStringMap(parmaPath: String, paramHeaders: HttpHeaders, params: MultiValueMap<String, String>): ResponseEntity<String>? {
        val webClient: WebClient = WebClient.create()

        val requestBuilder = webClient
                .get()
                .uri { uriBuilder ->
                    uriBuilder
                            .path(parmaPath)
                            .queryParams(params)
                            .build()
                }
                .headers { httpHeaders -> httpHeaders.addAll(paramHeaders) }

        return requestBuilder.retrieve().bodyToMono<ResponseEntity<String>>().block()
    }
}