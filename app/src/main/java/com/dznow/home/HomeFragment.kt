package com.dznow.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dznow.R
import com.dznow.category.CategoryDataFactory
import com.dznow.category.CategoryModel
import com.dznow.network.OkHttpRequest
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // test the request
        fetchJson()
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = rootView.findViewById(R.id.rv_item) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        // recyclerView.adapter = CategoryAdapter(CategoryDataFactory.getAll(1))
        return rootView
    }

    private fun fetchJson() {
        val url = "https://dznow.herokuapp.com/api/v0/fr/all"
        val client = OkHttpClient()
        val request = OkHttpRequest(client)
        request.GET(url, object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)
                val latest = CategoryModel(0, "Latest", "", "#000000", "#FFFFFF", homeFeed.latest)
                val categories : MutableList<CategoryModel> = mutableListOf()
                categories.add(0, latest)
                categories.addAll(homeFeed.categories)
                getActivity()?.runOnUiThread {
                    recyclerView.adapter = CategoryAdapter(categories)
                }
            }
        })
    }
}
