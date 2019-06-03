package com.searoth.template.domain.models.news

data class BBCTopHeadline(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)