package com.dznow.article

import com.dznow.article.ArticleModel
import com.dznow.category.CategoryModel
import com.dznow.source.SourceModel

object ArticleDataFactory {
    fun getArticles (count : Int) : List<ArticleModel>{
        val articles = mutableListOf<ArticleModel>()
        for (i in 0..count) {
            val source =
                SourceModel(i, "sourceName $i", "sourceName $i", "sourceName $i", "sourceName $i", "sourceName $i", null)
            val category =
                CategoryModel(i, "category $i", "category $i", "category $i", "category $i", null)
            val article = ArticleModel(
                i,
                "article $i",
                "article $i",
                2,
                "article $i",
                "article $i",
                category,
                source,
                "article $i"
            )
            articles.add(article)
        }
        return articles
    }
}
