package com.dznow.home

import com.dznow.models.ArticleModel
import com.dznow.models.CategoryModel
import com.dznow.models.SourceModel

object ArticleDataFactory {
    fun getArticles (count : Int) : List<ArticleModel>{
        val articles = mutableListOf<ArticleModel>()
        for (i in 0..count) {
            val source = SourceModel(i, "source $i", "source $i", "source $i", "source $i", "source $i", null)
            val category = CategoryModel(i, "category $i", "category $i", "category $i", "category $i", null)
            val article = ArticleModel(i, "article $i", "article $i", 2,"article $i", "article $i", category, source, "article $i")
            articles.add(article)
        }
        return articles
    }
}
