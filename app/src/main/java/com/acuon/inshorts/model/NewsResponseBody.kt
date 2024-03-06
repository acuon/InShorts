package com.acuon.inshorts.model

data class NewsResponseBody(
    val category: String,
    val `data`: List<NewsItem>,
    val success: Boolean
)

data class NewsItem(
    val author: String,
    val content: String,
    val date: String,
    val id: String,
    val imageUrl: String,
    val readMoreUrl: String,
    val time: String,
    val title: String,
    val url: String
)