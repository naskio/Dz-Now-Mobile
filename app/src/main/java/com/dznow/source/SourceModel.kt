package com.dznow.source

import com.dznow.article.ArticleModel

data class SourceModel (
    val id : Int,
    val name : String,
    val logo_url : String,
    val background_color : String,
    val text_color : String,
    val website : String,
    val articles : List<ArticleModel>?
)