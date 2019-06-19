package com.dznow.services.helpers

import android.content.Context
import com.dznow.models.ArticleModel
import com.google.gson.GsonBuilder

class Bookmarks {
    val filename = "bookmarks.json"
    var bookmarks : ArticleFeed

    init {
        val gson = GsonBuilder().create()
        var fileContent = App.appContext?.openFileInput(filename)?.bufferedReader().use { it?.readText() }
        if (fileContent.equals("")) {
            fileContent = "{ articles: [] }"
        }
        bookmarks = gson.fromJson(fileContent, ArticleFeed::class.java)
    }

    fun bookmark(article : ArticleModel?) {
        if (article != null) {
            if (!isBookmarked(article)) {
                bookmarks.articles.add(article)
            }
            else {
                bookmarks.articles.remove(article)
            }
        }
        saveBookmarks()
    }

    fun isBookmarked(article : ArticleModel?): Boolean {
        return bookmarks.articles.contains(article)
    }

    fun saveBookmarks() {
        val gson = GsonBuilder().create()
        val bookmarksJson = gson.toJson(bookmarks, ArticleFeed::class.java)
        App.appContext?.deleteFile(filename)
        App.appContext?.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it?.write(bookmarksJson.toByteArray())
        }
    }
}