package com.dznow.category

import com.dznow.article.ArticleDataFactory

object CategoryDataFactory {
    fun getAll(count : Int) : List<CategoryModel>{
        val items = mutableListOf<CategoryModel>()
        for (i in 0..count) {
            val item = CategoryModel(
                i,
                "category $i",
                "category $i",
                "category $i",
                "category $i",
                ArticleDataFactory.getArticles(3)
            )
            items.add(item)
        }
        return items
    }

    fun getCategories(count : Int) : List<CategoryModel>{
        val items = mutableListOf<CategoryModel>()
        for (i in 0..count) {
            val item = CategoryModel(
                i,
                "category $i",
                "category $i",
                "category $i",
                "category $i",
                null
            )
            items.add(item)
        }
        return items
    }
}
