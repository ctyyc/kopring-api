package com.kopring.api.common.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Configuration

@OpenAPIDefinition(info = Info(title = "Blog API 명세서", description = "Blog 검색 서비스 API 명세서", version = "v1"))
@Configuration
class SwaggerConfig {

}