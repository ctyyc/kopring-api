package com.kopring.api.blog.repository

import com.kopring.api.blog.entity.Wordcount
import org.springframework.data.repository.CrudRepository

interface WordRepository : CrudRepository<Wordcount, String>