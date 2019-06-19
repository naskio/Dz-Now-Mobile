package com.dznow.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dznow.R
import com.dznow.recyclers.BrowseCategoriesAdapter
import com.dznow.recyclers.BrowseSourcesAdapter
import com.dznow.services.helpers.CategoriesFeed
import com.dznow.services.network.OkHttpRequest
import com.dznow.services.helpers.SourcesFeed
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

class BrowseFragment : Fragment() {
    lateinit var categoriesRecyclerView: RecyclerView
    lateinit var sourcesRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // asynchronous call to fetch data from api
        fetchJson()
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_browse, container, false)
        // categories recycler view
        categoriesRecyclerView = rootView.findViewById(R.id.recyclerViewCategories) as RecyclerView
        categoriesRecyclerView.setHasFixedSize(true)
        categoriesRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        // categoriesRecyclerView.adapter = CategoryPreviewAdapter(CategoryDataFactory.getCategories(4))
        // sources recycler view
        sourcesRecyclerView = rootView.findViewById(R.id.rv_sources) as RecyclerView
        sourcesRecyclerView.setHasFixedSize(true)
        sourcesRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        // sourcesRecyclerView.adapter = BrowseSourcesAdapter(SourceDataFactory.getSources(3))
        // return rootView
        return rootView
    }

    private fun fetchJson() {
        val categoriesUrl = "https://dznow.herokuapp.com/api/v0/fr/categories"
        val client = OkHttpClient()
        val request = OkHttpRequest(client)
        request.GET(categoriesUrl, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = "{ categories: " + response.body()?.string() + "}"
                val gson = GsonBuilder().create()
                val categoryFeed = gson.fromJson(body, CategoriesFeed::class.java)
                getActivity()?.runOnUiThread {
                    categoriesRecyclerView.adapter =
                        BrowseCategoriesAdapter(categoryFeed.categories)
                }
            }
        })
        val sourcesUrl = "https://dznow.herokuapp.com/api/v0/fr/sources"
        request.GET(sourcesUrl, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = "{ sources: " + response.body()?.string() + "}"
                val gson = GsonBuilder().create()
                val sourceFeed = gson.fromJson(body, SourcesFeed::class.java)
                getActivity()?.runOnUiThread {
                    sourcesRecyclerView.adapter = BrowseSourcesAdapter(sourceFeed.sources)
                }
            }
        })
    }
}
