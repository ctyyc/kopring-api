package com.kopring.api.blog.controller

import com.kopring.api.blog.dto.BlogDto
import com.kopring.api.blog.service.BlogService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/blog")
@RestController
class BlogController(
        val blogService: BlogService
) {
    @GetMapping("")
    fun search(@RequestBody blogDto: BlogDto): String? {
        val result: String? = blogService.searchKakao(blogDto)
        return result
    }
}