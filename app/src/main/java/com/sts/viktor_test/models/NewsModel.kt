package com.sts.viktor_test.models

data class TopHeadLines (
    val totalArticles : Int,
    val articles : List<NewsModel>
)

data class NewsModel(
    val title : String,
    val description : String,
    val content : String,
    val url : String,
    val image : String,
    val publishedAt : String
)
