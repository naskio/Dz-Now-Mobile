package com.dznow.models

data class CategoryModel (
    val id : Int,
    val name : String,
    val background_url : String,
    val background_color : String,
    val text_color : String,
    val articles : List<ArticleModel>?
)