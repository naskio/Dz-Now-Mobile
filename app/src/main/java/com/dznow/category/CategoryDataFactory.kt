package com.dznow.category

import com.dznow.article.ArticleDataFactory

object CategoryDataFactory {
    fun getParents(count : Int) : List<CategoryModel>{
        val items = mutableListOf<CategoryModel>()
        for (i in 0..count) {
            val item = CategoryModel("title $i", ArticleDataFactory.getArticles(3))
            items.add(item)
        }
        return items
    }
}
