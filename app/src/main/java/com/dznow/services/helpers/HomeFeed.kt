package com.dznow.services.helpers

import com.dznow.models.ArticleModel
import com.dznow.models.CategoryModel

class HomeFeed (
    val latest : List<ArticleModel>,
    var categories : List<CategoryModel>
)