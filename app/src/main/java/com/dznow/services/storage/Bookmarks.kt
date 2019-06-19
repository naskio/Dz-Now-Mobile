package com.dznow.services.storage

import com.dznow.models.ArticleModel
import com.dznow.services.helpers.ArticleFeed
import com.google.gson.GsonBuilder

class Bookmarks private constructor(){
    private var bookmarks : ArticleFeed

    init {
        val gson = GsonBuilder().create()
        var fileContent = retrieve(bookmarksFileName)
        if (fileContent == "") {
            fileContent = "{ articles: [] }"
        }
        bookmarks = gson.fromJson(fileContent, ArticleFeed::class.java)
    }

    fun bookmark(article : ArticleModel) {
        if (!isBookmarked(article)) {
            bookmarks.articles.add(article)
        }
        saveBookmarks()
    }

    fun unBookmark(article : ArticleModel) {
        if (isBookmarked(article)) {
            bookmarks.articles.remove(bookmarks.articles.find { x -> x.id == article.id })
        }
        saveBookmarks()
    }

    fun isBookmarked(article : ArticleModel): Boolean {
        return bookmarks.articles.any { x -> x.id == article.id }
    }

    fun saveBookmarks() {
        val gson = GsonBuilder().create()
        val bookmarksJson = gson.toJson(bookmarks, ArticleFeed::class.java)
        store(bookmarksFileName, bookmarksJson)
    }

    fun getBookmarks() : ArrayList<ArticleModel> {
        return bookmarks.articles
    }

    private object Singleton {
        val instance = Bookmarks()
    }

    companion object {
        fun getInstance() = Singleton.instance
    }
}
