package com.dznow.article

object ArticleDataFactory {
    fun getArticles (count : Int) : List<ArticleModel>{
        val articles = mutableListOf<ArticleModel>()
        for (i in 0..count) {
            val article = ArticleModel("source $i", "date $i", "title $i", "time $i")
            articles.add(article)
        }
        return articles
    }
}
