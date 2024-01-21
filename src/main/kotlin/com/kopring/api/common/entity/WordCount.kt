package com.kopring.api.common.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "word_count")
class WordCount (
        @Id
        val word: String,

        var cnt: Int = 0
)