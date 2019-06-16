package com.dznow.home

import com.dznow.article.ArticleModel
import com.dznow.category.CategoryModel

class HomeFeed (
    val latest : List<ArticleModel>,
    var categories : List<CategoryModel>
)