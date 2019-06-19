package com.dznow.services

import com.dznow.models.ArticleModel
import com.dznow.services.helpers.ArticleFeed
import com.dznow.services.helpers.CategoryFeed
import com.dznow.services.network.OkHttpRequest
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

fun categoryService(success: (ArrayList<ArticleModel>) -> Unit, failure: () -> Unit, categoryId : Int, page : Int) {
    val url = categoryAPI("fr", categoryId, page)
    val client = OkHttpClient()
    val request = OkHttpRequest(client)

    request.GET(url, object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            failure()
        }

        override fun onResponse(call: Call, response: Response) {
            val body = "{ category : " + response.body()?.string() + "}"
            val gson = GsonBuilder().create()
            val categoryFeed = gson.fromJson(body, CategoryFeed::class.java)
            success(ArrayList(categoryFeed.category.articles))
        }
    })
}
