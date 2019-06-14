package com.dznow.models

data class ArticleModel (
    val id : Int,
    val title : String,
    val content : String,
    val minutes_read : Int,
    val cover_url : String,
    val created_at : String,
    val category : CategoryModel,
    val source : SourceModel,
    val url : String
)