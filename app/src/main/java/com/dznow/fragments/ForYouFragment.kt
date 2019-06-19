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
import com.dznow.services.storage.ForYouCategories

class ForYouFragment : Fragment() {

    private lateinit var categoriesForYouRecyclerView: RecyclerView
    private lateinit var sourcesForYouRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_for_you, container, false)

        // displaying categories
        categoriesForYouRecyclerView = rootView.findViewById(R.id.recyclerViewCategories) as RecyclerView
        categoriesForYouRecyclerView.layoutManager = LinearLayoutManager(activity)
        categoriesForYouRecyclerView.adapter = CategoryPreviewAdapter(ForYouCategories.getInstance().getStarringCategories())

        // displaying sources
        sourcesForYouRecyclerView = rootView.findViewById(R.id.recyclerViewCategories) as RecyclerView
        sourcesForYouRecyclerView.layoutManager = LinearLayoutManager(activity)
        sourcesForYouRecyclerView.adapter = CategoryPreviewAdapter(ForYouCategories.getInstance().getStarringCategories())

        return rootView
    }

    override fun onResume() {
        super.onResume()
        categoriesForYouRecyclerView.adapter = CategoryPreviewAdapter(ForYouCategories.getInstance().getStarringCategories())
        sourcesForYouRecyclerView.adapter = CategoryPreviewAdapter(ForYouCategories.getInstance().getStarringCategories())
    }
}
