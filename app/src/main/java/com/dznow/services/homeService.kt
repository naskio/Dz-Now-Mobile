package com.dznow.services

import com.dznow.models.CategoryModel
import com.dznow.services.helpers.HomeFeed
import com.dznow.models.ArticleModel
import com.dznow.services.network.OkHttpRequest
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

fun homeService(success: (ArrayList<ArticleModel>, ArrayList<CategoryModel>) -> Unit, failure: () -> Unit) {
    val url = homeAPI("fr")
    val client = OkHttpClient()
    val request = OkHttpRequest(client)

    request.GET(url, object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            failure()
        }

        override fun onResponse(call: Call, response: Response) {
            val body = response.body()?.string()
            val gson = GsonBuilder().create()
            val homeFeed = gson.fromJson(body, HomeFeed::class.java)
            success(ArrayList(homeFeed.latest), ArrayList(homeFeed.categories))
        }
    })
}