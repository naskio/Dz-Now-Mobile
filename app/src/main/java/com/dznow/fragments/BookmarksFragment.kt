package com.dznow.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dznow.R
import com.dznow.recyclers.ArticlePreviewAdapter
import com.dznow.services.storage.Bookmarks

class BookmarksFragment : Fragment() {

    private lateinit var bookmarksRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_bookmarks, container, false)

        // displaying bookmarks
        bookmarksRecyclerView = rootView.findViewById(R.id.recyclerViewBookmarks) as RecyclerView
        bookmarksRecyclerView.layoutManager = LinearLayoutManager(activity)
        bookmarksRecyclerView.adapter = ArticlePreviewAdapter(Bookmarks.getInstance().getBookmarks())

        return rootView
    }

    override fun onResume() {
        super.onResume()
        bookmarksRecyclerView.adapter = ArticlePreviewAdapter(Bookmarks.getInstance().getBookmarks())
    }
}
