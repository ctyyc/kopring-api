package com.kopring.api.kakao

import com.kopring.api.common.entity.WordCount
import org.springframework.data.repository.CrudRepository

interface WordCountRepository : CrudRepository<WordCount, String> {
    fun findTop10ByOrderByCntDesc(): List<WordCount>
}
