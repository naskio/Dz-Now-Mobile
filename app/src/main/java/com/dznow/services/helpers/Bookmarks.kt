package com.dznow.services.helpers

import com.dznow.models.ArticleModel
import com.dznow.services.bookmarksFileName
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
        else {
            bookmarks.articles.remove(article)
        }
        saveBookmarks()
    }

    fun isBookmarked(article : ArticleModel): Boolean {
        return bookmarks.articles.contains(article)
    }

    fun saveBookmarks() {
        val gson = GsonBuilder().create()
        val bookmarksJson = gson.toJson(bookmarks, ArticleFeed::class.java)
        App.appContext?.deleteFile(bookmarksFileName)
        store(bookmarksFileName, bookmarksJson)
    }

    private object Singleton {
        val instance = Bookmarks()
    }

    companion object {
        fun getInstance() = Singleton.instance
    }
}
