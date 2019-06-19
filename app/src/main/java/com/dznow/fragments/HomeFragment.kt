package com.dznow.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dznow.R
import com.dznow.recyclers.CategoryPreviewAdapter
import com.dznow.models.ArticleModel
import com.dznow.models.CategoryModel
import com.dznow.recyclers.ArticlePreviewAdapter

class HomeFragment : Fragment() {

    private lateinit var latest: ArrayList<ArticleModel>
    private lateinit var categories: ArrayList<CategoryModel>
    private lateinit var categoriesRecyclerView: RecyclerView
    private lateinit var latestRecyclerView: RecyclerView

    companion object {
        @JvmStatic
        fun newInstance(latest: ArrayList<ArticleModel>, categories: ArrayList<CategoryModel>) = HomeFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList("latest", latest)
                putParcelableArrayList("categories", categories)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // fetching data
        this.arguments?.getParcelableArrayList<ArticleModel>("latest")?.let { latest = it }
        this.arguments?.getParcelableArrayList<CategoryModel>("categories")?.let { categories = it }

        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        // displaying latest
        latestRecyclerView = rootView.findViewById(R.id.recyclerViewLatest) as RecyclerView
        latestRecyclerView.layoutManager = LinearLayoutManager(activity)
        latestRecyclerView.adapter = ArticlePreviewAdapter(latest)

        // displaying categories
        categoriesRecyclerView = rootView.findViewById(R.id.recyclerViewCategories) as RecyclerView
        categoriesRecyclerView.layoutManager = LinearLayoutManager(activity)
        categoriesRecyclerView.adapter = CategoryPreviewAdapter(categories)

        return rootView
    }

    override fun onResume() {
        super.onResume()
        latestRecyclerView.adapter = ArticlePreviewAdapter(latest)
        categoriesRecyclerView.adapter = CategoryPreviewAdapter(categories)
    }
}
