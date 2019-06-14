package com.dznow.home

import com.dznow.models.CategoryModel

object CategoryDataFactory {
    fun getParents(count : Int) : List<CategoryModel>{
        val items = mutableListOf<CategoryModel>()
        for (i in 0..count) {
            val item = CategoryModel(i, "category $i","category $i", "category $i", "category $i", ArticleDataFactory.getArticles(3))
            items.add(item)
        }
        return items
    }
}
